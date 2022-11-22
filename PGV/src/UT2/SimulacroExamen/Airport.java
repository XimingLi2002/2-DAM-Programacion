package UT2.SimulacroExamen;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Airport {
    public static ArrayList<Plane> planes = new ArrayList<>();
    public static final int MAX_CAPACITY = 50;
    private final Semaphore semaphore = new Semaphore(1);

    public void pushPlane(Plane plane) {
        try {
            semaphore.acquire();
            if (planes.size() < MAX_CAPACITY) {
                planes.add(plane);
                System.out.println(plane.getPlaneLine() + " pide desplegar");
                System.out.println("El vuelo " + plane.getPlaneLine() + " se encuentra en la pista en la posicion " + getNumberOfPlanes());
            }else{
                System.err.println("Aeropuerto lleno");
            }
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Plane pullPlane(){
        try {
            semaphore.acquire();
            if (!planes.isEmpty()) {
                Plane plane = planes.get(0);
                planes.remove(0);
                semaphore.release();
                return plane;
            }
            semaphore.release();
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public int getNumberOfPlanes(){
        return planes.size();
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

}
