package UT2.Examen.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BufferedIO {
    private File file;
    public BufferedIO(String path){
        this.file = new File(path);
    }

    public ArrayList<String> readFile() {
        ArrayList<String> linesList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesList.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return linesList;
    }
}
