package com.acdat.springboot.demo.modelo;

import java.io.Serializable;
import java.util.Objects;

public class EntidadClientesDestinosPK implements Serializable {
    private int clienteId;
    private int destinoId;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadClientesDestinosPK that = (EntidadClientesDestinosPK) o;
        return clienteId == that.clienteId && destinoId == that.destinoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, destinoId);
    }
}
