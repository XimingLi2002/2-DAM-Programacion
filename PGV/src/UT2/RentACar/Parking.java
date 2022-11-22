package UT2.RentACar;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Parking {
    private final ArrayList<Car> parking = new ArrayList<>();
    public static final int MAX_CAPACITY = 20;
    private final Semaphore semaphore = new Semaphore(1);

    Parking() {
        fillParking();
    }

    private void fillParking() {
        //Rellena el parking en este caso con 20 coches
        for (int i = 0; i < MAX_CAPACITY; i++) {
            parking.add(new Car(i));
        }
    }

    public void putCar(Car car) {
        try {
            semaphore.acquire();
            parking.add(car);
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Car takeCar(){
        try {
            if (!parking.isEmpty()) {
                semaphore.acquire();
                //Selecciona un coche del parking
                Car car = parking.get(0);
                //Elimina el coche seleccionado del arrayList
                //Al eliminar la posicion 0 el resto de componentes del arrayList se rodarán un hueco
                //es decir si tenemos 3 objetos 0,1,2 la posición 1 y 2 pasará a 0,1
                parking.remove(0);
                semaphore.release();
                //Devuelve el coche seleccionado
                return car;
            }
            semaphore.release();
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public int getActualParkingCars(){
        return parking.size();
    }
}
