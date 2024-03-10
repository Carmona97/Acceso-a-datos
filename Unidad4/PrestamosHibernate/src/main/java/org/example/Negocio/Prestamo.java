package org.example.Negocio;

import org.example.Entidades.EntidadPrestamos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Prestamo {
    private int id;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private int idUsuario;

    public Prestamo(int id, int idLibro, Date fechaPrestamo, Date fechaDevolucion, int idUsuario) {
        this.id = id;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idUsuario = idUsuario;
    }

    public Prestamo() {
    }

    public int getId() {
        return id;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String mostrarPrestamosLogica() throws Exception {
        String respuesta = "";
        EntidadPrestamos entidadPrestamos = new EntidadPrestamos();
        ArrayList<Prestamo> prestamosLogica = entidadPrestamos.mostrarPrestamosPersistencia();

        for (Prestamo prestamo : prestamosLogica) {
            respuesta = respuesta + "\n" + prestamo.toString();
        }

        return respuesta;
    }

    public boolean agregarPrestamo() throws SQLException {
        boolean respuesta = false;

        EntidadPrestamos prestamoNuevo = new EntidadPrestamos();
        respuesta = prestamoNuevo.agregarPrestamo(this);

        return respuesta;
    }

    public boolean actualizarPrestamo() throws Exception {
        boolean respuesta = false;

        EntidadPrestamos entidadPrestamos = new EntidadPrestamos();
        entidadPrestamos.setIdPrestamo(this.id);
        entidadPrestamos.setIdLibro(this.idLibro);
        entidadPrestamos.setFechaPrestamo((java.sql.Date) this.fechaPrestamo);
        entidadPrestamos.setFechaDevolucion((java.sql.Date) this.fechaDevolucion);
        entidadPrestamos.setIdUsuario(this.idUsuario);
        respuesta = entidadPrestamos.actualizarPrestamo(entidadPrestamos);

        return respuesta;
    }

    public boolean eliminarPrestamo() throws Exception {
        boolean respuesta = false;

        EntidadPrestamos entidadPrestamos = new EntidadPrestamos();
        entidadPrestamos.setIdPrestamo(this.id);
        entidadPrestamos.setIdLibro(this.idLibro);
        entidadPrestamos.setFechaPrestamo((java.sql.Date) this.fechaPrestamo);
        entidadPrestamos.setFechaDevolucion((java.sql.Date) this.fechaDevolucion);
        entidadPrestamos.setIdUsuario(this.idUsuario);
        respuesta = entidadPrestamos.eliminarPrestamo(entidadPrestamos);

        return respuesta;
    }

    public boolean cargarPrestamo() throws Exception {
        boolean respuesta = false;

        EntidadPrestamos entidadPrestamos = new EntidadPrestamos();
        EntidadPrestamos prestamo = entidadPrestamos.cargarPrestamo(this.id);
        if (prestamo != null) {
            this.id = prestamo.getIdPrestamo();
            this.idLibro = prestamo.getIdLibro();
            this.fechaPrestamo = prestamo.getFechaPrestamo();
            this.fechaDevolucion = prestamo.getFechaDevolucion();
            this.idUsuario = prestamo.getIdUsuario();

            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", idLibro=" + idLibro +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
