package UT1.SimulacroExamen;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

enum Bytes {
    INT(4), CHAR(2),
    CLUB_NAME(CHAR.byteSize * Main.clubNameLength),
    PRESIDENT(CHAR.byteSize * Main.presidentLength),
    LOCALE(CHAR.byteSize * Main.localeLength),
    TOTAL(INT.byteSize + CLUB_NAME.byteSize + PRESIDENT.byteSize + INT.byteSize + LOCALE.byteSize);
    int byteSize;
    Bytes(int byteSize) {
        this.byteSize = byteSize;
    }
}

public class Main {
    static BufferedReader bufferedReader;
    static RandomAccessFile randomAccessFile;
    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;
    static int clubNameLength = 50, presidentLength = 50, localeLength = 50;
    static String pathTxt = "./src/UT1.SimulacroExamen/datosEquipos.txt";
    static String pathDAT = "./src/UT1.SimulacroExamen/datosEquipos.dat";
    static String pathOBJ = "./src/UT1.SimulacroExamen/datosEquipos.obj";
    static boolean exists = false;
    static int clubNumberInput;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Lee el fichero .txt y divide las palabras
        splitFileTextByCharacter();
        System.out.print("Introduce el numero del club: ");
        clubNumberInput = consoleInputIntType();
        int telephoneNumberPosition = checkClub();
        if (exists) updateTelephoneNumber(telephoneNumberPosition);
        readObject();
        scanner.close();
    }

    private static void splitFileTextByCharacter() {
        String line;
        openBufferedReader();
        openRandomFileAccess(pathDAT, "rw");
        try {
            //Lee línea a línea el fichero .txt
            while ((line = bufferedReader.readLine()) != null) {
                //Divide las palabras y manda el vector (palabras divididas en una línea) a otro método para escribirlo en el fichero .dat
                FileIntoRandomFile(line.split("##"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeBufferedReader();
        closeRandomFileAccess();
    }

    private static String textModifier(String text, int length) {
        StringBuffer stringBuffer = new StringBuffer(text);
        //Estable la longitud del string
        stringBuffer.setLength(length);
        return stringBuffer.toString();
    }

    private static void FileIntoRandomFile(String[] split) {
        //A cada valor del vector le asigno su nombre (variable)
        //solo para que se entienda mejor aunque lo pudiera haber metido directamente al write
        String clubNumber = split[0];
        String clubName = split[1];
        String president = split[2];
        String telephone = split[3];
        String locale = split[4];
        try {
            randomAccessFile.writeInt(Integer.parseInt(clubNumber));
            randomAccessFile.writeChars(textModifier(clubName, clubNameLength));
            randomAccessFile.writeChars(textModifier(president, presidentLength));
            randomAccessFile.writeInt(Integer.parseInt(telephone));
            randomAccessFile.writeChars(textModifier(locale, localeLength));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("El parseo de algún dato ha dado error");
        }
    }

    private static String getString(int length, RandomAccessFile randomAccessFile) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            try {
                chars[i] = randomAccessFile.readChar();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String(chars).trim();
    }

    private static void writeObjectFile(Equipos equipos) {
        try {
            objectOutputStream.writeObject(equipos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkClub() {
        openObjectOutputStream();
        openRandomFileAccess(pathDAT, "r");

        //position = variable que almacena la posicion en bytes
        //telephoneNumberPosition = variable que almacena la posicion del telefono de un club
        int position = 0, telephoneNumberPosition = 0;

        int clubNumber, telephoneNumber;
        String clubName, president, locate;

        try {
            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {

                randomAccessFile.seek(position);
                clubNumber = randomAccessFile.readInt();
                clubName = getString(clubNameLength, randomAccessFile);
                president = getString(presidentLength, randomAccessFile);
                telephoneNumber = randomAccessFile.readInt();
                locate = getString(localeLength, randomAccessFile);

                //Escribe en el fichero .obj los objetos
                writeObjectFile(new Equipos(clubNumber, clubName, president, telephoneNumber, locate));

                //comprueba si el numero de club introducido existe y muestra sus datos
                if (clubNumberInput == clubNumber) {
                    System.out.println("Datos del club:   " + clubName + "   " + president + "   " + telephoneNumber + "   " + locate);
                    exists = true;
                    //guarda la posicion del telefono del club
                    telephoneNumberPosition = position + Bytes.TOTAL.byteSize - Bytes.LOCALE.byteSize - Bytes.INT.byteSize;
                }
                position += Bytes.TOTAL.byteSize;
            }
        } catch (EOFException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!exists) {
            System.out.println("No existe el club");
        }
        closeRandomFileAccess();
        closeObjectOutputStream();
        return telephoneNumberPosition;
    }

    private static void updateTelephoneNumber(int telephoneNumberPosition) {
        //int telephoneNumber;
        openRandomFileAccess(pathDAT, "rw");
        try {
            //randomAccessFile.seek(telephoneNumberPosition);
            //telephoneNumber = randomAccessFile.readInt();
            System.out.print("Introduce el nuevo telefono del club: ");
            int newTelephoneNumber = consoleInputIntType();
            randomAccessFile.seek(telephoneNumberPosition);
            randomAccessFile.writeInt(newTelephoneNumber);
            checkClub();
            closeRandomFileAccess();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readObject() {
        openObjectInputStream();
        Equipos equipos;
        try {
            while (true) {
                equipos = (Equipos) objectInputStream.readObject();
                System.out.println(equipos.toString());
            }
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        closeObjectInputStream();
    }

    private static void openBufferedReader() {
        try {
            bufferedReader = new BufferedReader(new FileReader(pathTxt));
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }
    }

    private static void closeBufferedReader() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openRandomFileAccess(String path, String mode) {
        try {
            randomAccessFile = new RandomAccessFile(new File(path), mode);
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado");
        }
    }

    private static void closeRandomFileAccess() {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openObjectOutputStream() {
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(pathOBJ));
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeObjectOutputStream() {
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void openObjectInputStream() {
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(pathOBJ));
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeObjectInputStream() {
        try {
            objectInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static int consoleInputIntType() {
        int input = 0;
        boolean error;
        do {
            error = false;
            try {
                input = scanner.nextInt();
            } catch (NoSuchElementException e) {
                scanner.next();
                System.err.println("Dato inválido");
                error = true;
            }
        } while (error);
        return input;
    }

}
