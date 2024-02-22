package org.acdat.entitiesXML;

public class EntidadDestinosAgencias {
    private int destinoId;
    private int agenciaId;
    private EntidadDestinos destinosByDestinoId;
    private EntidadAgencias agenciasByAgenciaId;

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

        EntidadDestinosAgencias that = (EntidadDestinosAgencias) o;

        if (destinoId != that.destinoId) return false;
        if (agenciaId != that.agenciaId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = destinoId;
        result = 31 * result + agenciaId;
        return result;
    }

    public EntidadDestinos getDestinosByDestinoId() {
        return destinosByDestinoId;
    }

    public void setDestinosByDestinoId(EntidadDestinos destinosByDestinoId) {
        this.destinosByDestinoId = destinosByDestinoId;
    }

    public EntidadAgencias getAgenciasByAgenciaId() {
        return agenciasByAgenciaId;
    }

    public void setAgenciasByAgenciaId(EntidadAgencias agenciasByAgenciaId) {
        this.agenciasByAgenciaId = agenciasByAgenciaId;
    }
}
