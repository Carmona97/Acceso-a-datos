package org.example.Entidades;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores", schema = "public", catalog = "Prestamos")
public class EntidadAutores {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_autor", nullable = false)
    private int idAutor;
    @Basic
    @Column(name = "nombre_autor", nullable = true, length = 255)
    private String nombreAutor;
    @Basic
    @Column(name = "pais", nullable = true, length = 50)
    private String pais;

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<EntidadAutores> mostrarAutoresPersistencia() throws Exception {

        ArrayList<EntidadAutores> listaAutoresPersistencia = new ArrayList<>();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            listaAutoresPersistencia = (ArrayList<EntidadAutores>) miSesion.createNativeQuery("SELECT * FROM autores", EntidadAutores.class).list();
        }

        return listaAutoresPersistencia;
    }

    public boolean existeAutor() {
        boolean existe = true;
        List<EntidadAutores> comprobarCantidad = new ArrayList<>();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            comprobarCantidad = miSesion.createNativeQuery("SELECT * FROM autores WHERE id_autor = ?", EntidadAutores.class).setParameter(1, idAutor).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (comprobarCantidad.size() != 1) {
            existe = false;
        }
        return existe;
    }

    public EntidadAutores mostrarAutor() {
        EntidadAutores autor = new EntidadAutores();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            List<EntidadAutores> autoresSeleccionados = miSesion.createNativeQuery("SELECT * FROM autores WHERE id_autor = ?", EntidadAutores.class).setParameter(1, idAutor).list();

            if (!autoresSeleccionados.isEmpty()) {
                autor = autoresSeleccionados.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return autor;
    }

    public boolean agregarAutor(EntidadAutores autor) {
        boolean agregadoConExito = true;

        if (!existeAutor()) {
            try (Session miSesion = JPAPersistencia.abrirSession()) {
                Transaction transaction = miSesion.beginTransaction();
                miSesion.persist(autor);
                transaction.commit();
            } catch (Exception e) {
                agregadoConExito = false;
                e.printStackTrace();
            }
        } else {
            System.out.println("El autor ya existe en la base de datos.");
            agregadoConExito = false;
        }

        return agregadoConExito;
    }

    public boolean actualizarAutor(EntidadAutores autor) {
        boolean actualizadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.saveOrUpdate(autor);
            transaction.commit();
        } catch (Exception e) {
            actualizadoConExito = false;
            e.printStackTrace();
        }

        return actualizadoConExito;
    }

    public boolean eliminarAutor(EntidadAutores autor) {
        boolean eliminadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.delete(autor);
            transaction.commit();
        } catch (Exception e) {
            eliminadoConExito = false;
            e.printStackTrace();
        }

        return eliminadoConExito;
    }

    public EntidadAutores cargarAutor(int id) {
        EntidadAutores autor = new EntidadAutores();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            List<EntidadAutores> autoresSeleccionados = miSesion.createNativeQuery("SELECT * FROM autores WHERE id_autor = ?", EntidadAutores.class).setParameter(1, id).list();

            if (!autoresSeleccionados.isEmpty()) {
                autor = autoresSeleccionados.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return autor;
    }
}
