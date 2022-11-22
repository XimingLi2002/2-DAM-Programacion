package UT1.EjercicioFicheros.PropiedadesDeFicheros;

import UT2.ManejoDeConectores.Interfaces.ConsoleColors;
import java.io.File;
import java.util.Scanner;

public class Propiedades implements ConsoleColors {

    public static void main(String[] args) {
        System.out.print("Introduzca el directorio: ");
        File directory = new File(GetDirectory());
        if (checkDirectory(directory)) {
            FileProperties(directory);
        } else {
            System.err.println("Has introducido un directorio invalido");
        }
    }

    private static void FileProperties(File directory) {
        File[] files = directory.listFiles();
        for (File file: files) {
            System.out.println("Nombre: " + CYAN + file.getName() + RESET
                    + "   Ruta relativa: " + CYAN + file.getPath() + RESET
                    + "   Ruta absoluta: " + CYAN + file.getAbsolutePath() + RESET
                    + "   Atributo de lectura: " + CYAN + file.canRead() + RESET
                    + "   Atributo de escritura: " + CYAN + file.canWrite() + RESET
                    + "   Tamaño: " + CYAN + file.length() + RESET
                    + "   ¿Es directorio?: " + CYAN + file.isDirectory() + RESET
                    + "   ¿Es fichero?: " + CYAN + file.isFile() + RESET);
            printSubdirectories(file);
        }
    }

    private static void printSubdirectories(File file) {
        String[] subDirectories = file.list();
        if (checkDirectory(file)) {
            System.out.print("Subdirectorios: ");
            System.out.print(GREEN_BRIGHT);
            for (String subFile : subDirectories) {
                System.out.printf("%20s",subFile);
            }
            System.out.println(RESET);
        }
    }

    private static String GetDirectory() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static boolean checkDirectory(File directory) {
        return directory.isDirectory();
    }

}
