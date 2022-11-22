package UT2.Examen;

public class Producer implements Runnable,ConsoleColors{
    private Buffer buffer;
    private Thread thread;

    Producer(Buffer buffer) {
        this.thread = new Thread(this);
        this.buffer = buffer;
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 500));

                Object object = new Object("Nombre"); //TODO nombre
                buffer.pushObject(object);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
