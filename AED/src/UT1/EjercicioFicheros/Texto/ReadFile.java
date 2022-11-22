package UT1.EjercicioFicheros.Texto;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ReadFile {
    public static void main(String[] args) {
        try {
            File file = new File(Objects.requireNonNull(ReadFile.class.getClassLoader().getResource("EjercicioFicheros/Texto/ReadFile.txt")).getFile());
                readFile(file); //Muestra el contenido del fichero
                System.out.println("\n-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
                readFileTen(file);
        } catch (NullPointerException | IOException e){
            System.out.println("Error E/S: " + e);
        }
    }

    private static void readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        //Lee el fichero y lo muestra
        int valor;
        while ((valor = fileReader.read()) != -1) {
            System.out.print((char) valor);
        }
        fileReader.close();
    }

    private static void readFileTen(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        //Lee el fichero de 10 en 10
        int character;
        char[] chars = new char[10];
        while ((character = fileReader.read(chars)) != -1) {
            System.out.println(new String(chars, 0, character));
            chars = new char[10]; //Para que reinicie las letras asignadas anteriormente
        }

        fileReader.close();
    }
}
