package UT1.Apuntes;

import java.io.*;

public class FileTest {

    public static void main(String[] args) {
        //File file = new File(Objects.requireNonNull(FileTest.class.getClassLoader().getResource("./UT1.Apuntes/ReadFile.txt")).getFile());
        File file = new File("./AED/src/UT1.Apuntes/file.txt");
        try{
            WriteOnFile(file, "asd"); //Escribe en el fichero
            ReadFile(file); //Muestra el contenido del fichero
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
    }

    public static void WriteOnFile(File file, String text) throws IOException{
        FileWriter fileWriter = new FileWriter(file);
        //Escribe en el fichero
        fileWriter.write(text);
        //fileWriter.flush(); //Guarda los cambios del fichero
        fileWriter.close();
    }

    public static void ReadFile(File file) throws IOException{
        FileReader fileReader = new FileReader(file);
        //Lee el fichero y lo muestra
        int valor=fileReader.read();
        while(valor!=-1){
            System.out.print((char)valor);
            valor=fileReader.read();
        }
        fileReader.close();
    }
}