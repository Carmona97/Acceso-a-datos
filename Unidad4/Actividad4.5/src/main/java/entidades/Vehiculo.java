package entidades;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Vehiculos",schema = "public", catalog = "ventaDeCoches")
public class Vehiculo {
    @Id
    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "color")
    private String color;

    @Id
    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column(name = "fechaMatriculacion")
    private Date fechaMatriculacion;

    @ManyToOne
    @JoinColumn(name = "nombreComercial", foreignKey = @ForeignKey(name="NOMBRE_COMERCIAL_FK"), nullable = false)
    private Concesionario concesionarioEnPropiedad;

    public Vehiculo() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(Date fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Concesionario getConcesionarioEnPropiedad() {
        return concesionarioEnPropiedad;
    }

    public void setConcesionarioEnPropiedad(Concesionario concesionarioEnPropiedad) {
        this.concesionarioEnPropiedad = concesionarioEnPropiedad;
    }
}
