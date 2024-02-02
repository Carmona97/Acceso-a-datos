package org.ACDAT.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "continentes", schema = "public", catalog = "ACDATJPA")
public class EntidadContinentes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idContinente", nullable = false)
    private int idContinente;
    @Basic
    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;

    public int getIdContinente() {
        return idContinente;
    }

    public void setIdContinente(int idContinente) {
        this.idContinente = idContinente;
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

        EntidadContinentes that = (EntidadContinentes) o;

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
