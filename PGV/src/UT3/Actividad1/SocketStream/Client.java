package UT3.Actividad1.SocketStream;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Scanner scanner = new Scanner(System.in);
    private OutputStream clientWriter;

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }
    @Override
    public void run() {
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",5050);
            Socket socket = new Socket();
            socket.connect(inetSocketAddress);

            System.out.println("You are connected!");

            clientWriter = socket.getOutputStream();

            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("EXIT")) {
                    clientWriter.write("exit".getBytes());
                    socket.close();
                    scanner.close();
                    break;
                } else if (!input.equals("")) {
                    clientWriter.write(input.getBytes());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}