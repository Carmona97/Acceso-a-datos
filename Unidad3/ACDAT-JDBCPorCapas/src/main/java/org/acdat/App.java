package org.acdat;

import org.acdat.jdbc.MiJDBC;
import org.acdat.negocio.Agencia;
import org.acdat.negocio.Cliente;
import org.acdat.vista.VistaAgencia;
import org.acdat.vista.VistaCliente;

import java.nio.charset.MalformedInputException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        Scanner scn = new Scanner(System.in);
        int menu = 0;
        Agencia agencia = new Agencia();
        Cliente cliente = new Cliente();
        cliente.eliminarTablaCliente();
        agencia.eliminarTablaAgencia();
        agencia.crearTablaAgencia();
        cliente.crearTablaCliente();

        do{
            System.out.println("Desea precargar datos? \n(1) Sí\n(2) No");
            menu =scn.nextInt();
            switch (menu){
                case 1 -> {
                    boolean cargadoConExitoClientes = false;
                    boolean cargadoConExitoAgencias = false;
                    cargadoConExitoClientes = cliente.precargarClientes();
                    cargadoConExitoAgencias = agencia.precargarAgencias();
                    if(cargadoConExitoClientes && cargadoConExitoAgencias){
                        System.out.println("Se han cargado los datos con exito");
                    }
                }
                case 2-> {
                    System.out.println("No se ha cargado ningun dato");
                }
                default -> System.out.println("No se ha seleccionado una opcion correcta");
            }


        }while(menu <1 || menu >2);
        VistaCliente vistaCliente = new VistaCliente();
        VistaAgencia vistaAgencia = new VistaAgencia();

        do {
            System.out.println("Seleccione (1) si quiere modificar la vista de Clientes.\nSeleccione (2) si quiere modificar la vista de Agencia\n(0) Para salir ");
            menu = scn.nextInt();
            switch (menu){
                case 1 -> {
                    vistaCliente.crudCliente();
                }
                case 2 -> {
                    vistaAgencia.crudAgencia();
                }
                case 0 -> {
                    System.out.println("Saliendo del programa");
                }
                default -> System.out.println("Eleccion erronea");
            }
        } while (menu!=0);
    }
}
