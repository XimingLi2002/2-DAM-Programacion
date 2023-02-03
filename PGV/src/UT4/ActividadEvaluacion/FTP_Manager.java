package UT4.ActividadEvaluacion;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FTP_Manager {
    private FTPClient ftpClient;

    public FTP_Manager() {
        ftpClient = new FTPClient();
    }

    protected void setConnection(String ftpServer, String username, String password) throws IOException {
        ftpClient.connect(ftpServer);
        if (ftpClient.login(username, password)) System.out.println("Login successful!");
        else System.out.print("Login failed!");
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setAutodetectUTF8(true);
        System.out.println(ftpClient.getReplyString());
    }


    protected void checkConnection() throws IOException {
        //Check if the connection has been refused
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            System.out.println("Connection declined!");
            System.exit(0);
        }
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

    private HashMap<String, FTPFile> fileList = new HashMap<>();
    private Integer countAllDirectories = 0;
    private static ArrayList<String> directories = new ArrayList<>();

    protected HashMap<String, FTPFile> getAllFiles(String path) throws IOException {
        do {
            FTPFile[] files = this.getFiles(path);
            for (FTPFile file : files) {
                /*
                if (file.isDirectory()) printFormater(60, new String[]{file.getName(), "Carpeta de archivos", String.valueOf(file.getSize())});
                else printFormater(60, new String[]{file.getName(), "Archivo", String.valueOf(file.getSize())});
                 */
                if (file.isDirectory()) {
                    directories.add(path + "/" + file.getName());
                    countAllDirectories++;
                } else {
                    fileList.put(path + "/"+file.getName(),file);
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
    protected void resetCountAllDirectories(){
        countAllDirectories = 0;
    }
    protected Integer getCountAllDirectories(){
        return countAllDirectories;
    }


    public boolean makeDir(String pathName) throws IOException {
            return ftpClient.makeDirectory(pathName);
    }

    public boolean uploadOneFile(String localPath,String destinationFilePath) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(localPath));
            return ftpClient.storeFile(destinationFilePath, bufferedInputStream);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean downloadOneFile(String destinationLocalPath,String remoteFilePath) {
        try {
            BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(destinationLocalPath+"/"+remoteFilePath.split("/")[remoteFilePath.split("/").length-1]));
            return ftpClient.retrieveFile(remoteFilePath, bo);
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

    protected static void printFormater(int separation, String[] colums) {
        for (int i = 0; i < colums.length - 1; i++) {
            System.out.printf("%-" + separation + "s", colums[i]);
        }
        System.out.println(colums[colums.length - 1]);
    }
}
