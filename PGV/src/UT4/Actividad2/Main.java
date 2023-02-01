package UT4.Actividad2;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static UT4.Actividad2.FTP_Manager.printFormater;

//Actividad 2. Subir archivos FTP.
public class Main implements ConsoleColors {

    //IP de mi PC --> Aula R02 : "192.168.192.190"   Wifi Compartida de Peng: "192.168.10.1"
    //IP de Rita --> "192.168.192.75"
    private static final String FTP_SERVER = "192.168.192.190";
    private static final String username = "pgv";
    private static final String password = "pgv";
        private final static FTP_Manager ftpManager = new FTP_Manager();
    private final static Scanner scanner = new Scanner(System.in);
    ;

    public static void main(String[] args) throws SocketException, IOException {
        ftpManager.setConnection(FTP_SERVER);
        ftpManager.setLogin(username, password);
        do {
        System.out.println("Choose an action to execute: (Enter the text between '[' y ']')");
        System.out.println("[0] Mostrar todos los ficheros de un directorio, incluido los subdirectorios que contenga");
        System.out.println("[1] Subir un directorio local al servidor");
        switch (scanner.nextLine()) {
            case "1":
                //Clear the arrayList
                dirFiles = new ArrayList<>();
                getDirAllFiles(getLocalDir());
                if (!dirFiles.isEmpty())
                break;
            default:
                System.err.println("No se ha encontrado");
        }

        }while (!ftpManager.isConnected());

        System.out.print(BOLD);
        printFormater(60, new String[]{"Nombre", "Tipo", "Tama\u00f1o"}); //'\u00f1' = ñ -> Para que se muestra la ñ en consola
        System.out.print(RESET);

        ftpManager.getAllFiles("").forEach(it -> printFormater(60, new String[]{it.getName(), "Archivo", String.valueOf(it.getSize())}));


    }

    private static File getLocalDir() {
        File dir;
        boolean checkDirExists;
        do {
            System.out.println("Input a local directory absolute path: ");
            dir = new File(scanner.nextLine());
            checkDirExists = checkDirExists(dir);
            if (checkDirExists) {
                System.out.println("Received!");
            } else {
                System.out.println("The directory does not exist! Try Again");
            }
        } while (!checkDirExists);
        return dir;
    }

    private static boolean checkDirExists(File file) {
        return (file.isDirectory() && file.isAbsolute() && file.exists());
    }

    private static ArrayList<File> dirFiles;

    private static void getDirAllFiles(File dir) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                getDirAllFiles(file);
            } else {
                dirFiles.add(file);
            }
        }
    }

    private static void upload() {
        if (dirFiles.isEmpty()) {

            boolean dirCreated = false;

            do {
                System.out.println("\nEnter a new remote directory name/path (Ex: dir2 | dir2/subdir2 ): ");
                String newDir = scanner.nextLine();

                if (!newDir.equals("")) {
                    dirCreated = ftpManager.makeDir(newDir);

                    if (dirCreated) {
                        System.out.println("Dir " + newDir + " created!");
                        System.out.println("\n||==========||Upload status||==========||\n");
                        int uploadCount = 0;

                        for (File file : dirFiles) {
                            boolean uploaded = ftpManager.uploadOneFile(file.getAbsolutePath(), "./" + newDir + "/" + file.getName());
                            System.out.println(file.getName() + " uploaded: " + uploaded);

                            if (uploaded) uploadCount++;
                        }

                        System.out.println("\nUPLOADED FILES: " + uploadCount);
                    }
                }
            } while (!dirCreated);

        } else {
            System.out.println("Empty directory, nothing uploaded...");
        }
    }


}
