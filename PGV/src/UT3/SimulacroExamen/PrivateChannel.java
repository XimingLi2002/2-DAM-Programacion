package UT3.SimulacroExamen;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class PrivateChannel extends Thread {
    Connection connection;
    String privateMessage;

    public PrivateChannel(Connection connection, String privateMessage) {
        this.connection = connection;
        this.privateMessage = privateMessage;
    }

    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(connection.getPrivateAddress(), connection.getPrivatePort());
            serverSocket.bind(inetSocketAddress);
            Socket newSocket = serverSocket.accept();

            String respuesta = privateMessage;
            OutputStream outputStream = newSocket.getOutputStream();

            outputStream.write((respuesta + "\n").getBytes());

            outputStream.close();
            newSocket.close();
            serverSocket.close();
        } catch (IOException ignored) {
        }

    }
}
