package UT2.RentACar;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Parking parking = new Parking();

        int numClients = new Random().nextInt(10, 30); //min max
        System.out.println("Cantidad de clientes: " + numClients);

        for (int i = 0; i < numClients; i++) {
            new Client(i, parking).getThread().start();
        }
    }

}
