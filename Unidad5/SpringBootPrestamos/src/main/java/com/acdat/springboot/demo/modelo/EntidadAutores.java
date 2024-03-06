package com.acdat.springboot.demo.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "autores", schema = "public", catalog = "Prestamos")
public class EntidadAutores {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAutor;
    @Basic
    @Column(name = "nombre_autor", nullable = true, length = 255)
    private String nombreAutor;
    @Basic
    @Column(name = "pais", nullable = true, length = 50)
    private String pais;

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadAutores that = (EntidadAutores) o;
        return idAutor == that.idAutor && Objects.equals(nombreAutor, that.nombreAutor) && Objects.equals(pais, that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutor, nombreAutor, pais);
    }
}
