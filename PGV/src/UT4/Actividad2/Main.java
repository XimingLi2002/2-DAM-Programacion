package UT4.Actividad2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

import static UT4.Actividad2.FTP_Manager.printFormater;

public class Main implements ConsoleColors {
    //Actividad 2. Subir archivos FTP.
    private static final String FTP_SERVER = "192.168.192.190";
    //private static final String FTP_SERVER = "192.168.10.1";

    //RITA
    //private static final String FTP_SERVER = "192.168.192.75";
    private static final String username = "pgv";
    private static final String password = "pgv";

    public static void main(String[] args) throws SocketException, IOException {
        FTP_Manager ftpManager = new FTP_Manager();
        ftpManager.setConnection(FTP_SERVER);
        ftpManager.setLoginAccount(username, password);

        //ftpManager.showCurrentDirectories();
        //System.out.println("Para cambiar de directorio introduzca el nombre del directorio, en caso contrario seguirás en el directorio actual");
        //ftpManager.setWorkingDirectory(new Scanner(System.in).nextLine());

        System.out.print(BOLD);
        printFormater(60, new String[]{"Nombre", "Tipo", "Tama\u00f1o"}); //'\u00f1' = ñ -> Para que se muestra la ñ en consola
        System.out.print(RESET);
        //ftpManager.getAllFiles("");
        ftpManager.getAllFiles("").forEach(it -> printFormater(60, new String[]{it.getName(), "Archivo", String.valueOf(it.getSize())}));


    }

}
