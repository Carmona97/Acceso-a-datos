package org.ACDAT.Entidades;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuario", schema = "public", catalog = "ACDATJPA")
public class EntidadUsuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario", nullable = false)
    private long idUsuario;
    @Basic
    @Column(name = "nombre", nullable = true, length = 48)
    private String nombre;

    @Column(name = "cumpleano", nullable = true)
    private Date fechaCumpleanyos;


    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaCumpleanyos(Date fechaCumpleanyos) {
        this.fechaCumpleanyos = fechaCumpleanyos;
    }

    public Date getFechaCumpleanyos() {
        return fechaCumpleanyos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadUsuario that = (EntidadUsuario) o;

        if (idUsuario != that.idUsuario) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) idUsuario;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
