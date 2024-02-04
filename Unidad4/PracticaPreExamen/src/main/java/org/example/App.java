package org.example;

import org.example.Entidades.JPAPersistencia;
import org.example.vista.VistaAgencia;
import org.example.vista.VistaCliente;
import org.example.vista.VistaDestino;
import org.hibernate.Session;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws Exception {
        Scanner scn = new Scanner(System.in);
        int opcion = 0;
        Session miSesion = JPAPersistencia.abrirSession();
        JPAPersistencia.eliminarWarnings();
        miSesion.close();
        VistaAgencia menuAgencia = new VistaAgencia();
        VistaCliente menuCliente = new VistaCliente();
        VistaDestino menuDestino = new VistaDestino();

        do {
            System.out.println("Indique el menú al que desea acceder:\n1.Agencia\n2.Cliente\n3.Destino");
            opcion = scn.nextInt();

            switch (opcion){
                case 0 -> System.out.println("Hasta pronto!");
                case 1 -> menuAgencia.crudAgencia();
                case 2 -> menuCliente.crudCliente();
                case 3 -> menuDestino.crudDestino();
                default -> System.out.println("Elija una opcion valida");
            }
        } while (opcion != 0);


    }



}
