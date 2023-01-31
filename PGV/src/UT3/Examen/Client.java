package UT3.Examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Scanner scanner = new Scanner(System.in);
    private OutputStream clientWriter;
    private BufferedReader clientReader;

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }
    @Override
    public void run() {
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",26555);
            Socket clientSocket = new Socket();
            clientSocket.connect(inetSocketAddress);

            clientWriter = clientSocket.getOutputStream();
            clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Extras: identificacion del usuario, pasé este exto al server para mostrarlo ahi
            System.out.printf("Introduce tu nombre de usuario: ");
            String username = scanner.nextLine();
            String connectMessage = username + "[" + InetAddress.getLocalHost() + "] se ha conectado";
            clientWriter.write((connectMessage + "\n").getBytes());

            System.out.println("You are connected!");

            while (true) {
                String input = scanner.nextLine();
                //Comprueba si es un mensaje para salir
                if (input.equalsIgnoreCase("###")) {
                    clientWriter.write(("###" + "\n").getBytes());
                    System.out.println("You are desconnected!");
                    //Cierra todo
                    scanner.close();
                    clientReader.close();
                    clientWriter.close();
                    clientSocket.close();
                    //Sale del bucle para finalizar
                    break;
                }else{
                    clientWriter.write((input + "\n").getBytes());
                    System.out.println(clientReader.readLine());
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}