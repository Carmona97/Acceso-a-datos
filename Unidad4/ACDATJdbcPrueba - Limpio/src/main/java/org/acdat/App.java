package org.acdat;

import org.acdat.entitiesJPA.JPAPersistencia;
import org.acdat.jdbc.MiJDBC;
import org.acdat.vista.VistaCliente;
import org.acdat.vista.VistaVuelo;
import org.hibernate.Session;

import java.nio.charset.MalformedInputException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        Session miSesion = JPAPersistencia.abrirSession();
        JPAPersistencia.eliminarWarnings();
        VistaCliente vistaCliente = new VistaCliente();
        VistaVuelo vistaVuelo = new VistaVuelo();

        //vistaCliente.crudCliente();
        vistaVuelo.crudVuelo();
    }
}
