package com.acdat.springboot.demo.modelo;

import java.io.Serializable;
import java.util.Objects;

public class EntidadDestinosAgenciasPK implements Serializable {
    private int destinoId;
    private int agenciaId;

    public int getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(int destinoId) {
        this.destinoId = destinoId;
    }

    public int getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadDestinosAgenciasPK that = (EntidadDestinosAgenciasPK) o;
        return destinoId == that.destinoId && agenciaId == that.agenciaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinoId, agenciaId);
    }
}
