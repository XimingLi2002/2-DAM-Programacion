package UT4.Actividad1;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

public class ClientFTP {
    private static final String FTP_SERVER = "192.168.10.1";
    private static final String username = "pgv";
    private static final String password = "pgv";
    public static void main(String[] args) throws SocketException, IOException {
        FTPClient client = new FTPClient();

        client.connect(FTP_SERVER);
        client.enterLocalPassiveMode();
        System.out.println(client.getReplyString());

        //Check if the connection has been refused
        if (!FTPReply.isPositiveCompletion(client.getReplyCode())){
            client.disconnect();
            System.out.println("Connection declined!");
            System.exit(0);
        }

        //login with anonymous
        client.login(username,password);
        //Read current directory information

        String currentDir = client.printWorkingDirectory();
        System.out.println("Current directory: " + currentDir);

        FTPFile[] files = client.listFiles();
        System.out.println("Number of files: " + files.length);
        for (FTPFile file: files){
            System.out.printf("%-80s",file.getName());
            System.out.println(file.getSize());
        }

        //To download files, we indicate the name with which we recorded it locally
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("./src/UT4/FTPTest.pdf"));
        System.out.println(files[files.length-2].getName());
        if (client.retrieveFile(files[files.length-2].getName(), bufferedOutputStream)){
            System.out.println("File recovered");
        }else {
            System.out.println("Download failed");
        }
        bufferedOutputStream.close();

        client.disconnect();
        System.out.println("Connection ended successfully!");
    }

}
