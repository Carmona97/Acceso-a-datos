package entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Concesionarios",schema = "public",catalog = "ventaDeCoches")
public class Concesionario {
    @Id
    @Column(name="nombreComercial",nullable = false)
    private String nombreComercial;

    @Column(name = "nombreEmpresarial")
    private String nombreEmpresarial;

    @Column(name = "direccionConcesionario", nullable = false)
    private String direccionConcesionario;

    @Column(name = "numTrabajadores")
    private int numTrabajadores;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "concesionarioEnPropiedad", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculosEnVenta;

    public Concesionario() {
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreEmpresarial() {
        return nombreEmpresarial;
    }

    public void setNombreEmpresarial(String nombreEmpresarial) {
        this.nombreEmpresarial = nombreEmpresarial;
    }

    public String getDireccionConcesionario() {
        return direccionConcesionario;
    }

    public void setDireccionConcesionario(String direccionConcesionario) {
        this.direccionConcesionario = direccionConcesionario;
    }

    public int getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(int numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Vehiculo> getVehiculosEnVenta() {
        return vehiculosEnVenta;
    }

    public void setVehiculosEnVenta(List<Vehiculo> vehiculosEnVenta) {
        this.vehiculosEnVenta = vehiculosEnVenta;
    }
}
