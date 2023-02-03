package UT4.ActividadEvaluacion;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import static UT4.ActividadEvaluacion.FTP_Manager.printFormater;

public class Main implements ConsoleColors {

    private static final String FTP_SERVER = "192.168.192.75";
    private static final String username = "pgv";
    private static final String password = "pgv";
    private final static FTP_Manager ftpManager = new FTP_Manager();
    private final static Scanner scanner = new Scanner(System.in);
    private static ArrayList<File> dirFiles;

    public static void main(String[] args) throws SocketException, IOException {
        ftpManager.setConnection(FTP_SERVER, username, password);
        ftpManager.checkConnection();

        ArrayList<FTPFile> serverMainDirFiles = new ArrayList<>();
        ArrayList<FTPFile> serverMainFiles = new ArrayList<>();

        //ftpManager.getFiles(""); --> Devuelve todos los archivos del directorio principal
        System.out.println(RED_BOLD_BRIGHT + "TODO LO QUE HAY EN EL DIRECTORIO PRINCIPAL" + RESET);

        System.out.print(BOLD);
        printFormater(40, new String[]{"Nombre", "Tama\u00f1o", "Ruta"}); //'\u00f1' = ñ -> Para que se muestra la ñ en consola
        System.out.print(RESET);

        for (FTPFile file : ftpManager.getFiles("")) {
            if (file.isDirectory()) serverMainDirFiles.add(file);
            else serverMainFiles.add(file);
            //Si quieres ver detalladamente la informacion del archivo
            printFormater(40, new String[]{file.getName(), String.valueOf(file.getSize()), ftpManager.getWorkingDirectory() + file.getName()});
        }

        System.out.println(BLUE_BOLD_BRIGHT + "En el directorio principal hay " + serverMainDirFiles.size() + " directorios y " + serverMainFiles.size() + " archivos" + RESET);


        System.out.println("\n" + RED_BOLD_BRIGHT + "INFORMACION DE TODOS LOS ARCHIVOS" + RESET);

        ftpManager.resetCountAllDirectories();
        HashMap<String, FTPFile> fileList = ftpManager.getAllFiles("");

        System.out.print(BOLD);
        printFormater(60, new String[]{"Nombre", "Tama\u00f1o", "Ruta"}); //'\u00f1' = ñ -> Para que se muestra la ñ en consola
        System.out.print(RESET);

        fileList.forEach((path, file) -> printFormater(60, new String[]{file.getName(), String.valueOf(file.getSize()), path}));

        System.out.println(BLUE_BOLD_BRIGHT + "En total hay " + ftpManager.getCountAllDirectories() + " directorios y " + fileList.size() + " archivos" + RESET);


        //Solo deberia haber un directorio con mi nombre
        serverMainDirFiles.forEach(it -> {
            if (it.getName().contains("Ximing")) {
                try {
                    ftpManager.setWorkingDirectory("Ximing");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(GREEN_BOLD_BRIGHT + "\nDirectorio de trabajo cambiado" + RESET);
            }
        });

        FTPFile myDirFiles[] = ftpManager.getFiles();
        for (FTPFile file : myDirFiles) {
            if (ftpManager.downloadOneFile("C:\\Users\\ikill\\Documents\\2-DAM-Programacion\\PGV\\src\\UT4\\ActividadEvaluacion\\Downloads", file.getName())) {
                System.out.println(YELLOW_BOLD_BRIGHT + file.getName() + " descargado!" + RESET);
            } else {
                System.out.println(RED_BOLD_BRIGHT + file.getName() + " ha fallado al descargarse!" + RESET);
            }

        }
    }
}
