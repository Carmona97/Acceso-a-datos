package org.example.Entidades;

import jakarta.persistence.*;
import org.example.Negocio.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros", schema = "public", catalog = "Prestamos")
public class EntidadLibros {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_libro", nullable = false)
    private int idLibro;
    @Basic
    @Column(name = "titulo", nullable = true, length = 255)
    private String titulo;
    @Basic
    @Column(name = "id_autor", nullable = true)
    private Integer idAutor;
    @Basic
    @Column(name = "genero", nullable = true, length = 50)
    private String genero;
    @Basic
    @Column(name = "anio_publicacion", nullable = true)
    private Integer anioPublicacion;
    @ManyToOne
    @JoinColumn(name = "id_autor", referencedColumnName = "id_autor", insertable = false, updatable = false)
    private EntidadAutores autoresByIdAutor;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public EntidadAutores getAutoresByIdAutor() {
        return autoresByIdAutor;
    }

    public void setAutoresByIdAutor(EntidadAutores autoresByIdAutor) {
        this.autoresByIdAutor = autoresByIdAutor;
    }

    public ArrayList<Libro> mostrarLibrosPersistencia() throws Exception {
        ArrayList<Libro> listaLibrosLogica = new ArrayList<>();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            // Consulta para obtener todos los libros
            List<EntidadLibros> listaLibrosPersistencia = miSesion.createNativeQuery("SELECT * FROM libros", EntidadLibros.class).list();

            // Convertir los libros de la persistencia a objetos de tipo Libro
            for (EntidadLibros entidadLibros : listaLibrosPersistencia) {
                Libro libro = new Libro();
                libro.setId(entidadLibros.getIdLibro());
                libro.setTitulo(entidadLibros.getTitulo());
                libro.setIdAutor(entidadLibros.getIdAutor());
                libro.setGenero(entidadLibros.getGenero());
                libro.setAnioPublicacion(entidadLibros.getAnioPublicacion());

                listaLibrosLogica.add(libro);
            }
        }

        return listaLibrosLogica;
    }

    public boolean agregarLibro(EntidadLibros libro) {
        boolean agregadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.persist(libro);
            transaction.commit();
        } catch (Exception e) {
            agregadoConExito = false;
            e.printStackTrace();
        }

        return agregadoConExito;
    }

    public boolean actualizarLibro(EntidadLibros libro) {
        boolean actualizadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.saveOrUpdate(libro);
            transaction.commit();
        } catch (Exception e) {
            actualizadoConExito = false;
            e.printStackTrace();
        }

        return actualizadoConExito;
    }

    public boolean eliminarLibro(EntidadLibros libro) {
        boolean eliminadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.delete(libro);
            transaction.commit();
        } catch (Exception e) {
            eliminadoConExito = false;
            e.printStackTrace();
        }

        return eliminadoConExito;
    }

    public EntidadLibros cargarLibro(int id) {
        EntidadLibros libro = new EntidadLibros();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            List<EntidadLibros> librosSeleccionados = miSesion.createNativeQuery("SELECT * FROM libros WHERE id_libro = ?", EntidadLibros.class).setParameter(1, id).list();

            if (!librosSeleccionados.isEmpty()) {
                libro = librosSeleccionados.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return libro;
    }
}
