import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name="jugador")
@XmlType(propOrder ={"nombre","apodo","puesto","dorsal","descripcion"})
public class Jugador implements Serializable {

    //ATRIBUTOS

    private String nombre;
    private String apodo;
    private String puesto;
    private int dorsal;
    private String descripcion;

    //CONSTRUCTOR

    public Jugador(String nombre, String apodo, String puesto, int dorsal, String descripcion) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.puesto = puesto;
        this.dorsal = dorsal;
        this.descripcion = descripcion;
    }

    //GETTERS AND SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //METODO TOSTRING

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", puesto='" + puesto + '\'' +
                ", dorsal=" + dorsal +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
