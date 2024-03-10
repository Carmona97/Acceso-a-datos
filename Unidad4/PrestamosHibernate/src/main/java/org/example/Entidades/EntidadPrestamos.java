package org.example.Entidades;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "prestamos", schema = "public", catalog = "Prestamos")
public class EntidadPrestamos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_prestamo", nullable = false)
    private int idPrestamo;
    @Basic
    @Column(name = "id_libro", nullable = true)
    private Integer idLibro;
    @Basic
    @Column(name = "fecha_prestamo", nullable = true)
    private Date fechaPrestamo;
    @Basic
    @Column(name = "fecha_devolucion", nullable = true)
    private Date fechaDevolucion;
    @Basic
    @Column(name = "id_usuario", nullable = true)
    private Integer idUsuario;
    @ManyToOne
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")
    private EntidadLibros librosByIdLibro;

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public EntidadLibros getLibrosByIdLibro() {
        return librosByIdLibro;
    }

    public void setLibrosByIdLibro(EntidadLibros librosByIdLibro) {
        this.librosByIdLibro = librosByIdLibro;
    }

    public boolean agregarPrestamo(EntidadPrestamos prestamo) {
        boolean agregadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.persist(prestamo);
            transaction.commit();
        } catch (Exception e) {
            agregadoConExito = false;
            e.printStackTrace();
        }

        return agregadoConExito;
    }

    public boolean actualizarPrestamo(EntidadPrestamos prestamo) {
        boolean actualizadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.saveOrUpdate(prestamo);
            transaction.commit();
        } catch (Exception e) {
            actualizadoConExito = false;
            e.printStackTrace();
        }

        return actualizadoConExito;
    }

    public boolean eliminarPrestamo(EntidadPrestamos prestamo) {
        boolean eliminadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.delete(prestamo);
            transaction.commit();
        } catch (Exception e) {
            eliminadoConExito = false;
            e.printStackTrace();
        }

        return eliminadoConExito;
    }

    public EntidadPrestamos cargarPrestamo(int id) {
        EntidadPrestamos prestamo = new EntidadPrestamos();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            List<EntidadPrestamos> prestamosSeleccionados = miSesion.createNativeQuery("SELECT * FROM prestamos WHERE id_prestamo = ?", EntidadPrestamos.class).setParameter(1, id).list();

            if (!prestamosSeleccionados.isEmpty()) {
                prestamo = prestamosSeleccionados.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return prestamo;
    }
}
