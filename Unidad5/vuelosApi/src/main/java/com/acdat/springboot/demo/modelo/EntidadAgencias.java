package com.acdat.springboot.demo.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "agencias", schema = "public", catalog = "vuelos")
public class EntidadAgencias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int agenciaId;

    @Basic
    @Column(name = "nombre_agencia", nullable = true, length = 100)
    private String nombreAgencia;
    @Basic
    @Column(name = "direccion_agencia", nullable = true)
    private String direccionAgencia;
    @Basic
    @Column(name = "telefono_agencia", nullable = true, length = 20)
    private String telefonoAgencia;

    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public String getDireccionAgencia() {
        return direccionAgencia;
    }

    public void setDireccionAgencia(String direccionAgencia) {
        this.direccionAgencia = direccionAgencia;
    }

    public String getTelefonoAgencia() {
        return telefonoAgencia;
    }

    public void setTelefonoAgencia(String telefonoAgencia) {
        this.telefonoAgencia = telefonoAgencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadAgencias that = (EntidadAgencias) o;
        return agenciaId == that.agenciaId && Objects.equals(nombreAgencia, that.nombreAgencia) && Objects.equals(direccionAgencia, that.direccionAgencia) && Objects.equals(telefonoAgencia, that.telefonoAgencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agenciaId, nombreAgencia, direccionAgencia, telefonoAgencia);
    }
}
