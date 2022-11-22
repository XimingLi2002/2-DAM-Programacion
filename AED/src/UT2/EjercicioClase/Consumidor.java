package UT2.EjercicioClase;

public class Consumidor implements Runnable{

    private final Thread thread = new Thread(this);
    private MyBuffer buffer;

    public Consumidor(MyBuffer buffer) {
        this.buffer=buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.leer();
            try {
                Thread.sleep((int) (Math.random() * 600));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Thread getThread() {
        return thread;
    }
}
