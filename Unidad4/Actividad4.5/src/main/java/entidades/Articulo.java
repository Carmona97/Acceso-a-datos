package entidades;

import jakarta.persistence.*;

import java.util.ArrayList;

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
    @ManyToMany(mappedBy = "esCompradoPor")
    @Column(name = "compra")
    private ArrayList<Comprador> articulos;

    public ArrayList<Comprador> getArticulos() {
        return articulos;
    }

    public void setArticulos(Comprador nuevoComprador) {
        articulos.add(nuevoComprador);
    }

    public int getIdArticulo() {
        return idArticulo;
    }
    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
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
