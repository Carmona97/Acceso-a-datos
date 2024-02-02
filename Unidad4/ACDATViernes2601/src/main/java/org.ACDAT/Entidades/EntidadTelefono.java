package org.ACDAT.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "telefono", schema = "public", catalog = "ACDATJPA")
public class EntidadTelefono {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idTelefono", nullable = false)
    private int idTelefono;

    @Column(name = "numTelefono", nullable = false)
    private String numTelefono;

    public EntidadTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public EntidadPersona getPersona() {
        return persona;
    }

    public void setPersona(EntidadPersona persona) {
        this.persona = persona;
    }

    @ManyToOne
    @JoinColumn(name="idPersona", foreignKey = @ForeignKey(name="PERSONA_ID_FK"))
    private EntidadPersona persona;


    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }


}
