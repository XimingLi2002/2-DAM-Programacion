package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "departamentos", catalog = "postgres")
public class PostgreDepartamentos {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @Basic
    @Column(name = "localidad", nullable = false, length = 49)
    private String localidad;
    @OneToMany(mappedBy = "departamentosByIdDpt")
    private Collection<PostgreEmpleados> empleadosById;

    public PostgreDepartamentos() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public PostgreDepartamentos(int id, String nombre, String localidad) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostgreDepartamentos that = (PostgreDepartamentos) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (localidad != null ? !localidad.equals(that.localidad) : that.localidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (localidad != null ? localidad.hashCode() : 0);
        return result;
    }

    public Collection<PostgreEmpleados> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Collection<PostgreEmpleados> empleadosById) {
        this.empleadosById = empleadosById;
    }

    @Override
    public String toString() {
        return "PostgreDepartamentos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
