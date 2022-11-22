package UT2.SimulacroExamen;

public class PlanePuller implements Runnable, ConsoleColors {
    private Airport airport;
    private Thread thread;

    PlanePuller(Airport airport){
        this.thread = new Thread(this);
        this.airport = airport;
        this.thread.start();
    }
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) (Math.random() * 1500));
                if (airport.getNumberOfPlanes() >= 0) {
                    System.out.println(RED_BOLD_BRIGHT + airport.pullPlane().getPlaneLine() + " ya despego" + RESET);
                }else{
                    System.err.println("No hay aviones para despegar");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
