package UT3.SimulacroExamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server extends Thread {
    //TCP = Para comunicar siempre con el mismo destinatario y el canal de comunicación se queda abierto para intercambiar múltiples mensajes
    //UDP = Para

    //grupo donde se conectan
    static MulticastSocket multicastSocket;

    public static void main(String[] args) throws IOException {
        Connection connection = new Connection();

        String username = "server";

        SocketAddress socketAddress = new InetSocketAddress(connection.getInetAddress(), connection.getMulticastPort());

        multicastSocket = new MulticastSocket(connection.getMulticastPort());
        multicastSocket.joinGroup(socketAddress, null);

        String connectMessage = username + "[" + InetAddress.getLocalHost() + "] se ha unido al grupo (iniciado)";

        DatagramPacket connectionPacketOut = new DatagramPacket(connectMessage.getBytes(), connectMessage.length(), connection.getInetAddress(), connection.getMulticastPort());
        //El mensaje almacenado en el DatagramPacket se "sube a la red" y para recibirlo se usa un .receive
        //El mensaje permanecerá hasta que se haga otro .send() y se sobreescriba
        multicastSocket.send(connectionPacketOut);

        //Lee constantemente los mensajes que se suben a la red
        new Server().start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String message = "";
        DatagramPacket messagePacketOut;

        while (!(message = (bufferedReader.readLine()).trim()).equalsIgnoreCase("salir")) {
            try {
                //Si es un mensaje privado, quita el mensaje y prepara el canal TCP
                if (message.toUpperCase().contains("PRIVADO:")) {
                    String splitMessage[] = message.split(":");

                    if (splitMessage.length > 2) {
                        /* Mensaje con solo 'PRIVADO:username' */
                        String broadcastMsg = (splitMessage[0] + ":" + splitMessage[1]).toUpperCase();

                        /* Mensaje a enviar por el canal privado */
                        String privateMessage = "PRIVADO: " + splitMessage[2];

                    /*
                       Enviamos el mensaje con 'PRIVADO:username', y el cliente con ese username
                       se conectará al canal privado (no se muestra para los otros)
                    */

                        messagePacketOut = new DatagramPacket(broadcastMsg.getBytes(), broadcastMsg.length(), connection.getInetAddress(), connection.getMulticastPort());
                        multicastSocket.send(messagePacketOut);

                        /* Iniciamos la conexión privada como servidor */
                        new Thread(new PrivateChannel(connection, privateMessage)).start();
                    }
                }else {
                    messagePacketOut = new DatagramPacket(message.getBytes(), message.length(), connection.getInetAddress(), connection.getMulticastPort());
                    multicastSocket.send(messagePacketOut);
                    System.out.println("Mensaje envíado");
                }


            }catch (IOException ignored) {
            }
        }
        multicastSocket.close();
        System.out.println("Socket cerrado definitivamente (server)");

    }

    @Override
    public void run() {
        String message = "";
        try {
            while (!message.trim().equals("/")) {
                byte[] buf = new byte[1000];
                DatagramPacket datagramPacketIn = new DatagramPacket(buf, buf.length);

                multicastSocket.receive(datagramPacketIn);

                message = new String(datagramPacketIn.getData()).trim();
                System.out.println(message);
            }

        } catch (IOException e) { // gestionar
        }
    }
}
