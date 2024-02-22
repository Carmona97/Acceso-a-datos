package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "venta",schema = "public",catalog = "ventaDeCoches")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta")
    private Long idVenta;
    @ManyToOne
    @JoinColumn(name = "idComprador")
    private Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;

    @Column(name = "unidadesVendidas", nullable = false)
    private int unidadesVendidas;

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }
}
