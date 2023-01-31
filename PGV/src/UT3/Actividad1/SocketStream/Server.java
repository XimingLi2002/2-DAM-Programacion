package UT3.Actividad1.SocketStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server implements Runnable{
    public static void main(String[] args) throws IOException {
        new Thread(new Server()).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 5050);
            serverSocket.bind(inetSocketAddress);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientConnection(socket)).start();
            }
        }catch (RuntimeException | IOException e){
            throw new RuntimeException(e);
        }
    }

    static class ClientConnection implements Runnable{
        private final Socket socket;
        ClientConnection(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();

                String clientIp = this.socket.getInetAddress().getHostAddress();
                String threadName = Thread.currentThread().getName();
                String currentDate = new Date(System.currentTimeMillis()).toString();
                System.out.println(currentDate + " - " + threadName + " connected with IP: " + clientIp);

                while (true) {
                    byte[] bytes = new byte[128];
                    inputStream.read(bytes);
                    String message = new String(bytes).trim();
                    if (message.equalsIgnoreCase("exit")){
                        System.out.println(threadName + " has disconnected!");
                        break;
                    }else {
                        System.out.println(threadName + ": " + message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
