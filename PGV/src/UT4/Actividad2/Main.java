package UT4.Actividad2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements ConsoleColors {
    //Actividad 2. Subir archivos FTP.
    private static final String FTP_SERVER = "192.168.192.190";
    private static final String username = "pgv";
    private static final String password = "pgv";

    public static void main(String[] args) throws SocketException, IOException {
        FTP_Manager ftpManager = new FTP_Manager();
        ftpManager.setConnection(FTP_SERVER);
        ftpManager.setLoginAccount(username, password);

        ftpManager.showCurrentDirectories();
        System.out.println("Para cambiar de directorio introduzca el nombre del directorio, en caso contrario seguirás en el directorio actual");
        ftpManager.setWorkingDirectory(new Scanner(System.in).nextLine());

        ftpManager.getAllFiles("");

//        System.out.print(BOLD);
//        printFormater(60, new String[]{"Nombre", "Tipo", "Tama\u00f1o"}); //'\u00f1' = ñ -> Para que se muestra la ñ en consola
//        System.out.print(RESET);
    }



    private static void printFormater(int separation, String[] colums) {
        for (int i = 0; i < colums.length - 1; i++) {
            System.out.printf("%-" + separation + "s", colums[i]);
        }
        System.out.println(colums[colums.length - 1]);
    }
}
