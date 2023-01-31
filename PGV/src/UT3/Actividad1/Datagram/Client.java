package UT3.Actividad1.Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client implements Runnable {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }

    @Override
    public void run() {
        try {
            System.out.println("You are connected!");
            InetAddress serverIp = InetAddress.getByName("localhost");
            DatagramSocket clientSocket = new DatagramSocket();

            String input = scanner.nextLine();
            DatagramPacket sendPacket = new DatagramPacket(input.getBytes(), input.getBytes().length, serverIp, 5052);
            clientSocket.send(sendPacket);

            byte[] bytes = new byte[64];
            //Cuando recibes un mensaje no es necesario la ip ni el puerto
            DatagramPacket receivePacket = new DatagramPacket(bytes, bytes.length);
            clientSocket.receive(receivePacket);
            System.out.println("Received message: " + new String(bytes));
            scanner.close();
            clientSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
