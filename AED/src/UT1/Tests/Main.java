package UT1.Tests;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static Alumno alumno;
    private static File file;
    private static File file2;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;

    public static void main(String[] args) {
        file = new File("./AED/src/UT1.Tests", "alumnos.obj");
        file2 = new File("./AED/src/UT1.Tests", "testData.obj");
        scanner = new Scanner(System.in);
        /*
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }
        consolePrintln("Introduce el CIAL: ");
        String getCial = scanner.nextLine();
        consolePrintln("Introduce el nombre: ");
        String getName = scanner.nextLine();

        alumno = new Alumno(getCial);
        alumno.setNombre(getName);

        try {
            objectOutputStream.writeObject(alumno);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error E/S: " + e);
        }

        scanner.close();


        //Lectura
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            alumno = (Alumno) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(alumno.toString());
            System.out.println(alumno.getCIAL());

        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            System.out.println("Error E/S: " + e);
        }

         */

        //DATA
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(file2));
            dataOutputStream.writeInt(12);
            dataOutputStream.writeChar('A');
            dataOutputStream.writeUTF("Pedro Sanchez");
            dataOutputStream.close();

            dataInputStream = new DataInputStream((new FileInputStream(file2)));
            System.out.println(dataInputStream.readInt() + "  - -  " + dataInputStream.readChar() + "  - -  " + dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void consolePrintln(String text){
        System.out.println(text);
    }
}
