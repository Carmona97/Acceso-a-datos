package org.ACDAT.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "paises", schema = "public", catalog = "ACDATJPA")
public class EntidadPaises {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPais", nullable = false)
    private int idPais;
    @Basic
    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "mi_continente_ConLogger", nullable = true, length = 50)
    private String continente;


    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadPaises that = (EntidadPaises) o;

        if (idPais != that.idPais) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPais;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
