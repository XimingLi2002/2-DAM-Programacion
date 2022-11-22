package UT1.EjercicioFicheros.ConBuffered;

import java.io.*;

public class Buffered {

    public static void main(String[] args) {
        File file = new File("./AED/src/UT1.EjercicioFicheros/ConBuffered", "Buffered.txt");
        try {
            writeFile(file);
            readFile(file);
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }
    private static void writeFile(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        byte row = 1;
        while (row <= 10) {
            bufferedWriter.write(text(row));
            row++;
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }
    private static String text(byte row) {return "Fila " + row + " Dam2";}
    private static void readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        int valor;
        while ((valor = fileReader.read()) != -1) {
            System.out.print((char) valor);
        }
        fileReader.close();
    }
}
