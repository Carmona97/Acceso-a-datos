package com.acdat.springboot.demo.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "clientes", schema = "public", catalog = "vuelos")
public class EntidadClientes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clienteId;
    @Basic
    @Column(name = "nombre_cliente", nullable = true, length = 100)
    private String nombreCliente;
    @Basic
    @Column(name = "correo_cliente", nullable = true, length = 100)
    private String correoCliente;

    @Basic
    @Column(name = "telefono_cliente", nullable = true, length = 20)
    private String telefonoCliente;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadClientes that = (EntidadClientes) o;
        return clienteId == that.clienteId && Objects.equals(nombreCliente, that.nombreCliente) && Objects.equals(correoCliente, that.correoCliente) && Objects.equals(telefonoCliente, that.telefonoCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, nombreCliente, correoCliente, telefonoCliente);
    }
}
