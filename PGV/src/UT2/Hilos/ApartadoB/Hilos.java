package UT2.Hilos.ApartadoB;

public class Hilos extends Thread{
    private final Thread thread;
    Hilos(int priority){
        thread = new Thread(this);
        thread.setPriority(priority);
    }
    @Override
    public void run(){
        super.run();
        for (int i = 1; i <= 10; i++) {
            System.out.println(thread.getName() + " prioridad " + thread.getPriority());
            if (thread.getPriority() == Thread.MIN_PRIORITY && i == 5) thread.setPriority(Thread.MAX_PRIORITY);
            else if (thread.getPriority() == Thread.MAX_PRIORITY && i == 5) thread.setPriority(Thread.MIN_PRIORITY);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
