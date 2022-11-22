package UT2.ExamenVersionFichero;


public class Producer implements Runnable, ConsoleColors {
    private FileBuffer fileBuffer;
    private Thread thread;

    Producer(FileBuffer fileBuffer) {
        this.thread = new Thread(this);
        this.fileBuffer = fileBuffer;
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 500));

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
