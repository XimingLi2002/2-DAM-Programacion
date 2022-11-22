package UT2.Tests;

public class Test1 extends Thread {
    public static void main(String[] args) {
        Thread thread;
        int num = 5;
        for (int i = 0; i < num; i++) {
            thread = new Thread(new Test1(), "hilo " + i);
            thread.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Soy el hilo: " + Thread.currentThread().getName() + " iteración: " + i);
        }
    }
}
