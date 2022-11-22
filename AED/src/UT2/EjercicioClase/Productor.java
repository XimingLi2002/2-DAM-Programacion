package UT2.EjercicioClase;

public class Productor extends Thread {

    private final Thread thread = new Thread(this);
    private MyBuffer buffer;

    public Productor(MyBuffer buffer) {
        this.buffer=buffer;
    }

    public void run() {
        boolean exito = true;
        int i = 1;
        while(true){
            exito = buffer.cargar(i);
            if (exito){
                i += 2;
                try {
                    Thread.sleep((int) (Math.random() * 500));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Thread getThread() {
        return thread;
    }
}
