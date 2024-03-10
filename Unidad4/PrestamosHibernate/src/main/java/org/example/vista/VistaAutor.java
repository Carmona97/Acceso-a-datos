package org.example.vista;

import org.example.Negocio.Autor;

import java.sql.SQLException;
import java.util.Scanner;

public class VistaAutor {
    Autor autor = new Autor();

    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Autores");
        System.out.println("1. Mostrar Autores");
        System.out.println("2. Agregar Autor");
        System.out.println("3. Actualizar Autor");
        System.out.println("4. Eliminar Autor");
        System.out.println("0. Volver al menú principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudAutores() throws Exception {
        String respuesta = "";

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(autor.mostrarAutoresLogica());
                    break;
                case 2:
                    this.agregarAutor();
                    break;
                case 3:
                    this.actualizarAutor();
                    break;
                case 4:
                    this.eliminarAutor();
                    break;
                case 0:
                    System.out.println("¡Volviendo al menú principal!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elija nuevamente.");
            }
            System.out.println(respuesta);
        }
    }

    public void agregarAutor() {
        Scanner scanner = new Scanner(System.in);
        Autor autor = new Autor();

        System.out.print("Ingrese el ID del autor: ");
        autor.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese el nombre del autor: ");
        autor.setNombreAutor(scanner.nextLine());
        System.out.print("Ingrese el país del autor: ");
        autor.setPais(scanner.nextLine());

        try {
            if (autor.agregarAutor()) {
                System.out.println("Autor agregado correctamente");
            } else {
                System.out.println("Error al crear el autor");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void actualizarAutor() throws Exception {
        System.out.println(autor.mostrarAutoresLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del autor que desea actualizar: ");
        int autorId = scanner.nextInt();
        scanner.nextLine();
        autor.setId(autorId);

        if (autor.cargarAutor()) {
            System.out.print("Ingrese el nuevo nombre del autor (" + autor.getNombreAutor() + "): ");
            String nuevoNombreAutor = scanner.nextLine();
            System.out.print("Ingrese el nuevo país del autor (" + autor.getPais() + "): ");
            String nuevoPais = scanner.nextLine();

            autor.setNombreAutor(nuevoNombreAutor);
            autor.setPais(nuevoPais);
        }

        try {
            if (autor.actualizarAutor()) {
                System.out.println("Autor actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el autor");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarAutor() throws Exception {
        System.out.println(autor.mostrarAutoresLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del autor que desea eliminar: ");
        int autorId = scanner.nextInt();
        scanner.nextLine();
        autor.setId(autorId);

        try {
            if(autor.cargarAutor()){
                if (autor.eliminarAutor()) {
                    System.out.println("Autor eliminado correctamente");
                } else {
                    System.out.println("Error al eliminar el autor");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
