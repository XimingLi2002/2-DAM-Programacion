package UT2.SemaforosConFicheros;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FileBuffer {


    private Semaphore semaphore = new Semaphore(1);
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ArrayList<String> linesList;

    FileBuffer() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("./src/UT2/SemaforosConFicheros/file.txt"));
        bufferedWriter = new BufferedWriter(new FileWriter("./src/UT2/SemaforosConFicheros/file.txt",true));
        linesList = new ArrayList<>();
    }

    public void writeFile(String line) {
        try {
            semaphore.acquire();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            semaphore.release();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFile() throws IOException, InterruptedException {
        String line;
        semaphore.acquire();
        refreshLines();
        if(!linesList.isEmpty()){
            line = linesList.get(0);
            linesList.remove(0);
            semaphore.release();
            return line;
        }
        semaphore.release();
        return null;
    }

    public void refreshLines() throws IOException {
        String line;
        while ((line = this.bufferedReader.readLine()) != null) {
            linesList.add(line);
        }
    }
}
