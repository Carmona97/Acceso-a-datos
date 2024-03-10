package org.example.vista;

import org.example.Negocio.Libro;

import java.sql.SQLException;
import java.util.Scanner;

public class VistaLibro {
    private Libro libro = new Libro();

    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Libros");
        System.out.println("1. Mostrar Libros");
        System.out.println("2. Agregar Libro");
        System.out.println("3. Actualizar Libro");
        System.out.println("4. Eliminar Libro");
        System.out.println("0. Volver al menú principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudLibros() throws Exception {
        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(libro.mostrarLibrosLogica());
                    break;
                case 2:
                    this.agregarLibro();
                    break;
                case 3:
                    this.actualizarLibro();
                    break;
                case 4:
                    this.eliminarLibro();
                    break;
                case 0:
                    System.out.println("¡Volviendo al menú principal!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elija nuevamente.");
            }
        }
    }

    public void agregarLibro() {
        Scanner scanner = new Scanner(System.in);
        Libro nuevoLibro = new Libro();

        System.out.print("Ingrese el ID del libro: ");
        nuevoLibro.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese el título del libro: ");
        nuevoLibro.setTitulo(scanner.nextLine());
        System.out.print("Ingrese el ID del autor del libro: ");
        nuevoLibro.setIdAutor(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese el género del libro: ");
        nuevoLibro.setGenero(scanner.nextLine());
        System.out.print("Ingrese el año de publicación del libro: ");
        nuevoLibro.setAnioPublicacion(scanner.nextInt());

        try {
            if (nuevoLibro.agregarLibro()) {
                System.out.println("Libro agregado correctamente");
            } else {
                System.out.println("Error al crear el libro");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarLibro() throws Exception {
        System.out.println(libro.mostrarLibrosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del libro que desea actualizar: ");
        int libroId = scanner.nextInt();
        scanner.nextLine();
        libro.setId(libroId);

        if (libro.cargarLibro()) {
            System.out.print("Ingrese el nuevo título del libro (" + libro.getTitulo() + "): ");
            String nuevoTitulo = scanner.nextLine();
            System.out.print("Ingrese el nuevo ID del autor del libro (" + libro.getIdAutor() + "): ");
            int nuevoIdAutor = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el nuevo género del libro (" + libro.getGenero() + "): ");
            String nuevoGenero = scanner.nextLine();
            System.out.print("Ingrese el nuevo año de publicación del libro (" + libro.getAnioPublicacion() + "): ");
            int nuevoAnioPublicacion = scanner.nextInt();

            libro.setTitulo(nuevoTitulo);
            libro.setIdAutor(nuevoIdAutor);
            libro.setGenero(nuevoGenero);
            libro.setAnioPublicacion(nuevoAnioPublicacion);
        }

        if (libro.actualizarLibro()) {
            System.out.println("Libro actualizado correctamente");
        } else {
            System.out.println("Error al actualizar el libro");
        }
    }

    public void eliminarLibro() throws Exception {
        System.out.println(libro.mostrarLibrosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del libro que desea eliminar: ");
        int libroId = scanner.nextInt();
        scanner.nextLine();
        libro.setId(libroId);

        if (libro.cargarLibro()) {
            if (libro.eliminarLibro()) {
                System.out.println("Libro eliminado correctamente");
            } else {
                System.out.println("Error al eliminar el libro");
            }
        } else {
            System.out.println("No se encontró un libro con el ID proporcionado.");
        }
    }
}
