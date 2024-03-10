package org.example.negocio;

import org.example.Entidades.VueloEntidad;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Vuelo {
    private int idVuelo;
    private String origenVuelo;
    private String destinoVuelo;
    private Date fechaSalidaVuelo;
    private Date fechaLlegadaVuelo;
    private double costeVuelo;

    public Vuelo(int idVuelo, String origenVuelo, String destinoVuelo, Date fechaSalidaVuelo, Date fechaLlegadaVuelo, double costeVuelo) {
        this.idVuelo = idVuelo;
        this.origenVuelo = origenVuelo;
        this.destinoVuelo = destinoVuelo;
        this.fechaSalidaVuelo = fechaSalidaVuelo;
        this.fechaLlegadaVuelo = fechaLlegadaVuelo;
        this.costeVuelo = costeVuelo;
    }

    public Vuelo() {
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOrigenVuelo() {
        return origenVuelo;
    }

    public void setOrigenVuelo(String origenVuelo) {
        this.origenVuelo = origenVuelo;
    }

    public String getDestinoVuelo() {
        return destinoVuelo;
    }

    public void setDestinoVuelo(String destinoVuelo) {
        this.destinoVuelo = destinoVuelo;
    }

    public Date getFechaSalidaVuelo() {
        return fechaSalidaVuelo;
    }

    public void setFechaSalidaVuelo(Date fechaSalidaVuelo) {
        this.fechaSalidaVuelo = fechaSalidaVuelo;
    }

    public Date getFechaLlegadaVuelo() {
        return fechaLlegadaVuelo;
    }

    public void setFechaLlegadaVuelo(Date fechaLlegadaVuelo) {
        this.fechaLlegadaVuelo = fechaLlegadaVuelo;
    }

    public double getCosteVuelo() {
        return costeVuelo;
    }

    public void setCosteVuelo(double costeVuelo) {
        this.costeVuelo = costeVuelo;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "idVuelo=" + idVuelo +
                ", origenVuelo='" + origenVuelo + '\'' +
                ", destinoVuelo='" + destinoVuelo + '\'' +
                ", fechaSalidaVuelo=" + fechaSalidaVuelo +
                ", fechaLlegadaVuelo=" + fechaLlegadaVuelo +
                ", costeVuelo=" + costeVuelo +
                '}';
    }

    public boolean existeVuelo() throws SQLException {
        boolean respuesta = false;
        // Supongamos que existe una clase VueloEntidad similar a DestinoEntidad
        VueloEntidad vueloJPAEntity = new VueloEntidad();
        respuesta = vueloJPAEntity.existeVuelo();
        return respuesta;
    }

    public String mostrarVuelosLogica() throws Exception {
        String respuesta = "";
        // Supongamos que existe una clase VueloEntidad similar a DestinoEntidad
        VueloEntidad vueloPersistencia = new VueloEntidad();
        ArrayList<Vuelo> vueloLogica = vueloPersistencia.mostrarVuelosPersistencia();
        for (Vuelo vuelo : vueloLogica) {
            respuesta = respuesta + "\n" + vuelo.toString();
        }
        return respuesta;
    }

    public boolean agregarVuelo() throws SQLException {
        boolean respuesta = false;
        // Supongamos que existe una clase VueloEntidad similar a DestinoEntidad
        VueloEntidad vueloNuevo = new VueloEntidad();
        respuesta = vueloNuevo.agregarVuelo(this);
        return respuesta;
    }

    public boolean actualizarVuelo() throws SQLException {
        boolean respuesta = false;
        // Supongamos que existe una clase VueloEntidad similar a DestinoEntidad
        VueloEntidad vueloJPAEntity = new VueloEntidad();
        respuesta = vueloJPAEntity.actualizarVuelo(this);
        return respuesta;
    }

    public boolean eliminarVuelo() throws SQLException {
        boolean respuesta = false;
        // Supongamos que existe una clase VueloEntidad similar a DestinoEntidad
        VueloEntidad vueloJPAEntity = new VueloEntidad();
        respuesta = vueloJPAEntity.eliminarVuelo(this);
        return respuesta;
    }

    public boolean cargarVuelo() throws SQLException {
        boolean respuesta = false;
        // Supongamos que existe una clase VueloEntidad similar a DestinoEntidad
        VueloEntidad vueloJPAEntity = new VueloEntidad();
        Vuelo vuelo = vueloJPAEntity.cargarVuelo(this.idVuelo);
        if (vuelo != null) {
            this.idVuelo = vuelo.idVuelo;
            this.origenVuelo = vuelo.origenVuelo;
            this.destinoVuelo = vuelo.destinoVuelo;
            this.fechaSalidaVuelo = vuelo.fechaSalidaVuelo;
            this.fechaLlegadaVuelo = vuelo.fechaLlegadaVuelo;
            this.costeVuelo = vuelo.costeVuelo;
            respuesta = true;
        }
        return respuesta;
    }
}
