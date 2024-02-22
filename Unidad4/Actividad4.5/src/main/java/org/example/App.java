package org.example;

import entidades.Articulo;
import entidades.Comprador;
import entidades.Concesionario;
import entidades.Vehiculo;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class App 
{
    public static void main( String[] args )
    {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        Session miSession = abrirSession();
        Transaction transaccion = null;
        ArrayList<Vehiculo> vehiculosConcesionario = new ArrayList<>();
        try {

            Concesionario concesionarioCreado = agregarConcesionario(transaccion,miSession);
            agregar3Coches(concesionarioCreado,vehiculosConcesionario,transaccion,miSession);
            realizarVenta(miSession,transaccion);

        }catch (Exception e){
            e.printStackTrace();
            transaccion.rollback();
        }
    }

    static Session abrirSession(){
        Session session = null;
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        return session;
    }

    public static Concesionario agregarConcesionario(Transaction transaccion,Session miSession){

        Concesionario nuevoConcesionario = new Concesionario();
        if (miSession != null) {
            System.out.println("Se ha iniciado la sesión");
            transaccion = miSession.beginTransaction();
            nuevoConcesionario.setNombreComercial("Llabadaba");
            nuevoConcesionario.setNombreEmpresarial("Empresa5");
            nuevoConcesionario.setEmail("Concesionario5@gmail.com");
            nuevoConcesionario.setNumTrabajadores(2330);
            nuevoConcesionario.setDireccionConcesionario("Calle latarara 8");
            miSession.persist(nuevoConcesionario);
            miSession.flush();
            transaccion.commit();
        } else {
            System.out.println("No se pudo encontrar la sesion");
        }

        return nuevoConcesionario;

    }

    public static void agregar3Coches(Concesionario concesionario,ArrayList<Vehiculo> vehiculosConcesionario,Transaction transaccion,Session miSession){
        transaccion = miSession.beginTransaction();
        for (int i = 0; i < 3; i++) {
            Vehiculo nuevoVehiculo = new Vehiculo();
            nuevoVehiculo.setMarca("Marca" + i);
            nuevoVehiculo.setModelo("Modelo" + i);
            nuevoVehiculo.setColor("Blanco");
            nuevoVehiculo.setMatricula((int)(Math.random()*9999) + concesionario.getNombreComercial().substring(0,3).toUpperCase());
            nuevoVehiculo.setFechaMatriculacion(Date.from(Instant.now()));
            nuevoVehiculo.setConcesionarioEnPropiedad(concesionario);
            vehiculosConcesionario.add(nuevoVehiculo);
            miSession.persist(nuevoVehiculo);
            miSession.flush();

        }
        transaccion.commit();
    }

    public static void realizarVenta(Session miSession,Transaction transaccion){
        transaccion = miSession.beginTransaction();
        Comprador nuevoComprador = new Comprador();
        Articulo nuevoArticulo = new Articulo();
        nuevoComprador.setNombre("Juanma");
        nuevoComprador.setTelefono("112233456");
        nuevoArticulo.setDescripcion("Bueno,bonito,barato");
        nuevoArticulo.setPrecioVenta(10);
        nuevoArticulo.setArticulos(nuevoComprador);
        nuevoComprador.setEsCompradoPor(nuevoArticulo);
        miSession.persist(nuevoComprador);
        miSession.persist(nuevoArticulo);
        transaccion.commit();

    }

}
