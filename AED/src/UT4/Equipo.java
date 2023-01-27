package UT4;

public class Equipo {
    private String nombre, categoria, sede, presidente;
    private Integer grupo, puntos;

    Equipo(){

    }

    public Equipo(String nombre, String categoria, Integer grupo, String sede, String presidente, Integer puntos) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.sede = sede;
        this.presidente = presidente;
        this.grupo = grupo;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", sede='" + sede + '\'' +
                ", presidente='" + presidente + '\'' +
                ", grupo=" + grupo +
                ", puntos=" + puntos +
                '}';
    }
}
