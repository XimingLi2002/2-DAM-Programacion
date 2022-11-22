package UT1.EjercicioXMLDOM;

import java.io.*;

import static UT1.EjercicioXMLDOM.Main.filePathOBJ;

public class objectIO {
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    protected static void createObjects() {
        //Las variables que se va a usar para crear cada (objeto) centro educativo
        String[] tipo = new String[]{"CIFP", "CEIP", "IES", "IES", "IES"};
        String[] nombre = new String[]{"Villa de Agüimes", "Abona", "Las Salinas", "Puerto del Rosario", "Garoé"};
        Integer[] codigo = new Integer[]{35014664, 38008560, 35007398, 35006187, 38011303};
        String[] calle = new String[]{"Alcorac 50", "La Orotava 10", "Tamaragua 2", "Gran Canaria 45", "República de venezuela 2"};
        Integer[] codigoPostal = new Integer[]{35118, 38611, 35500, 35600, 38900};
        String[] localidad = new String[]{"Agüimes", "San Isidro", "Arrecife", "Puerto del Rosario", "Valverde"};
        String[] isla = new String[]{"Gran Canaria", "Tenerife", "Lanzarote", "Fuerteventura", "El Hierro"};
        openObjectOutputStream();
        for (int i = 0; i < tipo.length; i++) {
            writeFile(new CentroEducativo(tipo[i], nombre[i], codigo[i], calle[i], codigoPostal[i], localidad[i], isla[i]));
        }
        closeObjectOutputStream();
    }
    protected static void readObjects() {
        openObjectInputStream();
        int i = 0;
        try {
            while (true) {
                xmlIO.centroEducativosList.add((CentroEducativo) objectInputStream.readObject());
                System.out.println(xmlIO.centroEducativosList.get(i).toString());
                i++;
            }
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        closeObjectInputStream();
    }
    private static void openObjectOutputStream(){
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePathOBJ));
        }catch (FileNotFoundException e){
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void openObjectInputStream(){
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filePathOBJ));
        }catch (FileNotFoundException e){
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeFile(CentroEducativo centroEducativo) {
        try {
            objectOutputStream.writeObject(centroEducativo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void closeObjectOutputStream(){
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void closeObjectInputStream(){
        try {
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
