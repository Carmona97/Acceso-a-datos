package org.acdat.entitiesJPA;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.acdat.negocio.Vuelo;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table(name = "vuelos",schema = "public", catalog = "HibernatePrueba")
public class VueloJPA {
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

    public VueloJPA() {

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

        ArrayList<VueloJPA> listaVueloPersistencia = new ArrayList<>();
        ArrayList<Vuelo> listaVueloLogica = new ArrayList<>();

        Session miSesion = JPAPersistencia.abrirSession();
        JPAPersistencia.eliminarWarnings();
        listaVueloPersistencia = (ArrayList<VueloJPA>) miSesion.createNativeQuery("SELECT * FROM vuelos", VueloJPA.class).list();
        for(VueloJPA VueloPersistencia : listaVueloPersistencia){
            Vuelo agregarElementoLogica = new Vuelo();
            agregarElementoLogica.setId(VueloPersistencia.getIdVuelo());
            agregarElementoLogica.setOrigen(VueloPersistencia.getOrigenVuelo());
            agregarElementoLogica.setDestino(VueloPersistencia.getDestinoVuelo());
            agregarElementoLogica.setFecha_salida(VueloPersistencia.getFechaSalidaVuelo().toString());
            agregarElementoLogica.setFecha_llegada(VueloPersistencia.getFechaLlegadaVuelo().toString());
            agregarElementoLogica.setCosto(VueloPersistencia.getCosteVuelo());

            listaVueloLogica.add(agregarElementoLogica);
        }

        miSesion.close();

        return listaVueloLogica;
    }

    public boolean existeVuelo(){
        boolean existe = true;
        List<VueloJPA> comprobarCantidad = new ArrayList<>();
        try (Session miSesion = JPAPersistencia.abrirSession()) {
            JPAPersistencia.eliminarWarnings();
            comprobarCantidad = miSesion.createNativeQuery("SELECT * FROM Vuelos WHERE vuelo_id = ?", VueloJPA.class).setParameter(1, idVuelo).list();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (comprobarCantidad.size() != 1){
            existe = false;
        }
        return existe;
    }

    public Vuelo mostrarVuelo(){
        Vuelo Vuelo = new Vuelo();
        VueloJPA VueloJPA = null;
        List<VueloJPA> VueloSeleccionada = new ArrayList<>();
        try (Session miSesion = JPAPersistencia.abrirSession()){
            JPAPersistencia.eliminarWarnings();
            if (miSesion != null){
                VueloSeleccionada = miSesion.createNativeQuery("SELECT * FROM vuelos WHERE vuelo_id = ?",VueloJPA.class).setParameter(1,idVuelo).list();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        VueloJPA = VueloSeleccionada.get(0);

        Vuelo.setId(VueloJPA.getIdVuelo());
        Vuelo.setOrigen(VueloJPA.getOrigenVuelo());
        Vuelo.setDestino(VueloJPA.getDestinoVuelo());
        Vuelo.setFecha_llegada(VueloJPA.getFechaLlegadaVuelo().toString());
        Vuelo.setFecha_salida(VueloJPA.getFechaSalidaVuelo().toString());
        Vuelo.setCosto(VueloJPA.getCosteVuelo());

        return Vuelo;

    }

    public boolean agregarVuelo(Vuelo vuelo){
        boolean anadidaConExito = true;
        VueloJPA nuevoVuelo = new VueloJPA();
        Transaction transaccion = null;
        if(!nuevoVuelo.existeVuelo()){
            try (Session miSesion = JPAPersistencia.abrirSession()){
                JPAPersistencia.eliminarWarnings();
                transaccion = miSesion.beginTransaction();
                nuevoVuelo.idVuelo = vuelo.getId();
                nuevoVuelo.origenVuelo = vuelo.getOrigen();
                nuevoVuelo.destinoVuelo = vuelo.getDestino();
                nuevoVuelo.fechaSalidaVuelo = Date.valueOf(vuelo.getFecha_salida());
                nuevoVuelo.fechaLlegadaVuelo = Date.valueOf(vuelo.getFecha_llegada());
                nuevoVuelo.costeVuelo = vuelo.getCosto();

                miSesion.persist(nuevoVuelo);

                miSesion.flush();
                transaccion.commit();

            } catch (Exception e) {
                anadidaConExito = false;
            }

        }
        return anadidaConExito;
    }
    public boolean actualizarVuelo(Vuelo Vuelo) throws SQLException {
        boolean actualizadoConExito = true;
        Transaction transaccion = null;

        try (Session miSesion = JPAPersistencia.abrirSession()) {
            transaccion = miSesion.beginTransaction();

            VueloJPA VueloJPA = miSesion.get(VueloJPA.class, Vuelo.getId());

            if (VueloJPA != null) {
                VueloJPA.setOrigenVuelo(Vuelo.getOrigen());
                VueloJPA.setDestinoVuelo(Vuelo.getDestino());
                VueloJPA.setFechaSalidaVuelo(Date.valueOf(Vuelo.getFecha_salida()));
                VueloJPA.setDestinoVuelo(String.valueOf(Date.valueOf(Vuelo.getFecha_llegada())));
                VueloJPA.setCosteVuelo(VueloJPA.getCosteVuelo());

                miSesion.saveOrUpdate(VueloJPA);
                transaccion.commit();
            } else {
                System.out.println("La Vuelo con ID " + Vuelo.getId() + " no existe en la base de datos.");
                actualizadoConExito = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            actualizadoConExito = false;
        }
        return actualizadoConExito;
    }

    public boolean eliminarVuelo(Vuelo Vuelo){
        boolean eliminadoConExito = true;
        Transaction transaccion = null;
        try(Session miSesion = JPAPersistencia.abrirSession()){
            JPAPersistencia.eliminarWarnings();
            transaccion = miSesion.beginTransaction();
            VueloJPA VueloJPA = miSesion.get(VueloJPA.class, Vuelo.getId());
            miSesion.delete(VueloJPA);
            transaccion.commit();

        }catch(Exception e){
            eliminadoConExito = false;
        }
        return eliminadoConExito;
    }

    public Vuelo cargarVuelo (int id) throws SQLException {
        Vuelo Vuelo = new Vuelo();
        VueloJPA vueloJPA = null;
        List<VueloJPA> vueloJPALista = new ArrayList<>();
        try (Session miSesion = JPAPersistencia.abrirSession()){
            if (miSesion != null){
                vueloJPALista = miSesion.createNativeQuery("SELECT * FROM vuelos WHERE vuelo_id = ?",VueloJPA.class).setParameter(1,id).list();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        vueloJPA = vueloJPALista.get(0);

        Vuelo.setId(vueloJPA.getIdVuelo());
        Vuelo.setOrigen(vueloJPA.getOrigenVuelo());
        Vuelo.setDestino(vueloJPA.getDestinoVuelo());
        Vuelo.setFecha_salida(String.valueOf(vueloJPA.getFechaSalidaVuelo()));
        Vuelo.setFecha_llegada(String.valueOf(vueloJPA.getFechaLlegadaVuelo()));
        Vuelo.setCosto(vueloJPA.getCosteVuelo());

        return Vuelo;
    }

}
