package UT1.EjercicioFicheros.FicherosBinarios;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FicherosBinarios {
    public static void main(String[] args) {
        try {
            File file = new File("./AED/src/UT1.EjercicioFicheros/FicherosBinarios", "FicherosBinarios.txt");
            write(file);
            append(file);
            read(file);
        } catch (NullPointerException e) {
            System.out.println("Fichero no encontrado");
        }
    }

    private static void write(File file) {
        String text;
        byte[] code;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (int i = 1; i <= 50; i++) {
                text = "Número: " + i + "\n";
                code = text.getBytes(StandardCharsets.UTF_8);
                fileOutputStream.write(code);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    private static void read(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int character;
            while ((character = fileInputStream.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    private static void append(File file) {
        try {
            String textAppend = "++++++++++";
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            byte[] code;
            code = textAppend.getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(code);
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }
}
