package UT3.SimulacroExamen;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client extends Thread {
    private static Connection connection;

    public static void main(String[] args) throws IOException {
        connection = new Connection();

        System.out.printf("Introduce tu nombre de usuario: ");
        String username = new Scanner(System.in).nextLine();

        SocketAddress socketAddress = new InetSocketAddress(connection.getInetAddress(), connection.getMulticastPort());
        MulticastSocket multicastSocket = new MulticastSocket(connection.getMulticastPort());
        multicastSocket.joinGroup(socketAddress, null);

        String connectMessage = username + "[" + InetAddress.getLocalHost() + "] se ha unido al grupo";

        DatagramPacket connectionPacketOut = new DatagramPacket(connectMessage.getBytes(), connectMessage.length(), connection.getInetAddress(), connection.getMulticastPort());
        multicastSocket.send(connectionPacketOut);

        String message = "";

        while (true) {
            byte[] messageBuffer = new byte[1024];

            DatagramPacket messagePacketIn = new DatagramPacket(messageBuffer, messageBuffer.length);
            multicastSocket.receive(messagePacketIn);
            message = new String(messagePacketIn.getData()).trim();

            //Comprobación del mensaje recibido
            if (!message.equalsIgnoreCase("salir")) {
                if (message.contains("PRIVADO:")) {

                    /* Recupera el nombre de usuario enviado por el servidor */
                    String userCheck = message.split(":")[1];

                    /* Si es nuestro usuario, empieza la conexión privada */
                    //IMPORTANTE: no diferencia MAYUS de MINUS
                    if (userCheck.equalsIgnoreCase(username)) {
                        new Thread(new Client()).start();
                    }

                } else {
                    System.out.println("Servidor: " + message);
                }
            } else {
                break;
            }
        }

    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(connection.getPrivateAddress(), connection.getPrivatePort());
            clientSocket.connect(inetSocketAddress);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(reader.readLine());
            reader.close();
            clientSocket.close();

        } catch (IOException ignored) {
        }

    }
}
