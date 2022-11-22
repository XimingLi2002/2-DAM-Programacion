package UT1.Examen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        //He separado las rutas para que a la hora de que necesitas modificarlo para ver su funcionamiento sea mas sencillo
        String getHostnamePATH = "D:\\Programacion\\PGV\\src\\Examen\\getHostname.jar";
        String batPATH = "D:\\Programacion\\PGV\\src\\Examen\\Ping.bat";

        //Ejecuta taskmgr.exe (apartado b del examen)
        ProcessBuilder processBuilder1 = new ProcessBuilder("cmd","/C","taskmgr.exe");

        //Ejecuta un jar que ejecutara el comando hostname y se lo pasará al padre y se almacenará en hostname
        //(Podría pillar el dato y darselo directamente al proceso hijo pero he decido guardarlo para primero mostrarlo)
        ProcessBuilder processBuilder2 = new ProcessBuilder("java","-jar", getHostnamePATH);

        //Ejecuta el bat que hará ping al hostname
        ProcessBuilder processBuilder3 = new ProcessBuilder("cmd", "/C", batPATH);

        Process process1 = startProcess(processBuilder1);
        //proceso donde el hijo ejecuta el comando hostname y se lo pasa al padre y lo muestra por pantalla
        Process process2 = startProcess(processBuilder2);
        String hostname = getHostName(process2);
        System.out.println(hostname);

        Process process3 = startProcess(processBuilder3);
        //pasa al hijo (.bat) el nombre del hostname, en el bat lo guardará como variable y lo ejecutará con el ping
        OutputStream(hostname, process3);
        //muestra la información
        InputStream(process3);
    }
    private static Process startProcess(ProcessBuilder processBuilder){
        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process;
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
    private static String getHostName(Process process){
        StringBuffer text = new StringBuffer();
        InputStream inputStream = process.getInputStream();
        int c;
        try {
            while ((c = inputStream.read()) != -1) {
                text.append((char) c);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
