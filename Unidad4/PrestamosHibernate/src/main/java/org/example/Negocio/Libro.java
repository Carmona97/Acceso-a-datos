package org.example.Negocio;

import org.example.Entidades.EntidadLibros;
import org.example.Entidades.JPAPersistencia;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class Libro {
    private int id;
    private String titulo;
    private int idAutor;
    private String genero;
    private int anioPublicacion;

    public Libro(int id, String titulo, int idAutor, String genero, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
    }

    public Libro() {
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String mostrarLibrosLogica() throws Exception {
        String respuesta = "";
        EntidadLibros entidadLibros = new EntidadLibros();
        ArrayList<Libro> librosLogica = entidadLibros.mostrarLibrosPersistencia();

        for (Libro libro : librosLogica) {
            respuesta += libro.toString() + "\n";
        }

        return respuesta;
    }

    public boolean agregarLibro() throws SQLException {
        boolean respuesta = false;

        EntidadLibros libroNuevo = new EntidadLibros();
        libroNuevo.setIdLibro(this.id);
        libroNuevo.setTitulo(this.titulo);
        libroNuevo.setIdAutor(this.idAutor);
        libroNuevo.setGenero(this.genero);
        libroNuevo.setAnioPublicacion(this.anioPublicacion);
        respuesta = libroNuevo.agregarLibro(libroNuevo);

        return respuesta;
    }

    public boolean actualizarLibro() throws Exception {
        boolean respuesta = false;

        EntidadLibros entidadLibros = new EntidadLibros();
        entidadLibros.setIdLibro(this.id);
        entidadLibros.setTitulo(this.titulo);
        entidadLibros.setIdAutor(this.idAutor);
        entidadLibros.setGenero(this.genero);
        entidadLibros.setAnioPublicacion(this.anioPublicacion);
        respuesta = entidadLibros.actualizarLibro(entidadLibros);

        return respuesta;
    }

    public boolean eliminarLibro() throws Exception {
        boolean respuesta = false;

        EntidadLibros entidadLibros = new EntidadLibros();
        entidadLibros.setIdLibro(this.id);
        entidadLibros.setTitulo(this.titulo);
        entidadLibros.setIdAutor(this.idAutor);
        entidadLibros.setGenero(this.genero);
        entidadLibros.setAnioPublicacion(this.anioPublicacion);
        respuesta = entidadLibros.eliminarLibro(entidadLibros);

        return respuesta;
    }

    public boolean cargarLibro() throws Exception {
        boolean respuesta = false;

        EntidadLibros entidadLibros = new EntidadLibros();
        EntidadLibros libro = entidadLibros.cargarLibro(this.id);
        if (libro != null) {
            this.id = libro.getIdLibro();
            this.titulo = libro.getTitulo();
            this.idAutor = libro.getIdAutor();
            this.genero = libro.getGenero();
            this.anioPublicacion = libro.getAnioPublicacion();
            respuesta = true;
        }

        return respuesta;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idAutor=" + idAutor +
                ", genero='" + genero + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                '}';
    }
}
