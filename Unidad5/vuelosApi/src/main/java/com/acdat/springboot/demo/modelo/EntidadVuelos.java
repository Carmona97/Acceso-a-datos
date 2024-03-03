package com.acdat.springboot.demo.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "vuelos", schema = "public", catalog = "vuelos")
public class EntidadVuelos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vueloId;
    @Basic
    @Column(name = "origen", nullable = true, length = 50)
    private String origen;
    @Basic
    @Column(name = "destino", nullable = true, length = 50)
    private String destino;
    @Basic
    @Column(name = "fecha_salida", nullable = true)
    private Date fechaSalida;
    @Basic
    @Column(name = "fecha_llegada", nullable = true)
    private Date fechaLlegada;
    @Basic
    @Column(name = "costo", nullable = true)
    private double costo;

    public int getVueloId() {
        return vueloId;
    }

    public void setVueloId(int vueloId) {
        this.vueloId = vueloId;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadVuelos that = (EntidadVuelos) o;
        return vueloId == that.vueloId && Objects.equals(origen, that.origen) && Objects.equals(destino, that.destino) && Objects.equals(fechaSalida, that.fechaSalida) && Objects.equals(fechaLlegada, that.fechaLlegada) && Objects.equals(costo, that.costo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vueloId, origen, destino, fechaSalida, fechaLlegada, costo);
    }
}
