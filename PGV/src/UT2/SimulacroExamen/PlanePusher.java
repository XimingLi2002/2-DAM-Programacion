package UT2.SimulacroExamen;

import java.util.Random;

public class PlanePusher implements Runnable {
    private static final String[] planeLines = new String[]{"Iberia", "Air Berlin", "Binter", "Ryanair", "Vueling", "Spanair", "Lufthansa", "Condor", "SwissAir", "Canaryfly"};
//    private static final String[] planeLines = new String[]{"Iberia", "Air Berlin","Binter","Ryanair"};
    private Airport airport;
    private Thread thread;

    PlanePusher(Airport airport) {
        this.thread = new Thread(this);
        this.airport = airport;
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int check = 0;
                Thread.sleep((long) (Math.random() * 100));

                //crea un nuevo avion con una aerolinea aleatoria
                Plane plane = new Plane(getRandomPlaneLine());

                //Comprueba cuantos aviones hay de x aerolinea
                for (int i = 0; i < airport.getNumberOfPlanes(); i++) {
                    if (airport.getPlanes().get(i).getPlaneLine().contains(plane.getPlaneLine())) check++;
                }

                //en caso de que haya menos de 10 aviones de x aerolinea el vuelo se pondra a la cola
                if (check < 10) airport.pushPlane(plane);
                //en caso contrario no se pondra a la cola y mostrara un mensaje
                else System.err.println("No se pudo autorizar el vuelo a " + plane.getPlaneLine());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomPlaneLine() {
        return planeLines[new Random().nextInt(planeLines.length)];
    }
}
