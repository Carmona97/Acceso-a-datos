package com.acdat.springboot.demo.DTO;

public class DestinoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private double costoEstadia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
