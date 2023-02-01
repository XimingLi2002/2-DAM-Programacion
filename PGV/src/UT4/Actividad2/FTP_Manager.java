package UT4.Actividad2;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FTP_Manager {
    private FTPClient ftpClient;

    public FTP_Manager() {
        ftpClient = new FTPClient();
    }

    protected void setConnection(String ftpServer) throws IOException {
        ftpClient.connect(ftpServer);
        ftpClient.enterLocalPassiveMode();
        System.out.println(ftpClient.getReplyString());

        //Check if the connection has been refused
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            System.out.println("Connection declined!");
            System.exit(0);
        }
    }

    protected void setLogin(String username, String password) throws IOException {
        if (ftpClient.login(username, password)) System.out.println("Login successful!");
        else System.out.printf("Login failed!");
    }

    protected String getWorkingDirectory() throws IOException {
        return ftpClient.printWorkingDirectory();
    }

    protected FTPFile[] getFiles() throws IOException {
        return ftpClient.listFiles();
    }

    protected FTPFile[] getFiles(String path) throws IOException {
        return ftpClient.listFiles(path);
    }

    protected FTPFile[] getDirectories() throws IOException {
        return ftpClient.listDirectories();
    }

    protected void showCurrentDirectories() throws IOException {
        FTPFile[] currentDirectories = ftpClient.listDirectories();
        for (FTPFile directory : currentDirectories) {
            System.out.println(directory.getName());
        }
    }

    protected void setWorkingDirectory(String path) throws IOException {
        ftpClient.changeWorkingDirectory("/" + path);
    }

    private static ArrayList<FTPFile> fileList = new ArrayList<>();
    private static ArrayList<String> directories = new ArrayList<>();

    protected ArrayList<FTPFile> getAllFiles(String path) throws IOException {
        do {
            FTPFile[] files = this.getFiles(path);
            for (FTPFile file : files) {
                /*
                if (file.isDirectory()) printFormater(60, new String[]{file.getName(), "Carpeta de archivos", String.valueOf(file.getSize())});
                else printFormater(60, new String[]{file.getName(), "Archivo", String.valueOf(file.getSize())});
                 */
                if (file.isDirectory()) {
                    directories.add(path + "/" + file.getName());
                } else {
                    fileList.add(file);
                }
            }
            if (!directories.isEmpty()) {
                String directory = directories.get(0);
                directories.remove(0);
                getAllFiles(directory);
            }
        } while (!directories.isEmpty());
        return fileList;
    }

    protected static void printFormater(int separation, String[] colums) {
        for (int i = 0; i < colums.length - 1; i++) {
            System.out.printf("%-" + separation + "s", colums[i]);
        }
        System.out.println(colums[colums.length - 1]);
    }
    public boolean makeDir(String pathName) {
        try {
            return  ftpClient.makeDirectory(pathName);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean uploadOneFile(String localPath,String destinationFilePath) {
        try {
            BufferedInputStream bi = new BufferedInputStream(new FileInputStream(localPath));

            return ftpClient.storeFile(destinationFilePath, bi);
        } catch (IOException e) {
            return false;
        }
    }

    protected boolean isConnected(){
        return ftpClient.isConnected();
    }
    protected void closeConnection(){
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            System.err.println("Disconnection failed!");
        }
    }
}
