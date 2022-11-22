package UT2.a;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Consumer(buffer);
        new Producer(buffer);
    }
}
