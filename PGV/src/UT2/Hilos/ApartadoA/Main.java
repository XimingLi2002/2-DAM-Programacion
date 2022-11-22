package UT2.Hilos.ApartadoA;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new MiHilo(), "Hilo "+i).start();
        }
        System.out.println("Hilo principal terminado");
    }
}