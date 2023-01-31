package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados", catalog = "postgres")
public class PostgreEmpleados {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @Basic
    @Column(name = "salario", nullable = false, precision = 0)
    private double salario;
    @Basic
    @Column(name = "id_dpt", nullable = false)
    private int idDpt;
    @ManyToOne
    @JoinColumn(name = "id_dpt", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private PostgreDepartamentos departamentosByIdDpt;

    public PostgreEmpleados(int id, String nombre, double salario, int idDpt) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.idDpt = idDpt;
    }

    public PostgreEmpleados() {

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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getIdDpt() {
        return idDpt;
    }

    public void setIdDpt(int idDpt) {
        this.idDpt = idDpt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostgreEmpleados that = (PostgreEmpleados) o;

        if (id != that.id) return false;
        if (Double.compare(that.salario, salario) != 0) return false;
        if (idDpt != that.idDpt) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        temp = Double.doubleToLongBits(salario);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + idDpt;
        return result;
    }

    public PostgreDepartamentos getDepartamentosByIdDpt() {
        return departamentosByIdDpt;
    }

    public void setDepartamentosByIdDpt(PostgreDepartamentos departamentosByIdDpt) {
        this.departamentosByIdDpt = departamentosByIdDpt;
    }

    @Override
    public String toString() {
        return "PostgreEmpleados{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", idDpt=" + idDpt +
                ", departamentosByIdDpt=" + departamentosByIdDpt +
                '}';
    }
}
