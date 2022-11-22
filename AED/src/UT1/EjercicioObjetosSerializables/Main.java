package UT1.EjercicioObjetosSerializables;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<String> nameList, DNIList;
    private static ArrayList<Integer> ageList;
    private static ArrayList<Double> salaryList;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static String filePathOBJ = "./AED/src/UT1.EjercicioObjetosSerializables/people.obj";
    private static String filePathDAT = "./AED/src/UT1.EjercicioObjetosSerializables/people.DAT";

    public static void main(String[] args) {
        //Apartado 1
        fillVariables();
        writeFile();
        readFile();
        //Apartado 2 el write está en el metodo dataWriteFile()
        dataReadFile();
    }

    private static void readFile() {
        People people;
        try {
            //file = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("./UT1.EjercicioObjetosSerializables/people.obj")).getFile());
            objectInputStream = new ObjectInputStream(new FileInputStream(filePathOBJ));
            dataOutputStream = new DataOutputStream(new FileOutputStream(filePathDAT));
            try {
                while (true) {
                    people = (People) objectInputStream.readObject();
                    System.out.println(people.toString());
                    dataWriteFile(people);
                }
            } catch (EOFException ignored) {
            }
            dataOutputStream.close();
            objectInputStream.close();
        } catch (NullPointerException | FileNotFoundException e) {
            System.err.println("Fichero no encontrado");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Crea el objeto personas y lo almace en el fichero
    private static void writeFile() {
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePathOBJ));
                for (int i = 0; i < DNIList.size(); i++) {
                    objectOutputStream.writeObject(new People(nameList.get(i), DNIList.get(i), ageList.get(i), salaryList.get(i)));
                }
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillVariables() {
        //El ejercicio pide que crease un array con los datos para luego crear el objeto
        //para no hacer que se introduzca los datos por consola, he creado unos vectores con todos los datos
        String[] names = new String[]{"Pepe", "Luis", "Juan", "Diego", "Pablo", "Carlos"};
        String[] DNIs = new String[]{"55781926A", "75083767Z", "25338196Q", "90322614G", "26458979D", "96997434V"};
        Integer[] ages = new Integer[]{24, 27, 17, 19, 23, 22};
        Double[] salaries = new Double[]{1200.00, 1450.50, 800.00, 950.50, 1100.00, 1200.50};

        nameList = new ArrayList<>();
        DNIList = new ArrayList<>();
        ageList = new ArrayList<>();
        salaryList = new ArrayList<>();

        //rellena las arrays
        for (int i = 0; i < DNIs.length; i++) {
            nameList.add(names[i]);
            DNIList.add(DNIs[i]);
            ageList.add(ages[i]);
            salaryList.add(salaries[i]);
        }
    }

    private static void dataWriteFile(People people) {
        try {
            dataOutputStream.writeUTF(people.getNombre());
            dataOutputStream.writeUTF(people.getDNI());
            dataOutputStream.writeInt(people.getEdad());
            dataOutputStream.writeDouble(people.getSalario());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dataReadFile() {
        String nombre, DNI;
        Integer edad;
        Double salario;
        //dataInputStream = new DataInputStream(new FileInputStream(new File("./AED/src/UT1.EjercicioObjetosSerializables","people.DAT")));
        try {
            dataInputStream = new DataInputStream(new FileInputStream(filePathDAT));
            try {
                //cuando se quede sin nada para leer saltará una excepcion y se saldrá del bucle
                while (true) {
                    nombre = dataInputStream.readUTF();
                    DNI = dataInputStream.readUTF();
                    edad = dataInputStream.readInt();
                    salario = dataInputStream.readDouble();
                    //Imprime solo aquellos que son mayores o igual a 18
                    if (edad >= 18) {
                        System.out.println(nombre + "   " + DNI + "   " + edad + "   " + salario);
                    }
                }
            } catch (EOFException ignored) {
            }
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
