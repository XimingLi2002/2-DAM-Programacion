package UT4.Examen;

public class Mascotas {
    private String chip, nombre, raza, telefono;

    public Mascotas(String chip, String nombre, String raza, String telefono) {
        this.chip = chip;
        this.nombre = nombre;
        this.raza = raza;
        this.telefono = telefono;
    }
    public Mascotas(){

    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Mascotas{" +
                "chip='" + chip + '\'' +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
