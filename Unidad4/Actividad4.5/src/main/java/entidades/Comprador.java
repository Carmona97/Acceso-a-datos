package entidades;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Compradores",schema = "public",catalog = "ventaDeCoches")

public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComprador")
    private int idComprador;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "esCompradoPor")
    @ManyToMany(mappedBy = "articulos")
    private ArrayList<Articulo> esCompradoPor;

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Articulo> getEsCompradoPor() {
        return esCompradoPor;
    }

    public void setEsCompradoPor(Articulo nuevoArticulo) {
        esCompradoPor.add(nuevoArticulo);
    }
}
