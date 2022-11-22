package UT1.EjercicioAccesoFicheroAleatorio;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


enum Bytes {
    INT(4), CHAR(2), DOUBLE(8),
    EMPLOYEE_NAME_STRING(CHAR.byteSize * Main.nameLength),
    EMPLOYEE_TELEPHONENUMBER_STRING(CHAR.byteSize * Main.telephoneNumberLength),

    TOTAL(INT.byteSize + EMPLOYEE_NAME_STRING.byteSize + EMPLOYEE_TELEPHONENUMBER_STRING.byteSize + DOUBLE.byteSize);
    int byteSize;
    Bytes(int byteSize) {
        this.byteSize = byteSize;
    }
}

public class Main {
    static RandomAccessFile randomAccessFile;
    static Scanner scanner = new Scanner(System.in);
    static int nameLength = 25, telephoneNumberLength = 9;
    static ArrayList<Empleado> employeeList = new ArrayList<>();
    static int salaryPosition, inputEmployeeNumber;
    static String path = "./src/UT1.EjercicioAccesoFicheroAleatorio/Empleados.dat";
    static boolean exists = false;

    public static void main(String[] args) {
        //crea los empleados y lo muestra por consola
        createEmployees();
        //Solicita al usuario el numero de empleado
        inputEmployeeNumber = consoleInputIntType();
        //lee el fichero buscando el empleado con el identificador introducido por consola
        readFile();
        //solicita al usuario por consola la cantidad de salario extra y lo reescribe en el fichero y luego lo muestra con readFile()
        if (exists) updateSalary();
        scanner.close();
    }

    private static void updateSalary() {
        double salary;
        openRandomFileAccess(path, "rw");
        try {
            randomAccessFile.seek(salaryPosition);
            salary = randomAccessFile.readDouble();
            System.out.println("El salario actual de est@ emplead@ es: " + salary);
            randomAccessFile.seek(salaryPosition);
            randomAccessFile.writeDouble(salary + consoleInputDoubleType());
            readFile();
            closeRandomFileAccess();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() {
        //variable que almace la posicion en bytes
        int position = 0;
        try {
            openRandomFileAccess(path, "r");
            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {
                randomAccessFile.seek(position);
                int employeeNumber = randomAccessFile.readInt();
                if (inputEmployeeNumber == employeeNumber) {
                    String name = getString(nameLength, randomAccessFile);
                    String telephoneNumber = getString(telephoneNumberLength, randomAccessFile);
                    double salary = randomAccessFile.readDouble();
                    System.out.println("Datos del emplead@ seleccionad@:     " + employeeNumber + "  " + name + "  " + telephoneNumber + "  " + salary);
                    exists = true;
                    //variable que guarda la posicion donde se encuentra el salario de un empleado
                    salaryPosition = position + Bytes.TOTAL.byteSize - Bytes.DOUBLE.byteSize;
                }
                position += Bytes.TOTAL.byteSize;
            }
        } catch (EOFException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!exists) {
            System.out.println("No existe el empleado");
        }
        closeRandomFileAccess();
    }

    private static void createEmployees() {
        openRandomFileAccess(path, "rw");
        Empleado empleado;
        for (int i = 0; i < 6; i++) {
            empleado = new Empleado();
            //Para no tener que volver a recorrer el fichero posteriormente he decicido almacenarlo en una lista
            employeeList.add(empleado);
            System.out.println(empleado);
            //Escribe en el fichero
            writeEmployee(empleado);
        }
        closeRandomFileAccess();
    }

    private static void writeEmployee(Empleado empleado) {
        StringBuffer stringBuffer;
        try {
            randomAccessFile.writeInt(empleado.getEmployeeNumber());
            stringBuffer = new StringBuffer(empleado.getName());
            //Hace que la longitud del texto sea siempre 25
            stringBuffer.setLength(25);
            randomAccessFile.writeChars(stringBuffer.toString());
            randomAccessFile.writeChars(empleado.getTelephoneNumber());
            randomAccessFile.writeDouble(empleado.getSalary());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getString(int length, RandomAccessFile randomAccessFile) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            try {
                chars[i] = randomAccessFile.readChar();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String(chars).trim();
    }

    private static void openRandomFileAccess(String path, String mode) {
        try {
            randomAccessFile = new RandomAccessFile(new File(path), mode);
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado");
        }
    }

    private static void closeRandomFileAccess() {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int consoleInputIntType() {
        int employeeNumberInput = 0;
        boolean error;
        do {
            System.out.println("Introduce el numero del empleado: ");
            error = false;
            try {
                employeeNumberInput = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.err.println("Dato inválido");
                error = true;
            }
        } while (error);
        return employeeNumberInput;
    }

    private static double consoleInputDoubleType() {
        double salary = 0;
        boolean error;
        do {
            System.out.println("Introduzca el importe extra: ");
            error = false;
            try {
                salary = scanner.nextDouble();
                if (salary <= 0) {
                    error = true;
                    System.out.println("Dato inválido, no puede ser un numero negativo");
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.err.println("Dato inválido, tiene que ser un numero entero con/sin decimales");
                error = true;
            }
        } while (error);
        return salary;
    }
}
