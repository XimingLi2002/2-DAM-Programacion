package UT1.Examen;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static UT1.Examen.Main.nombrePeliLength;

enum Bytes {
    INT(4), CHAR(2), BYTE(1),FLOAT(4),DOUBLE(8),BOOLEAN(1),SHORT(2),LONG(8),
    NOMBRE_PELI(CHAR.byteSize * nombrePeliLength),
    TOTAL(INT.byteSize + NOMBRE_PELI.byteSize + DOUBLE.byteSize);
    int byteSize;
    Bytes(int byteSize) {
        this.byteSize = byteSize;
    }
}

public class Main {
    static int nombrePeliLength = 30;
    static FileOutputStream fileOutputStream;
    static FileInputStream fileInputStream;
    static RandomAccessFile randomAccessFile;
    static BufferedWriter bufferedWriter;
    static String pathDAT = "./src/UT1.Examen/randomAccessFile.dat";
    static String pathTXT = "./src/UT1.Examen/secuencialBinario.txt";
    public static void main(String[] args) {
        write();
        readByteFile();

        //Apartado 3
        checkCodPeli();

        //Apartado 4
        readRandomFileAccess();
    }

    private static void write () {
        int[] codPeli = new int[]{121, 125, 215, 123, 200};
        String[] namePeli = new String[]{"Los juegos del Hambre", "El corredor del laberinto", "peli3", "peli4", "peli5"};
        double[] precioPeli = new double[]{3.95, 2.50, 4.30, 5.00, 3.25};
        String toText = "";
        openFileOutputStream();
        byte[] code;
        try {
            for (int i = 0; i < codPeli.length; i++) {
                toText = codPeli[i] + ",";
                code = toText.getBytes(StandardCharsets.UTF_8);
                fileOutputStream.write(code);

                toText = namePeli[i] + ",";
                code = toText.getBytes(StandardCharsets.UTF_8);
                fileOutputStream.write(code);

                toText = precioPeli[i] + ":";
                code = toText.getBytes(StandardCharsets.UTF_8);
                fileOutputStream.write(code);

            }
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
        closeFileOutputStream();
    }
    private static void readByteFile() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            openFileInputStream();
            int character;
            while ((character = fileInputStream.read()) != -1) {
                stringBuffer.append((char) character);
            }

            closeFileInputStream();
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
        createRandomFileAccessFile(stringBuffer.toString());
    }

    private static void createRandomFileAccessFile(String string) {
        openRandomFileAccess(pathDAT,"rw");
        String[] peliculas;
        peliculas = string.split(":");
        for (int i = 0; i < peliculas.length; i++) {
            String datosPeliculas[] = peliculas[i].split(",");
            try {
                randomAccessFile.writeInt(Integer.parseInt(datosPeliculas[0]));
                randomAccessFile.writeChars(textModifier(datosPeliculas[1]));
                randomAccessFile.writeDouble(Double.parseDouble(datosPeliculas[2]));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("El parseo de algún dato ha dado error");
            }
        }
        closeRandomFileAccess();
    }
    private static String textModifier(String text) {
        StringBuffer stringBuffer = new StringBuffer(text);
        //Estable la longitud del string
        stringBuffer.setLength(nombrePeliLength);
        return stringBuffer.toString();
    }
    private static void checkCodPeli() {
        openRandomFileAccess(pathDAT, "rw");

        //position = variable que almacena la posicion en bytes
        int position = 0, precioPosition = 0;
        int codPeli;
        double precioPeli;

        try {
            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {
                randomAccessFile.seek(position);
                codPeli = randomAccessFile.readInt();
                if (codPeli%5 == 0) {
                    //Ya que no leo el nombre pues tengo que saltarme esa posicion
                    precioPosition = position + Bytes.NOMBRE_PELI.byteSize + Bytes.INT.byteSize;
                    randomAccessFile.seek(precioPosition);
                    precioPeli = randomAccessFile.readDouble();
                    //vuelve de nuevo a la posicion del precio para sobreescribirlo
                    precioPosition = position + Bytes.TOTAL.byteSize - Bytes.DOUBLE.byteSize;
                    randomAccessFile.seek(precioPosition);
                    randomAccessFile.writeDouble(incrementaPrecio(precioPeli));
                    //leer precio desde fichero: (lo lei y lo imprimí directamente)
                    randomAccessFile.seek(precioPosition);
                    System.out.println("¡¡Precios incrementados!!            codPeli: " + codPeli + "   precioAntiguo: " + precioPeli + "   precioNuevo: " + randomAccessFile.readDouble());
                }
                position += Bytes.TOTAL.byteSize;
            }
        } catch (EOFException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeRandomFileAccess();
    }
    private static double incrementaPrecio(double precioPeli){
        precioPeli += (precioPeli * 0.05);
        return precioPeli;
    }
    private static void readRandomFileAccess() {
        openRandomFileAccess(pathDAT, "rw");

        //position = variable que almacena la posicion en bytes
        int position = 0;
        int codPeli;
        String nombrePeli;
        double precioPeli;

        try {
            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {
                randomAccessFile.seek(position);
                codPeli = randomAccessFile.readInt();
                nombrePeli = getString(nombrePeliLength, randomAccessFile);
                precioPeli = randomAccessFile.readDouble();
                System.out.println("Datos de la película:   " + codPeli + "   " + nombrePeli + "   " + precioPeli);
                position += Bytes.TOTAL.byteSize;
            }
        } catch (EOFException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeRandomFileAccess();
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


    private static void openFileOutputStream() {
        try {
            File file = new File(pathTXT);
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }
    }

    private static void closeFileOutputStream() {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void openFileInputStream() {
        try {
            fileInputStream = new FileInputStream(pathTXT);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }
    }

    private static void closeFileInputStream() {
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
