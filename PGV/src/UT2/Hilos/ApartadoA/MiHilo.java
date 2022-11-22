package UT2.Hilos.ApartadoA;

import UT2.Tests.Test1;

public class MiHilo implements Runnable{
    private final Thread thread;

    public MiHilo() {
        thread = new Thread(this);
    }

    @Override
    public void run() {
        //for que imprime 5 veces el nombre del thread
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(thread.getName() + " terminado");
    }
}
