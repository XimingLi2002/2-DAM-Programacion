package UT1.EjercicioFicheros.ListasDeFicheros;

import java.io.File;
import java.util.Objects;

public class DirectorioActual {
    public static void main(String[] args) {
        //File file = new File("./");
        File file = new File(Objects.requireNonNull(DirectorioActual.class.getClassLoader().getResource("./")).getFile());
        String[] list = file.list();
        for (String listName : list) {
            System.out.println(listName);
        }
    }
}
