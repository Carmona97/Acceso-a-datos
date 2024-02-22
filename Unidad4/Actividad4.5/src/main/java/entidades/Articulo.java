package entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Articulos",schema = "public",catalog = "ventaDeCoches")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArticulo")
    private int idArticulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precioVenta")
    private long precioVenta;

    @ManyToMany
    @JoinTable(name = "compra", joinColumns = @JoinColumn(name = "idArticulo"), inverseJoinColumns = @JoinColumn(name = "idComparator"))
    private List<Comprador> articulos;

    public Articulo() {
        this.articulos = new ArrayList<>();
    }

    public List<Comprador> getArticulos() {
        return articulos;
    }

    public void setArticulos(Comprador nuevoComprador) {
        articulos.add(nuevoComprador);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(long precioVenta) {
        this.precioVenta = precioVenta;
    }
}
