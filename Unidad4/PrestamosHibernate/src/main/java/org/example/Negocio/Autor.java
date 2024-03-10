package org.example.Negocio;

import org.example.Entidades.EntidadAutores;

import java.sql.SQLException;
import java.util.ArrayList;

public class Autor {
    private int id;
    private String nombreAutor;
    private String pais;

    public Autor(int id, String nombreAutor, String pais) {
        this.id = id;
        this.nombreAutor = nombreAutor;
        this.pais = pais;
    }

    public Autor() {
    }

    public int getId() {
        return id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public String getPais() {
        return pais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    public String mostrarAutoresLogica() throws Exception {
        String respuesta = "";
        EntidadAutores entidadAutores = new EntidadAutores();
        ArrayList<Autor> autoresLogica = entidadAutores.mostrarAutoresPersistencia();

        for (Autor autor : autoresLogica) {
            respuesta = respuesta + "\n" + autor.toString();
        }

        return respuesta;
    }

    public boolean agregarAutor() throws SQLException {
        boolean respuesta = false;

        EntidadAutores autorNuevo = new EntidadAutores();
        respuesta = autorNuevo.agregarAutor(this);

        return respuesta;
    }
    public boolean actualizarAutor() throws Exception {
        boolean respuesta = false;

        EntidadAutores entidadAutores = new EntidadAutores();
        entidadAutores.setIdAutor(this.id);
        entidadAutores.setNombreAutor(this.nombreAutor);
        entidadAutores.setPais(this.pais);
        respuesta = entidadAutores.actualizarAutor(entidadAutores);

        return respuesta;
    }

    public boolean eliminarAutor() throws Exception {
        boolean respuesta = false;

        EntidadAutores entidadAutores = new EntidadAutores();
        entidadAutores.setIdAutor(this.id);
        entidadAutores.setNombreAutor(this.nombreAutor);
        entidadAutores.setPais(this.pais);
        respuesta = entidadAutores.eliminarAutor(entidadAutores);

        return respuesta;
    }



    public boolean cargarAutor() throws Exception {
        boolean respuesta = false;

        EntidadAutores entidadAutores = new EntidadAutores();
        EntidadAutores autor = entidadAutores.cargarAutor(this.id);
        if (autor != null) {
            this.id = autor.getIdAutor();
            this.nombreAutor = autor.getNombreAutor();
            this.pais = autor.getPais();

            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombreAutor='" + nombreAutor + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
