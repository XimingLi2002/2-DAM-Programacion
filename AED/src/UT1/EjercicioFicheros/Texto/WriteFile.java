package UT1.EjercicioFicheros.Texto;

import java.io.*;

public class WriteFile {
    public static void main(String[] args) {
        File file = new File("./AED/src/UT1.EjercicioFicheros/Texto", "WriteFile.txt");
        try {
            writeOnFile(file, "Primera prueba con FileWriter"); //Escribe en el fichero
            readFile(file);
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    private static void writeOnFile(File file, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        char[] c = text.toCharArray();
        fileWriter.write(c);
        addFinal(fileWriter, " y esto es el final.");
        //fileWriter.flush(); //Guarda los cambios del fichero
        fileWriter.close();
    }

    private static void addFinal(FileWriter fileWriter, String text) throws IOException {
        fileWriter.append(text);
    }

    private static void readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        int valor;
        while ((valor = fileReader.read()) != -1) {
            System.out.print((char) valor);
        }
        fileReader.close();
    }
}
