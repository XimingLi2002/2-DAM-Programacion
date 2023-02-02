package UT4.Actividad2;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
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
    private static ArrayList<File> dirFiles;

    public static void main(String[] args) throws SocketException, IOException {
        ftpManager.setConnection(FTP_SERVER);
        ftpManager.setLogin(username, password);
        do {
            System.out.println("Choose an action to execute: (Enter the text between '[' y ']')");
            System.out.println("[1] Upload all files (including files in subdirectories) from a local directory to the server");
            switch (scanner.nextLine()) {
                case "1" -> {
                    //Clear the arrayList
                    dirFiles = new ArrayList<>();
                    getDirAllFiles(getLocalDir());
                    if (!dirFiles.isEmpty()) {
                        upload();
                    } else {
                        System.out.println("Could not upload files due to empty directory...");
                    }
                }
                case "2" -> {
                    System.out.print(BOLD);
                    printFormater(60, new String[]{"Nombre", "Tipo", "Tama\u00f1o", "Ruta"}); //'\u00f1' = ñ -> Para que se muestra la ñ en consola
                    System.out.print(RESET);
                    ftpManager.getAllFiles("").forEach((path, file) -> printFormater(60, new String[]{file.getName(), "Archivo", String.valueOf(file.getSize()), path}));
                    ftpManager.downloadOneFile("C:\\Users\\ikill\\Documents\\2-DAM-Programacion\\PGV\\src\\UT4\\Actividad2\\", scanner.nextLine());
                }
                default -> System.err.println("No se ha encontrado");
            }

        } while (!ftpManager.isConnected());


    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    private static void getDirAllFiles(File dir) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                getDirAllFiles(file);
            } else {
                dirFiles.add(file);
            }
        }
    }

    private static File getLocalDir() {
        boolean dirExists;
        File dir;
        System.out.println("Input a local directory absolute path: ");
        do {
            dir = new File(scanner.nextLine());
            dirExists = checkDirExists(dir);
            if (!dirExists) System.out.println("The directory does not exist! Try Again");
        } while (!dirExists);
        System.out.println("Received!");
        return dir;
    }

    private static boolean checkDirExists(File file) {
        return (file.isDirectory() && file.isAbsolute() && file.exists());
    }

    private static void upload() throws IOException {
        boolean dirCreated = false;
        do {
            System.out.println("Enter a new remote directory path: ");
            String newServerDir = scanner.nextLine();

            if (!newServerDir.isEmpty() || !newServerDir.isBlank()) {
                dirCreated = ftpManager.makeDir(newServerDir);

                if (dirCreated) {
                    System.out.println("Directory creation successful!, uploading...");
                    for (File file : dirFiles) {
                        printFormater(50, new String[]{file.getName(),
                                String.valueOf(ftpManager.uploadOneFile(file.getAbsolutePath(), "./" + newServerDir + "/" + file.getName()))});
                    }
                }else {
                    System.out.println("Directory creation failed because duplicated or missing permissions!");
                }
            }else{
                System.out.println("Directory creation failed, the name cannot be empty!");
            }
        } while (!dirCreated);
    }
}
