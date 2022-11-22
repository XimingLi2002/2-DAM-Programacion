package UT1.Examen;

import java.io.*;

public class Main2 {
    static int nombrePeliLength = 30;
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    static RandomAccessFile randomAccessFile;
    static String pathDAT = "./src/UT1.Examen/secuencialBinario.DAT";
    static String pathDAT2 = "./src/UT1.Examen/secuencialBinario2.DAT";
    public static void main(String[] args) {
        dataWriteFile();
        dataReadFile();
        //readRandomFileAccess();
    }
    private static void dataWriteFile() {
        int[] codPeli = new int[]{121, 125, 215, 123, 200};
        String[] namePeli = new String[]{"Los juegos del Hambre", "El corredor del laberinto", "peli3", "peli4", "peli5"};
        double[] precioPeli = new double[]{3.95, 2.50, 4.30, 5.00, 3.25};
        openDataOutputStream();
        try {
            for (int i = 0; i < codPeli.length; i++) {
                dataOutputStream.writeInt(codPeli[i]);
                dataOutputStream.writeUTF(namePeli[i]);
                dataOutputStream.writeDouble(precioPeli[i]);
                dataOutputStream.flush();
                System.out.println(i);
            }
            dataOutputStream.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private static void dataReadFile() {
        int codPeli;
        String namePeli;
        double precioPeli;
        openDataiNputStream();
        openRandomFileAccess(pathDAT2, "rw");
        try {
            try {
                //cuando se quede sin nada para leer saltará una excepcion y se saldrá del bucle
                while (true) {
                    codPeli = dataInputStream.readInt();
                    namePeli = dataInputStream.readUTF();
                    precioPeli = dataInputStream.readDouble();
                    System.out.println(codPeli + "   " + namePeli + "   " + codPeli);
                    write(codPeli,namePeli,precioPeli);
                }
            } catch (EOFException ignored) {
            }
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeRandomFileAccess();
    }
    private static void write(int codPeli, String  namePeli, double precioPeli) throws IOException {
        randomAccessFile.writeInt(codPeli);
        randomAccessFile.writeChars(namePeli);
        randomAccessFile.writeDouble(precioPeli);
    }
    private static void readRandomFileAccess() {
        openRandomFileAccess(pathDAT2, "r");

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
    private static void openDataOutputStream(){
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(pathDAT));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }private static void openDataiNputStream(){
        try {
            dataInputStream = new DataInputStream(new FileInputStream(pathDAT));
        } catch (FileNotFoundException e) {
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
