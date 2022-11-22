package UT2.EjercicioClase;

public class MyBuffer {

    private int[] arrayImpares;
    int productor = 0;
    int consumidor = 0;
    public MyBuffer(int num_items) {
        this.arrayImpares = new int[num_items];
    }

    public synchronized boolean cargar(int valor) {
        if (productor< arrayImpares.length-1){
            productor++;
            arrayImpares[productor] = valor;
            System.out.println("El hilo " + Thread.currentThread().getName()+ " ha cargado el valor " + valor);
            return true;
        }
        return false;
    }

    public synchronized void leer(){
        if (productor > consumidor){
            System.out.println("El hilo " + Thread.currentThread().getName()+ " ha leido el valor " + arrayImpares[consumidor]);
            consumidor++;
        }
    }
}
