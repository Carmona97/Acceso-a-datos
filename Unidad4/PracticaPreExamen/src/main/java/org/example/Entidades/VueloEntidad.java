package org.example.Entidades;

import jakarta.persistence.*;
import org.example.negocio.Vuelo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vuelos",schema = "public", catalog = "TuViajePreExamen")
public class VueloEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vuelo_id", nullable = false)
    private int idVuelo;

    @Basic
    @Column(name = "origen", nullable = false)
    private String origenVuelo;

    @Basic
    @Column(name = "destino", nullable = false)
    private String destinoVuelo;

    @Basic
    @Column(name = "fecha_salida", nullable = false)
    private Date fechaSalidaVuelo;

    @Basic
    @Column(name = "fecha_llegada", nullable = false)
    private Date fechaLlegadaVuelo;

    @Basic
    @Column(name = "coste", nullable = false)
    private double costeVuelo;

    public VueloEntidad() {

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


    public ArrayList<Vuelo> mostrarVuelosPersistencia() throws Exception {

        ArrayList<VueloEntidad> listaVueloPersistencia = new ArrayList<>();
        ArrayList<Vuelo> listaVueloLogica = new ArrayList<>();

        Session miSesion = JPAPersistencia.abrirSession();
        listaVueloPersistencia = (ArrayList<VueloEntidad>) miSesion.createNativeQuery("SELECT * FROM vuelos", VueloEntidad.class).list();
        for(VueloEntidad VueloPersistencia : listaVueloPersistencia){
            Vuelo agregarElementoLogica = new Vuelo();
            agregarElementoLogica.setIdVuelo(VueloPersistencia.getIdVuelo());
            agregarElementoLogica.setOrigenVuelo(VueloPersistencia.getOrigenVuelo());
            agregarElementoLogica.setDestinoVuelo((VueloPersistencia.getDestinoVuelo()));
            agregarElementoLogica.setFechaSalidaVuelo(VueloPersistencia.getFechaSalidaVuelo());
            agregarElementoLogica.setFechaLlegadaVuelo(VueloPersistencia.getFechaLlegadaVuelo());
            agregarElementoLogica.setCosteVuelo(VueloPersistencia.getCosteVuelo());

            listaVueloLogica.add(agregarElementoLogica);
        }

        miSesion.close();

        return listaVueloLogica;
    }

    public boolean existeVuelo(){
        boolean existe = true;
        List<VueloEntidad> comprobarCantidad = new ArrayList<>();
        try (Session miSesion = JPAPersistencia.abrirSession()) {
            comprobarCantidad = miSesion.createNativeQuery("SELECT * FROM Vuelos WHERE vuelo_id = ?", VueloEntidad.class).setParameter(1, idVuelo).list();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (comprobarCantidad.size() != 1){
            existe = false;
        }
        return existe;
    }

    public Vuelo mostrarVuelo(){
        Vuelo vuelo = new Vuelo();
        VueloEntidad vueloEntidad = null;
        List<VueloEntidad> vueloSeleccionada = new ArrayList<>();
        try (Session miSesion = JPAPersistencia.abrirSession()){
            if (miSesion != null){
                vueloSeleccionada = miSesion.createNativeQuery("SELECT * FROM vuelos WHERE vuelo_id = ?",VueloEntidad.class).setParameter(1,idVuelo).list();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        vueloEntidad = vueloSeleccionada.get(0);

        vuelo.setIdVuelo(vueloEntidad.getIdVuelo());
        vuelo.setOrigenVuelo(vueloEntidad.getOrigenVuelo());
        vuelo.setDestinoVuelo(vueloEntidad.getDestinoVuelo());
        vuelo.setFechaLlegadaVuelo(vueloEntidad.getFechaLlegadaVuelo());
        vuelo.setFechaSalidaVuelo(vueloEntidad.getFechaSalidaVuelo());
        vuelo.setCosteVuelo(vueloEntidad.getCosteVuelo());

        return vuelo;

    }

    public boolean agregarVuelo(Vuelo vuelo){
        boolean anadidaConExito = true;
        VueloEntidad nuevoVuelo = new VueloEntidad();
        Transaction transaccion = null;
        if(!nuevoVuelo.existeVuelo()){
            try (Session miSesion = JPAPersistencia.abrirSession()){
                transaccion = miSesion.beginTransaction();
                nuevoVuelo.idVuelo = vuelo.getIdVuelo();
                nuevoVuelo.origenVuelo = vuelo.getOrigenVuelo();
                nuevoVuelo.destinoVuelo = vuelo.getDestinoVuelo();
                nuevoVuelo.fechaSalidaVuelo = (Date) vuelo.getFechaSalidaVuelo();
                nuevoVuelo.fechaLlegadaVuelo = (Date) vuelo.getFechaLlegadaVuelo();
                nuevoVuelo.costeVuelo = vuelo.getCosteVuelo();

                miSesion.persist(nuevoVuelo);

                miSesion.flush();
                transaccion.commit();

            } catch (Exception e) {
                anadidaConExito = false;
            }

        }
        return anadidaConExito;
    }
    public boolean actualizarVuelo(Vuelo vuelo) throws SQLException {
        boolean actualizadoConExito = true;
        Transaction transaccion = null;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            transaccion = miSesion.beginTransaction();

            VueloEntidad vueloEntidad = miSesion.get(VueloEntidad.class, vuelo.getIdVuelo());

            if (vueloEntidad != null) {
                vueloEntidad.setOrigenVuelo(vuelo.getOrigenVuelo());
                vueloEntidad.setDestinoVuelo(vuelo.getDestinoVuelo());
                vueloEntidad.setFechaSalidaVuelo((Date) vuelo.getFechaSalidaVuelo());
                vueloEntidad.setFechaLlegadaVuelo((Date) vuelo.getFechaLlegadaVuelo());
                vueloEntidad.setCosteVuelo(vueloEntidad.getCosteVuelo());

                miSesion.saveOrUpdate(vueloEntidad);
                transaccion.commit();
            } else {
                System.out.println("La Vuelo con ID " + vuelo.getIdVuelo() + " no existe en la base de datos.");
                actualizadoConExito = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            actualizadoConExito = false;
        }
        return actualizadoConExito;
    }

    public boolean eliminarVuelo(Vuelo vuelo){
        boolean eliminadoConExito = true;
        Transaction transaccion = null;
        try(Session miSesion = JPAPersistencia.abrirSession()){
            transaccion = miSesion.beginTransaction();
            VueloEntidad vueloEntidad = miSesion.get(VueloEntidad.class, vuelo.getIdVuelo());
            miSesion.delete(vueloEntidad);
            transaccion.commit();

        }catch(Exception e){
            eliminadoConExito = false;
        }
        return eliminadoConExito;
    }

    public Vuelo cargarVuelo (int id) throws SQLException {
        Vuelo vuelo = new Vuelo();
        VueloEntidad vueloEntidad = null;
        List<VueloEntidad> vueloEntidadLista = new ArrayList<>();
        try (Session miSesion = JPAPersistencia.abrirSession()){
            if (miSesion != null){
                vueloEntidadLista = miSesion.createNativeQuery("SELECT * FROM vuelos WHERE vuelo_id = ?",VueloEntidad.class).setParameter(1,id).list();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        vueloEntidad = vueloEntidadLista.get(0);

        vuelo.setIdVuelo(vueloEntidad.getIdVuelo());
        vuelo.setOrigenVuelo(vueloEntidad.getOrigenVuelo());
        vuelo.setDestinoVuelo(vueloEntidad.getDestinoVuelo());
        vuelo.setFechaSalidaVuelo(vueloEntidad.getFechaSalidaVuelo());
        vuelo.setFechaLlegadaVuelo(vueloEntidad.getFechaLlegadaVuelo());
        vuelo.setCosteVuelo(vueloEntidad.getCosteVuelo());

        return vuelo;
    }
}
