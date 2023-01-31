import entity.OracleMascotas;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static JPA_Manager jpaManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        //Para que se quite los logs que salta en la consola
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        //Conexión a la base de datos (Lo que está en el constructor de la clase JPA_Manager)
        jpaManager = new JPA_Manager();
        scanner = new Scanner(System.in);

        //Limpia los registros de la tabla
        jpaManager.getList(OracleMascotas.class).forEach(it -> jpaManager.removeEntity(it));

        //Se insertarán los datos, en caso de que ya exista una con la misma primary key no lo insertará
        jpaManager.insert(new OracleMascotas("100","Max","Pastor Alemán","600010203"));
        jpaManager.insert(new OracleMascotas("200'","Kira","Pastor Alemán","699010203"));
        jpaManager.insert(new OracleMascotas("300","Coty","Labrador","655010203"));
        jpaManager.insert(new OracleMascotas("400","Luna","Labrador","677010203"));
        jpaManager.insert(new OracleMascotas("500","Ali","Mestizon","644010203"));

        //Ambas formas son lo mismo para mostrar datos (Muestra los datos de todas las mascotas)
        //jpaManager.getList(OracleMascotas.class).forEach(it -> System.out.println(it.toString()));
        jpaManager.getList(OracleMascotas.class).forEach(System.out::println);

        //Muestra el nombre de las mascotas que tengan la raza pasado por consola (parámetro)
        List<OracleMascotas> list = findMascostsByRaza();
        if (!list.isEmpty()) {
            list.forEach(it -> System.out.println(it.getNombre()));
        } else {
            System.out.println("No hay mascotas registradas con esa raza");
        }

        //Sustituye Pastor Alemán por Pastor Belga
        AtomicInteger updateds = new AtomicInteger();
        jpaManager.getList(OracleMascotas.class).forEach( it -> {
            if (it.getRaza().trim().equals("Pastor Alemán")){
                it.setRaza("Pastor Belga");
                jpaManager.updateEntity(it);
                updateds.getAndIncrement();
            }
        });
        System.out.println("Se han realizado " + updateds + " cambios (updates)");

        //Remueve la mascota que tenga el chip pasado por parámetro
        AtomicInteger removes = new AtomicInteger();
        list = removeByChipParameter();
        if (!list.isEmpty()) {
            list.forEach(it -> {jpaManager.removeEntity(it); removes.getAndIncrement();
                System.out.println("Removido: " + it);});
        } else {
            System.out.println("No existe ninguna mascota que contenga ese chip");
        }
        System.out.println("Se han removido " + removes + " mascotas");

        //Muestra nuevamente los datos para ver que se ha eliminado de verdad y la actualizacion hecha anteriormente
        jpaManager.getList(OracleMascotas.class).forEach(System.out::println);
        jpaManager.close();
    }

    private static List<OracleMascotas> findMascostsByRaza() {
        String querySentence = "SELECT m FROM OracleMascotas m WHERE m.raza IN (?1)";
        Query query = jpaManager.getQuery(OracleMascotas.class, querySentence);
        System.out.print("Raza: ");
        query.setParameter(1, scanner.nextLine());
        return query.getResultList();
    }

    private static List<OracleMascotas> removeByChipParameter() {
        String querySentence = "SELECT m FROM OracleMascotas m WHERE m.chip IN (?1)";
        Query query = jpaManager.getQuery(OracleMascotas.class, querySentence);
        System.out.print("Chip: ");
        query.setParameter(1, scanner.nextLine());
        return query.getResultList();
    }



}
