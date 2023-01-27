package UT4.Examen;

import UT4.Equipo;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //Es donde lo tengo guardado el fichero
        String path = "src/UT4/Examen/DDBB_Mascotas.yap";
        //Se borra el fichero para limpiar datos aunque también esta el .delete sin embargo solo el objeto pasado por parámetro
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        //Creamos registros
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), path);
        db.store(new Mascotas("100","Max","Pastor Alemán","600010203"));
        db.store(new Mascotas("200'","Kira","Pastor Alemán","699010203"));
        db.store(new Mascotas("300","Coty","Labrador","655010203"));
        db.store(new Mascotas("400","Luna","Labrador","677010203"));
        db.store(new Mascotas("500","Ali","Mestizon","644010203"));

        ObjectSet<Mascotas> mascotas;

        //Visualizar todos los datos
        mascotas = db.queryByExample(new Mascotas());
        while (mascotas.hasNext()) {
            Mascotas mascota = mascotas.next();
            System.out.println(mascota.toString());
        }

        //Cierra la BBDD
        db.close();
    }
}
