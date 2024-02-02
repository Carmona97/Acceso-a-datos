package org.ACDAT.Entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "persona", schema = "public", catalog = "ACDATJPA")
public class EntidadPersona {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPersona", nullable = false)
    private int idPersona;
    @Basic
    @Column(name = "nombre", nullable = true, length = 48)
    private String nombre;

    @Column(name = "telefono", nullable = true)
    private String telefono;
    @OneToMany
    @JoinColumn(name="idTelefono", foreignKey = @ForeignKey(name="TELEFONO_ID_FK"))
    private EntidadTelefono idTelefono;
    ArrayList<EntidadTelefono> listaTelefonos = new ArrayList<EntidadTelefono>();

    public EntidadPersona(String nombre) {
        this.nombre = nombre;
    }


    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

}
