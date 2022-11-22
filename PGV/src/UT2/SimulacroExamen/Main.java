package UT2.SimulacroExamen;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport();
        new PlanePusher(airport);
        new PlanePuller(airport);
    }
}
