package entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue("CC")
public class CuentaCredito extends Cuenta implements Serializable {

    private double limiteCredito;

    public CuentaCredito() {
        super();
    }
    public double getLimiteCredito() {
        return this.limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

}
