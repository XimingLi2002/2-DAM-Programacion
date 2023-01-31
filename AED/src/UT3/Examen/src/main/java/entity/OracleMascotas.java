package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MASCOTAS", schema = "XIMING_AED")
public class OracleMascotas {

    @Id
    @Column(name = "CHIP", nullable = false, length = 10)
    private String chip;
    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 20)
    private String nombre;
    @Basic
    @Column(name = "RAZA", nullable = true, length = 20)
    private String raza;
    @Basic
    @Column(name = "TELEFONO", nullable = true, length = 9)
    private String telefono;

    public OracleMascotas() {

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

    public OracleMascotas(String chip, String nombre, String raza, String telefono) {
        this.chip = chip;
        this.nombre = nombre;
        this.raza = raza;
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OracleMascotas that = (OracleMascotas) o;

        if (chip != null ? !chip.equals(that.chip) : that.chip != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (raza != null ? !raza.equals(that.raza) : that.raza != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chip != null ? chip.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (raza != null ? raza.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OracleMascotas{" +
                "chip='" + chip + '\'' +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
