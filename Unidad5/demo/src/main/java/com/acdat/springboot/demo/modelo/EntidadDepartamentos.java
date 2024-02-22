package com.acdat.springboot.demo.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "empleados")
public class EntidadDepartamentos {
    private int depno;
    private String nombre;
    private String ubicacion;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "depno", nullable = false)
    public int getDepno() {
        return depno;
    }

    public void setDepno(int depno) {
        this.depno = depno;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 14)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ubicacion", nullable = true, length = 13)
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadDepartamentos that = (EntidadDepartamentos) o;
        return depno == that.depno && Objects.equals(nombre, that.nombre) && Objects.equals(ubicacion, that.ubicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depno, nombre, ubicacion);
    }
}
