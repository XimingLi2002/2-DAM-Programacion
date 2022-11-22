package UT2.RentACar;


public class Client implements Runnable, ConsoleColors {

    private final Thread thread;
    private int id;
    private Parking parking;

    public Client(int id, Parking parking) {
        this.id = id;
        this.parking = parking;
        this.thread = new Thread(this);
        this.thread.setName("Cliente " + this.id);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Car car;
                //Almacena el coche que pilla en la variable car
                if ((car = parking.takeCar()) != null) {
                    System.out.println(RED_BOLD + "El coche " + CYAN_BOLD + car.getId() +
                            RED_BOLD + " ha sido ALQUILADO por el cliente " + CYAN_BOLD + id + RESET);
                    //Duerme el hilo por x segundos
                    Thread.sleep((long) (Math.random() * 10000));
                    //Devuelve el coche alquilado
                    parking.putCar(car);
                    System.out.println(GREEN_BOLD + "El coche " + CYAN_BOLD + car.getId() +
                            GREEN_BOLD + " ha sido DEVUELTO por el cliente " + CYAN_BOLD + id +
                            YELLOW_BOLD + "\nCoches restantes: " + parking.getActualParkingCars() + RESET);
                } else {
                    System.out.println(WHITE_BOLD + "Alquilacion fallida por el cliente " + CYAN_BOLD + id + RESET);
                }
                //Duerme el hilo por x segundos
                //El cliente después de x tiempo intentará alquilar otro coche
                Thread.sleep((long) (Math.random() * 10000));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Thread getThread() {
        return this.thread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}