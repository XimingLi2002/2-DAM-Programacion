package UT3.Actividad2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Chat implements Runnable {
    public String username;
    @Override
    public void run() {
        try {
            MulticastSocket userSocket = new MulticastSocket(5051);
            InetAddress server = InetAddress.getByName("235.1.1.235");

            System.out.print("Introduce your username: ");
            username = new Scanner(System.in).nextLine();

            userSocket.joinGroup(server);
            new Thread(new Connection(userSocket, server)).start();

            String[] message;
            while (true) {
                byte[] bytes = new byte[128];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                userSocket.receive(packet);

                String packetData = new String(packet.getData()).trim();
                message = packetData.split(":");


                if (message[1].trim().equalsIgnoreCase("exit") && packetData.contains(username)) {
                    System.out.println(username + " has disconnected!");
                    break;
                }

                if (!(message[0].equals(username))) {
                    System.out.println(packetData);
                }
            }

            userSocket.leaveGroup(server);
            userSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Connection implements Runnable {
        private MulticastSocket multicastSocket;
        private InetAddress inetAddress;
        Connection(MulticastSocket multicastSocket, InetAddress inetAddress) {
            this.multicastSocket = multicastSocket;
            this.inetAddress = inetAddress;
        }

        @Override
        public void run() {
            try {
                sendMessage("Server: " + username + " has connected!");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String receivedMessage;
                do {
                    receivedMessage = bufferedReader.readLine();
                    sendMessage(username + ": "+ receivedMessage);
                } while (!receivedMessage.trim().equalsIgnoreCase("exit"));
            } catch (IOException ignored) {
            }
        }

        private void sendMessage(String message){
            try {
                multicastSocket.send(new DatagramPacket(message.getBytes(), message.length(), inetAddress, multicastSocket.getLocalPort()));
            } catch (IOException ignored) {
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new Chat()).start();
    }
}