package UT1.SimulacroExamen.Process2.src;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
//    private static OutputStream outputStream;

    public static void main(String[] args) {
        ProcessBuilder processBuilder3 = new ProcessBuilder("cmd", "/C", "E:\\Programacion\\PGV\\src\\SimulacroExamen\\process3.bat");

        Scanner scanner = new Scanner(System.in);

        //Espera a la entrada de datos del Main (OutputStream)
        String dir = scanner.nextLine();

        File file = new File(dir);
        if (file.isDirectory()) {
            //Obtenemos los nombres de los ficheros dentro del directorio
            String[] fileNames = file.list();

            try {
                for (String myFileName : fileNames) {
                    Process process3 = processBuilder3.start();
                    //Escribe en el proceso3 que en este caso contiene el .bat
                    OutputStream(dir + "\\" + myFileName, process3);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void OutputStream(String text, Process process) {
        OutputStream outputStream = process.getOutputStream();
        try {
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}