package UT4;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "src/UT4/DDBB_equipos.yap";
        //Se borra el fichero para limpiar datos aunque también esta el .delete sin embargo solo el objeto pasado por parámetro
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        //Creamos registros
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), path);
        db.store(new Equipo("Playas A", "alevín", 3, "Avenida Playa del Hombre", "Boss", 20));
        db.store(new Equipo("Porcinos", "categoria1", 8, "Vecindario", "Luis", 30));
        db.store(new Equipo("Playas B", "categoria2", 5, "Arucas", "Felipe", 22));
        db.store(new Equipo("Playas C", "categoria3", 7, "Madrid", "Carlos", 42));
        db.store(new Equipo("Playas D", "categoria4", 9, "Barcelona", "Alfonso", 21));

        ObjectSet<Equipo> equipos;

        //Obtener el nombre y los puntos de los equipos de la categoría alevín.
        equipos = db.queryByExample(new Equipo(null, "alevín", null, null, null, null));
        while (equipos.hasNext()) {
            Equipo equipo = equipos.next();
            System.out.println("Nombre: " + equipo.getNombre() + " - Puntos: " + equipo.getPuntos());
        }
        System.out.println();

        //Obtener la sede y el presidente de un equipo solicitado
        System.out.printf("Introduce un nombre de equipo: ");
        equipos = db.queryByExample(new Equipo(new Scanner(System.in).nextLine(), null, null, null, null, null));
        if (equipos.size() != 0){
            while (equipos.hasNext()) {
                Equipo equipo = equipos.next();
                System.out.println("Sede: " + equipo.getSede() + " - Presidente: " + equipo.getPresidente());
            }
        }else{
            System.out.println("No existe ningún equipo con ese nombre");
        }
        System.out.println();

        //Visualizar todos los datos
        equipos = db.queryByExample(new Equipo());
        while (equipos.hasNext()) {
            Equipo equipo = equipos.next();
            System.out.println(equipo.toString());
        }

        //Cierra la BBDD
        db.close();

    }
}
