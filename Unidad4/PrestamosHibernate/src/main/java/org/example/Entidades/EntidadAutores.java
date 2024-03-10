package org.example.Entidades;

import jakarta.persistence.*;
import org.example.Negocio.Autor;
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

    public ArrayList<Autor> mostrarAutoresPersistencia() throws Exception {

        ArrayList<Autor> listaAutoresLogica = new ArrayList<>();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            List<EntidadAutores> listaAutoresPersistencia = miSesion.createNativeQuery("SELECT * FROM autores", EntidadAutores.class).list();

            for (EntidadAutores entidadAutores : listaAutoresPersistencia) {
                Autor autor = new Autor();
                autor.setId(entidadAutores.getIdAutor());
                autor.setNombreAutor(entidadAutores.getNombreAutor());
                autor.setPais(entidadAutores.getPais());

                listaAutoresLogica.add(autor);
            }
        }

        return listaAutoresLogica;
    }

    public boolean agregarAutor(Autor autor){
        boolean anadidoConExito = true;
        EntidadAutores nuevoAutor = new EntidadAutores();
        Transaction transaction = null;
        if (!nuevoAutor.existeAutor()) {
            try (Session miSesion = JPAPersistencia.abrirSession()) {
                transaction = miSesion.beginTransaction();
                nuevoAutor.setIdAutor(autor.getId());
                nuevoAutor.setNombreAutor(autor.getNombreAutor());
                nuevoAutor.setPais(autor.getPais());
                miSesion.persist(nuevoAutor);
                miSesion.flush();
                transaction.commit();
            } catch (Exception e) {
                anadidoConExito = false;
            }
        }
        return anadidoConExito;
    }


    public boolean existeAutor(){
        boolean existe = true;
        List<EntidadAutores> comprobarCantidad = new ArrayList<>();
        try (Session miSesion = JPAPersistencia.abrirSession()) {
            comprobarCantidad = miSesion.createNativeQuery("SELECT * FROM AUTOR WHERE ID = ?", EntidadAutores.class).setParameter(1, idAutor).list();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (comprobarCantidad.size() != 1){
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
