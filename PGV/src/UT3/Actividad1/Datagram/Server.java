package UT3.Actividad1.Datagram;


import java.io.IOException;
import java.net.*;
import java.util.Date;

public class Server implements Runnable{
    public static DatagramSocket serverSocket;
    public static void main(String[] args) throws IOException {
        serverSocket = new DatagramSocket(5052);
        new Thread(new Server()).start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Server initialized!");
            System.out.println("Waiting for connections...");
            while (true) {
                byte[] bytes = new byte[256];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                serverSocket.receive(datagramPacket);
                new Thread(new ClientConnection(datagramPacket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static class ClientConnection implements Runnable {
        private final DatagramPacket datagramPacket;
        ClientConnection(DatagramPacket datagramPacket) {
            this.datagramPacket = datagramPacket;
        }
        @Override
        public void run() {
            String clientIp = this.datagramPacket.getAddress().getHostAddress();
            String clientName = Thread.currentThread().getName();
            String currentDate = new Date(System.currentTimeMillis()).toString();
            System.out.println(currentDate + " - " + clientName + " connected with IP: " + clientIp);

            String message = new String(datagramPacket.getData()).trim();
            if (message.equals("hora")) {
                Date date = new Date(System.currentTimeMillis());
                byte[] bytes = date.toString().getBytes();
                DatagramPacket datagramPacketReply = new DatagramPacket(bytes,
                        bytes.length, datagramPacket.getAddress(), datagramPacket.getPort());
                try {
                    serverSocket.send(datagramPacketReply);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Reply sent");
            } else {
                System.out.println(clientName + ": " + message.trim());
                String answer = "There is no reply to the message";
                byte[] bytes = answer.getBytes();
                DatagramPacket datagramPacketReply = new DatagramPacket(bytes,
                        bytes.length, datagramPacket.getAddress(), datagramPacket.getPort());
                try {
                    serverSocket.send(datagramPacketReply);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
