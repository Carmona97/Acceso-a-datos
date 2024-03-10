package org.example.Negocio;

import org.example.Entidades.EntidadAutores;
import org.example.Entidades.EntidadUsuarios;

import java.util.ArrayList;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String email;

    public Usuario(int idUsuario, String nombreUsuario, String email) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    public Usuario() {
    }

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

    public boolean agregarUsuario() {
        boolean respuesta = false;

        EntidadUsuarios usuarioNuevo = new EntidadUsuarios();
        respuesta = usuarioNuevo.agregarUsuario(this);

        return respuesta;
    }

    public String mostrarUsuariosLogica() throws Exception {
        String respuesta = "";
        EntidadUsuarios entidadUsuarios = new EntidadUsuarios();
        ArrayList<Usuario> usuariosLogica = entidadUsuarios.mostrarUsuariosPersistencia();

        for (Usuario usuario : usuariosLogica) {
            respuesta = respuesta + "\n" + usuario.toString();
        }

        return respuesta;
    }
    public boolean actualizarUsuario() {
        boolean actualizadoConExito = false;

        EntidadUsuarios entidadUsuario = new EntidadUsuarios();
        entidadUsuario.setIdUsuario(this.idUsuario);
        entidadUsuario.setNombreUsuario(this.nombreUsuario);
        entidadUsuario.setEmail(this.email);

        if (entidadUsuario.actualizarUsuario(entidadUsuario)) {
            actualizadoConExito = true;
        }

        return actualizadoConExito;
    }

    public boolean eliminarUsuario() {
        boolean eliminadoConExito = false;

        EntidadUsuarios entidadUsuario = new EntidadUsuarios();
        entidadUsuario.setIdUsuario(this.idUsuario);
        entidadUsuario.setNombreUsuario(this.nombreUsuario);
        entidadUsuario.setEmail(this.email);

        if (entidadUsuario.eliminarUsuario(entidadUsuario)) {
            eliminadoConExito = true;
        }

        return eliminadoConExito;
    }

    public boolean cargarUsuario() {
        boolean cargadoConExito = false;

        EntidadUsuarios entidadUsuario = new EntidadUsuarios();
        EntidadUsuarios usuario = entidadUsuario.cargarUsuario(this.idUsuario);

        if (usuario != null) {
            this.idUsuario = usuario.getIdUsuario();
            this.nombreUsuario = usuario.getNombreUsuario();
            this.email = usuario.getEmail();
            cargadoConExito = true;
        }

        return cargadoConExito;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
