package UT1.Apuntes;

import java.io.*;

public class WriteOnFile {
    public static void main(String[] args) {
        String path = "./src/UT1.Apuntes/Texto.txt";
        File file = new File(path);
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("32qweqwe");
            fileWriter.flush();

            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            fileWriter.write("asafafasfa");
            fileWriter.flush();


            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
