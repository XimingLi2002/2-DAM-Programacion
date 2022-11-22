package UT2.SemaforosConFicheros;

import java.io.BufferedWriter;

//Producer
public class Writer implements Runnable, ConsoleColors {
    private int contador = 1;
    private final Thread thread;
    private final FileBuffer fileBuffer;

    Writer(FileBuffer fileBuffer, String threadName) {
        this.thread = new Thread(this);
        this.fileBuffer = fileBuffer;
        this.thread.setName(threadName);
    }

    @Override
    public void run() {
        do{
            try {
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Cada escritor escribirá 3 veces
            if (contador <= 3) {
                fileBuffer.writeFile(thread.getName() + " ha escrito: " + contador++);
            } else {
                //cuando termine de escribir se interrumpira el hilo y se mostrara un mensaje por consola
                thread.interrupt();
            }
        }while(!thread.isInterrupted());
        System.out.println(PURPLE_BOLD + thread.getName() + " ha finalizado su escritura" + RESET);
    }

    public Thread getThread() {
        return thread;
    }

    public FileBuffer getFileBuffer() {
        return fileBuffer;
    }
}