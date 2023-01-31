package UT4.Actividad2;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.util.ArrayList;

public class FTP_Manager {
    private FTPClient ftpClient;
    public FTP_Manager(){
        ftpClient = new FTPClient();
    }
    protected void setConnection(String ftpServer) throws IOException {
        ftpClient.connect(ftpServer);
        ftpClient.enterLocalPassiveMode();
        System.out.println(ftpClient.getReplyString());

        //Check if the connection has been refused
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
            ftpClient.disconnect();
            System.out.println("Connection declined!");
            System.exit(0);
        }
    }
    protected void setLoginAccount(String username, String password) throws IOException {
        if (ftpClient.login(username,password)){
            System.out.println("Login successful!");
        }else{
            System.out.printf("Login failed!");
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

    protected void showCurrentDirectories() throws IOException{
        FTPFile[] currentDirectories = ftpClient.listDirectories();
        for(FTPFile directory : currentDirectories){
            System.out.println(directory.getName());
        }
    }

    protected void setWorkingDirectory(String path) throws IOException {
        ftpClient.changeWorkingDirectory("/"+ path);
    }

    protected ArrayList<FTPFile> getAllFiles(String path) throws IOException {
        ArrayList<FTPFile> fileList = new ArrayList<>();
        ArrayList<String> directories = new ArrayList<>();
        do {
            FTPFile[] files = this.getFiles(path);
            for (FTPFile file : files) {
                if (file.isDirectory()) {
                    directories.add(path + "/" + file.getName());
                } else {
                    fileList.add(file);
                }
            }
        }while(!directories.isEmpty());
        return fileList;
    }

}
