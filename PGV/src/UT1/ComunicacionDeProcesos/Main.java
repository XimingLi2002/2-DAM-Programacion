package UT1.ComunicacionDeProcesos;

import java.io.*;

public class Main {
    public static void main(String[] args) {
//        1) Escribe un programa en Java que lance un proceso que escriba bytes desde el proceso padre
//        hacia el proceso hijo y recoge los bytes y los pasa a mayúsculas y CONCURRENTEMENTE lanza un
//        proceso que ejecute el reproductor de música wmplayer.exe reproduciendo una canción.
        Apartado1();
        Apartado2();
    }

    private static void Apartado1() {
        String path = "E:\\Programacion\\PGV\\src\\UT1\\ComunicacionDeProcesos\\Apartado1.jar";
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", path);
            Process process = startProcess(processBuilder);
            OutputStream("Hola Mundo", process);
            InputStream(process);
    }
    private static void Apartado2() {
        String path = "E:\\Programacion\\PGV\\src\\UT1\\ComunicacionDeProcesos\\Tasklist.jar";
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", path);
        Process process = startProcess(processBuilder);

        try {
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line, serviceLine;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("svchost.exe")) {
                    bufferedReader.readLine();
                    serviceLine = bufferedReader.readLine();
                    serviceLine = serviceLine.substring(serviceLine.indexOf(":") + 1).trim();
//                    System.out.println(serviceLine);
                    serviceInfo(serviceLine);
                }
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void serviceInfo(String serviceName) throws IOException {
        String path = "E:\\Programacion\\PGV\\src\\UT1\\ComunicacionDeProcesos\\ServiceInfo.jar";
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", path);
        Process process = startProcess(processBuilder);
        OutputStream(serviceName, process);
        InputStream(process);
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
    private static Process startProcess(ProcessBuilder processBuilder){
        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process;
    }
}
