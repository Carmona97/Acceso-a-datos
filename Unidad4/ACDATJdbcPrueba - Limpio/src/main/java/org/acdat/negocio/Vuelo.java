package org.acdat.negocio;

import org.acdat.entitiesJPA.VueloJPA;
import org.acdat.jdbc.VueloDao;
import org.acdat.jdbc.MiJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vuelo {
    protected int id;
    protected String origen;
    protected String destino;
    protected String fecha_salida;

    protected String fecha_llegada;

    protected double costo;


    public Vuelo(int id, String origen, String destino, String fecha_salida, String fecha_llegada, double costo) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.fecha_llegada = fecha_llegada;
        this.costo = costo;
    }

    public Vuelo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }


    public boolean existeVuelo() throws SQLException {
        boolean respuesta = false;
        VueloJPA agenciaJPAEntity = new VueloJPA();
        respuesta = agenciaJPAEntity.existeVuelo();

        return respuesta;
    }

    public String mostrarVuelosLogica() throws Exception {
        String respuesta="";
        VueloJPA agenciaPersistencia = new VueloJPA();
        ArrayList<Vuelo> vueloLogica = agenciaPersistencia.mostrarVuelosPersistencia();

        for (Vuelo vuelo : vueloLogica) {
            respuesta = respuesta + "\n" + vuelo.toString();
        }

        return respuesta;
    }


    public boolean  agregarVuelo() throws SQLException {
        boolean respuesta = false;

        VueloJPA vueloNuevo = new VueloJPA();
        respuesta = vueloNuevo.agregarVuelo(this);

        return respuesta;
    }

    public boolean  actualizarVuelo() throws SQLException {
        boolean respuesta = false;

        VueloJPA agenciaJPAEntity = new VueloJPA();
        respuesta = agenciaJPAEntity.actualizarVuelo(this);

        return respuesta;
    }

    public boolean  eliminarVuelo() throws SQLException {
        boolean respuesta = false;

        VueloJPA agenciaJPAEntity = new VueloJPA();
        respuesta = agenciaJPAEntity.eliminarVuelo(this);

        return respuesta;
    }



    public boolean cargarVuelo() throws SQLException {
        boolean respuesta = false;

        VueloJPA vueloEntidad = new VueloJPA();
        Vuelo vuelo = vueloEntidad.cargarVuelo(this.id);
        if (vuelo != null){
            this.id = vuelo.id;
            this.origen = vuelo.getOrigen();
            this.destino = vuelo.getDestino();
            this.fecha_salida = vuelo.getFecha_salida();
            this.fecha_llegada = vuelo.getFecha_llegada();
            this.costo = vuelo.getCosto();

            respuesta = true;
        }
        return respuesta;
    }

}
