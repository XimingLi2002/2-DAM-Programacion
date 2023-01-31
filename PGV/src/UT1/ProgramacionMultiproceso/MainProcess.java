package UT1.ProgramacionMultiproceso;

import java.io.IOException;

public class MainProcess {
    private static ProcessBuilder processBuilder;
    public static void main(String[] args) {
        //Por comandos del S0
        //processBuilder = new ProcessBuilder().command("notepad.exe");
        //Es lo mismo que lo anterior
        processBuilder = new ProcessBuilder("notepad.exe");
        executeProcess(processBuilder);

        //Por comandos almacenados en un fichero por lotes, extensión .bat
        processBuilder = new ProcessBuilder("cmd","/C","start","E:\\Programacion\\PGV\\src\\UT1\\ProgramacionMultiproceso\\comandos.bat");
        executeProcess(processBuilder);

        //Un programa .jar
        processBuilder = new ProcessBuilder("java","-jar","E:\\Programacion\\PGV\\src\\UT1\\ProgramacionMultiproceso\\micalculadora.jar");
        executeProcess(processBuilder);
    }

    private static void executeProcess(ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();
            //Sirve para que el siguiente proceso espere a que se cierre el actual/anterior
            //process.waitFor();
            //Sirve para que nos imprima la información del proceso
            //System.out.println(process.info());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
