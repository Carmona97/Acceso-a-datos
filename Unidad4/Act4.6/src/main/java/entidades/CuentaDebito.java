package entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue("CD")
public class CuentaDebito extends Cuenta implements Serializable {

    private double cargoPorDescubierto;

    public CuentaDebito() {
        super();
    }
    public double getCargoPorDescubierto() {
        return this.cargoPorDescubierto;
    }

    public void setCargoPorDescubierto(double cargoPorDescubierto) {
        this.cargoPorDescubierto = cargoPorDescubierto;
    }

}
