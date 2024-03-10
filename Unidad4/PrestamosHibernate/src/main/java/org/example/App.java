package org.example;

import org.example.vista.VistaAutor;
import org.example.vista.VistaLibro;
import org.example.vista.VistaPrestamo;
import org.example.vista.VistaUsuario;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int eleccion = 0;
        VistaAutor vistaAutor = new VistaAutor();
        VistaLibro vistaLibro = new VistaLibro();
        VistaUsuario vistaUsuario = new VistaUsuario();
        VistaPrestamo vistaPrestamo = new VistaPrestamo();


        do{
            System.out.println("Que desea hacer?\n0.Salir\n1.Autores\n2.Libros\n3.Usuarios\n4.Prestamos");
            eleccion = scn.nextInt();
            switch (eleccion){
                case 1 -> {
                    try {
                        vistaAutor.crudAutores();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 ->{
                    try {
                        vistaLibro.crudLibros();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 ->{
                    try {
                        vistaUsuario.crudUsuarios();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 ->{
                    try {
                        vistaPrestamo.crudPrestamos();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 0 ->{
                    System.out.println("Saliendo...");
                }
                default -> {
                    System.out.println("Opcion erronea, vuelva a intentarlo");
                }
            }
        }
        while (eleccion != 0) ;
    }
}
