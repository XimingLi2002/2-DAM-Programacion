package UT2.Examen;

import UT2.SimulacroExamen.Airport;

public class Consumer implements Runnable, ConsoleColors{
    private Buffer buffer;
    private Thread thread;

    Consumer(Buffer buffer){
        this.thread = new Thread(this);
        this.buffer = buffer;
        this.thread.start();
    }
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 1000));
                if (buffer.getSize() >= 0) {
                    buffer.pullObject(); //TODO quita un objeto de la lista
                    System.out.println(RED_BOLD_BRIGHT + " " + RESET);
                }else{
                    System.out.println(PURPLE_BOLD_BRIGHT + " " + RESET);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
