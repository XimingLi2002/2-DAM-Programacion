package UT1.Examen.Hostname.src;

import java.io.IOException;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/C", "hostname");

        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();

            int c;
            while((c = inputStream.read()) != -1) {
                System.out.print((char)c);
            }

            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}