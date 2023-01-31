package UT1.SimulacroExamen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
//
        ProcessBuilder processBuilder2 = new ProcessBuilder("java","-jar","E:\\Programacion\\PGV\\src\\SimulacroExamen\\Process2.jar");
        ProcessBuilder processBuilder4 = new ProcessBuilder("cmd","/C","dir E:\\Programacion\\PGV\\src\\SimulacroExamen\\NuevaCarpeta");
        try {
            Process process1 = Runtime.getRuntime().exec("msconfig");
//            Process process1 = processBuilder1.start();
            Process process2 = processBuilder2.start();
            OutputStream("E:\\Programacion\\PGV\\src\\SimulacroExamen\\NuevaCarpeta",process2);
            process2.waitFor();
            Process process4 = processBuilder4.start();
            InputStream(process4);
            process1.destroy();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void OutputStream(String text, Process process){
        OutputStream outputStream = process.getOutputStream();
        try {
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void InputStream(Process process) {
//        StringBuffer text = new StringBuffer();
        InputStream inputStream = process.getInputStream();
        int c;
        try {
            while ((c = inputStream.read()) != -1) {
//                text.append((char) c);
                System.out.print((char) c);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(text);
    }
}
