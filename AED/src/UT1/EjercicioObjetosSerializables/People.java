package UT1.EjercicioObjetosSerializables;

import java.io.Serializable;

public class People implements Serializable {
    String nombre;
    String DNI;
    Integer edad;
    Double salario;

    public People(String nombre, String DNI, Integer edad, Double salario) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.edad = edad;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "People{" +
                "nombre='" + nombre + '\'' +
                ", DNI='" + DNI + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                '}';
    }
}
