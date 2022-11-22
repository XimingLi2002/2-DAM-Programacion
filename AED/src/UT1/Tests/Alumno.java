package UT1.Tests;

public class Alumno extends Persona{
    String nombre;
    String CIAL;
    String NIF;
    Double nota;

    public Alumno(String CIAL){
        super();
        this.setCIAL(CIAL);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCIAL() {
        return CIAL;
    }

    public void setCIAL(String CIAL) {
        this.CIAL = CIAL;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", CIAL='" + CIAL + '\'' +
                ", NIF='" + NIF + '\'' +
                ", nota=" + nota +
                '}';
    }
}
