package UT1.EjercicioXMLDOM;

import java.io.Serializable;

public class CentroEducativo implements Serializable {
    private String tipo;
    private String nombre;
    private Integer codigo;
    private String calle;
    private Integer codigoPostal;
    private String localidad;
    private String isla;
    private Integer attributesSize = 7;
    public CentroEducativo(String tipo, String nombre, Integer codigo, String calle, Integer codigoPostal, String localidad, String isla) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.codigo = codigo;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.isla = isla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getIsla() {
        return isla;
    }

    public void setIsla(String isla) {
        this.isla = isla;
    }

    public Integer getAttributesSize(){
        return attributesSize;
    }
    @Override
    public String toString() {
        return "CentroEducativo{" +
                "tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigo=" + codigo +
                ", calle='" + calle + '\'' +
                ", codigoPostal=" + codigoPostal +
                ", localidad='" + localidad + '\'' +
                ", isla='" + isla + '\'' +
                '}';
    }
}
