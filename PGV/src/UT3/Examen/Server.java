package UT3.Examen;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Server implements Runnable {
    public static void main(String[] args) {
        new Thread(new Server()).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 26555);
            serverSocket.bind(inetSocketAddress);

            while (true) {
                //Se queda a la espera de clientes(conexiones) y lo atiende creando un hilo aparte
                Socket socket = serverSocket.accept();
                new Thread(new ClientConnection(socket)).start();
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    static class ClientConnection implements Runnable {
        private final Socket socket;
        private OutputStream serverWriter;
        private BufferedReader serverReader;

        ClientConnection(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                serverWriter = socket.getOutputStream();

                //Esto lee x usuario se ha conectado (No lo pide el examen)
                System.out.println(serverReader.readLine());

                String clientMessage;
                while (true) {
                    clientMessage = serverReader.readLine();
                    if (clientMessage.equalsIgnoreCase("###")) {
                        //Mensaje a mostrar en el servidor
                        //podria haber hecho que el cliente mande tipo: {user} se ha desconectado y que el server lo lea pero el apartado no lo pedía
                        System.out.println("Se ha desconectado un cliente");
                        break;
                    } else {
                        //Siempre mostrará el mensaje del cliente menos el de desconectar
                        System.out.println(clientMessage);
                    }
                    if (clientMessage.toUpperCase().startsWith("DOWNLOAD")) {
                        //Si es un mensaje de descarga pues le responderá un Ok, recibido, se han desca... por eso esta sepaarado en un if
                        serverWriter.write(("OK, recibido, se han descargado " + new Random().nextInt(128) + " archivos" + "\n").getBytes());
                        System.out.println("Descarga completada");
                    } else {
                        serverWriter.write(("OK, recibido" + "\n").getBytes());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}