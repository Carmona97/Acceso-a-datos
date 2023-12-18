package actividad161;

import java.io.Serializable;

public class Contacto implements Serializable {
    protected String nombre;
    protected String apellido;
    protected String numeroMovil;
    protected String numeroTrabajo;
    protected String numeroCasa;

    public Contacto(String nombre, String apellido, String numeroMovil, String numeroTrabajo, String numeroCasa) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroMovil = numeroMovil;
        this.numeroTrabajo = numeroTrabajo;
        this.numeroCasa = numeroCasa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroMovil() {
        return numeroMovil;
    }

    public void setNumeroMovil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
    }

    public String getNumeroTrabajo() {
        return numeroTrabajo;
    }

    public void setNumeroTrabajo(String numeroTrabajo) {
        this.numeroTrabajo = numeroTrabajo;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre completo='" + nombre + " "+apellido+
                ", numeroMovil='" + numeroMovil + '\'' +
                ", numeroTrabajo='" + numeroTrabajo + '\'' +
                ", numeroCasa='" + numeroCasa + '\'' +
                '}';
    }

    public static void crearContacto(String nombre, String apellido, String numeroMovil,String numeroTrabajo,String numeroCasa){
        Contacto nuevo = new Contacto(nombre,apellido,numeroMovil,numeroTrabajo,numeroCasa);
    }
}
