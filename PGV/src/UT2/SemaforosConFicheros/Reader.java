package UT2.SemaforosConFicheros;

import java.io.IOException;

//Consumer
public class Reader implements Runnable, ConsoleColors {

    private final Thread thread;
    private final FileBuffer fileBuffer;

    Reader(FileBuffer fileBuffer, String threadName) {
        this.thread = new Thread(this);
        this.fileBuffer = fileBuffer;
        this.thread.setName(threadName);
    }

    @Override
    public void run() {
        String line;
        while (true) {
            try {
                Thread.sleep((long) (Math.random() * 10000));
                line = fileBuffer.readFile();
                if (line != null) {
                    System.out.println(GREEN_BOLD + thread.getName() + YELLOW_BOLD + " ha leido: " + CYAN_BOLD + line + RESET);
                } else {
                    System.out.println(RED_BOLD + thread.getName() + " no pudo leer" + RESET);
                }
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Thread getThread() {
        return thread;
    }

    public FileBuffer getFileBuffer() {
        return fileBuffer;
    }


}