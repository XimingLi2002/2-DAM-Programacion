package UT2.ExamenVersionFichero;

import java.io.*;
import java.util.concurrent.Semaphore;

public class FileBuffer implements ConsoleColors {
    private final Semaphore semaphore = new Semaphore(1);
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    FileBuffer() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(""));
        bufferedWriter = new BufferedWriter(new FileWriter("",true));
    }
    public void pushObject(Object object) {
        try {
            semaphore.acquire();

            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String pullObject(){
        try {
            semaphore.acquire();

            if (bufferedReader.readLine() != null) {
                return "";
            }
            semaphore.release();
            return null;
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
