package UT1.ProgramacionMultiproceso;

import java.io.IOException;

public class MainRuntime {
    private static Runtime runtime;

    public static void main(String[] args) {
        runtime = Runtime.getRuntime();

        //Por comandos del Sistema Operativo
        executeProcess("notepad.exe");

        //Por comandos almacenados en un fichero por lotes, extensión .bat
        executeProcess("cmd /C start E:\\Programacion\\PGV\\src\\UT1\\ProgramacionMultiproceso\\comandos.bat");

        //Un programa .jar
        executeProcess("java -jar E:\\Programacion\\PGV\\src\\UT1\\ProgramacionMultiproceso\\micalculadora.jar");
    }

    private static void executeProcess(String command) {
        try {
            Process process = runtime.exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}