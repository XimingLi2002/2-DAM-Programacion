package UT2.Hilos.ApartadoB;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Hilos> hilos = new ArrayList<>();
        Hilos hilo1 = new Hilos(Thread.MIN_PRIORITY);
        Hilos hilo2 = new Hilos(Thread.NORM_PRIORITY);
        Hilos hilo3 = new Hilos(Thread.MAX_PRIORITY);

        hilos.add(hilo1);
        hilos.add(hilo2);
        hilos.add(hilo3);

        for (Hilos hilo: hilos){
            hilo.start();
            hilo.join();
        }
    }
}
