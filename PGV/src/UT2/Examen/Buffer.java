package UT2.Examen;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import UT2.Examen.ConsoleColors;

public class Buffer implements ConsoleColors {
    public static ArrayList<Object> objectList = new ArrayList<>();
    public static final int MAX_CAPACITY = 50;
    private final Semaphore semaphore = new Semaphore(1);

    public void pushObject(Object object) {
        try {
            semaphore.acquire();
            if (objectList.size() < MAX_CAPACITY) {
                objectList.add(object);
                System.out.println(GREEN_BOLD_BRIGHT + "" + RESET);
            }else{
                System.err.println(PURPLE_BOLD_BRIGHT + "" + RESET);
            }
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Object pullObject(){
        try {
            semaphore.acquire();
            if (!objectList.isEmpty()) {
                Object object = objectList.get(0);
                objectList.remove(0);
                semaphore.release();
                return object;
            }
            semaphore.release();
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSize(){
        return objectList.size();
    }
}
