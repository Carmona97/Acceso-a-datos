package com.acdat.springboot.demo.modelo;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "destinos", schema = "public", catalog = "vuelos")
public class EntidadDestinos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int destinoId;
    @Basic
    @Column(name = "nombre_destino", nullable = true, length = 100)
    private String nombreDestino;
    @Basic
    @Column(name = "descripcion", nullable = true)
    private String descripcion;
    @Basic
    @Column(name = "costo_estadia", nullable = true)
    private double costoEstadia;

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoEstadia() {
        return costoEstadia;
    }

    public void setCostoEstadia(double costoEstadia) {
        this.costoEstadia = costoEstadia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadDestinos that = (EntidadDestinos) o;
        return destinoId == that.destinoId && Objects.equals(nombreDestino, that.nombreDestino) && Objects.equals(descripcion, that.descripcion) && Objects.equals(costoEstadia, that.costoEstadia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinoId, nombreDestino, descripcion, costoEstadia);
    }
}
