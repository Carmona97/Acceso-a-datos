package org.example.Entidades;

import jakarta.persistence.*;
import org.example.Negocio.Autor;
import org.example.Negocio.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios", schema = "public", catalog = "Prestamos")
public class EntidadUsuarios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "nombre_usuario", nullable = true, length = 255)
    private String nombreUsuario;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
/*    @OneToMany(mappedBy = "usuariosByIdUsuario")
    private Collection<EntidadPrestamos> prestamosByIdUsuario;*/

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

/*    public Collection<EntidadPrestamos> getPrestamosByIdUsuario() {
        return prestamosByIdUsuario;
    }

    public void setPrestamosByIdUsuario(Collection<EntidadPrestamos> prestamosByIdUsuario) {
        this.prestamosByIdUsuario = prestamosByIdUsuario;
    }*/

    public ArrayList<Usuario> mostrarUsuariosPersistencia() throws Exception {
        ArrayList<Usuario> listaUsuariosLogica = new ArrayList<>();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            List<EntidadUsuarios> listaUsuariosPersistencia = miSesion.createNativeQuery("SELECT * FROM usuarios", EntidadUsuarios.class).list();

            for (EntidadUsuarios entidadUsuarios : listaUsuariosPersistencia) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(entidadUsuarios.getIdUsuario());
                usuario.setNombreUsuario(entidadUsuarios.getNombreUsuario());
                usuario.setEmail(entidadUsuarios.getEmail());

                listaUsuariosLogica.add(usuario);
            }
        }

        return listaUsuariosLogica;
    }

    public boolean agregarUsuario(Usuario usuario) {
        boolean agregadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            agregadoConExito = false;
            e.printStackTrace();
        }

        return agregadoConExito;
    }

    public boolean actualizarUsuario(EntidadUsuarios usuario) {
        boolean actualizadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.saveOrUpdate(usuario);
            transaction.commit();
        } catch (Exception e) {
            actualizadoConExito = false;
            e.printStackTrace();
        }

        return actualizadoConExito;
    }

    public boolean eliminarUsuario(EntidadUsuarios usuario) {
        boolean eliminadoConExito = true;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            Transaction transaction = miSesion.beginTransaction();
            miSesion.delete(usuario);
            transaction.commit();
        } catch (Exception e) {
            eliminadoConExito = false;
            e.printStackTrace();
        }

        return eliminadoConExito;
    }

    public EntidadUsuarios cargarUsuario(int id) {
        EntidadUsuarios usuario = new EntidadUsuarios();

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            List<EntidadUsuarios> usuariosSeleccionados = miSesion.createNativeQuery("SELECT * FROM usuarios WHERE id_usuario = ?", EntidadUsuarios.class).setParameter(1, id).list();

            if (!usuariosSeleccionados.isEmpty()) {
                usuario = usuariosSeleccionados.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }
}
