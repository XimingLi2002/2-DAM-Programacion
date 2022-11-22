package UT2.SemaforosConFicheros;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        FileBuffer fileBuffer = new FileBuffer();
        for (int i = 1; i <= 5; i++) {
            Writer writer = new Writer(fileBuffer, "Writer " + i);
            writer.getThread().start();
        }

        for (int i = 1; i <= 3; i++) {
            Reader reader = new Reader(fileBuffer, "Reader " + i);
            reader.getThread().start();
        }
    }
}
