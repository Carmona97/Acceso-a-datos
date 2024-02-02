package org.ACDAT;

import jakarta.persistence.*;

@Entity
@Table(name = "continentes", schema = "public", catalog = "ACDATJPA")
public class EntidadContinente {
    private int idContinente;
    private String nombre;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "idContinente", nullable = false)
    public int getIdContinente() {
        return idContinente;
    }

    public void setIdContinente(int idContinente) {
        this.idContinente = idContinente;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 100)
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

        EntidadContinente that = (EntidadContinente) o;

        if (idContinente != that.idContinente) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContinente;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
