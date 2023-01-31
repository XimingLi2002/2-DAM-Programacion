package UT1.ComunicacionDeProcesos.Hijos.ServiceInfo.src;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ServiceInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String serviceName = scanner.nextLine();
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/C", "sc qc " + serviceName);

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
