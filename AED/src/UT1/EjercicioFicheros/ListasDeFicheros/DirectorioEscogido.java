package UT1.EjercicioFicheros.ListasDeFicheros;

import java.io.File;
import java.util.Scanner;

public class DirectorioEscogido {
    static File file;

    public static void main(String[] args) {
        System.out.print("Introduzca el directorio: ");
        file = new File(GetDirectory());
        if (checkDirectory()) { FileNames(); }
        else { System.err.println("Has introducido un directorio invalido");}
    }

    private static void FileNames() {
        for (String fileName : file.list())
            System.out.println(fileName);
    }

    private static String GetDirectory() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static boolean checkDirectory() {
        return file.isDirectory();
    }
}
