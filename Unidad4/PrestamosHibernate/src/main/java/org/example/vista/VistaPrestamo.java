package org.example.vista;

import org.example.Negocio.Prestamo;

import java.sql.SQLException;
import java.util.Scanner;

public class VistaPrestamo {
    Prestamo prestamo = new Prestamo();

    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Préstamos");
        System.out.println("1. Mostrar Préstamos");
        System.out.println("2. Agregar Préstamo");
        System.out.println("3. Actualizar Préstamo");
        System.out.println("4. Eliminar Préstamo");
        System.out.println("0. Volver al menú principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudPrestamos() throws Exception {
        String respuesta = "";

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(prestamo.mostrarPrestamosLogica());
                    break;
                case 2:
                    this.agregarPrestamo();
                    break;
                case 3:
                    this.actualizarPrestamo();
                    break;
                case 4:
                    this.eliminarPrestamo();
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

    public void agregarPrestamo() {
        Scanner scanner = new Scanner(System.in);
        Prestamo prestamo = new Prestamo();

        System.out.print("Ingrese el ID del préstamo: ");
        prestamo.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese el ID del libro: ");
        prestamo.setIdLibro(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese la fecha de préstamo (yyyy-MM-dd): ");
        String fechaPrestamoStr = scanner.nextLine();
        prestamo.setFechaPrestamo(java.sql.Date.valueOf(fechaPrestamoStr));
        System.out.print("Ingrese la fecha de devolución (yyyy-MM-dd): ");
        String fechaDevolucionStr = scanner.nextLine();
        prestamo.setFechaDevolucion(java.sql.Date.valueOf(fechaDevolucionStr));
        System.out.print("Ingrese el ID del usuario: ");
        prestamo.setIdUsuario(scanner.nextInt());

        try {
            if (prestamo.agregarPrestamo()) {
                System.out.println("Préstamo agregado correctamente");
            } else {
                System.out.println("Error al agregar el préstamo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarPrestamo() throws Exception {
        System.out.println(prestamo.mostrarPrestamosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del préstamo que desea actualizar: ");
        int prestamoId = scanner.nextInt();
        scanner.nextLine();
        prestamo.setId(prestamoId);

        if (prestamo.cargarPrestamo()) {
            System.out.print("Ingrese el nuevo ID del libro (" + prestamo.getIdLibro() + "): ");
            int nuevoIdLibro = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese la nueva fecha de préstamo (" + prestamo.getFechaPrestamo() + " - yyyy-MM-dd): ");
            String nuevaFechaPrestamoStr = scanner.nextLine();
            java.sql.Date nuevaFechaPrestamo = java.sql.Date.valueOf(nuevaFechaPrestamoStr);
            System.out.print("Ingrese la nueva fecha de devolución (" + prestamo.getFechaDevolucion() + " - yyyy-MM-dd): ");
            String nuevaFechaDevolucionStr = scanner.nextLine();
            java.sql.Date nuevaFechaDevolucion = java.sql.Date.valueOf(nuevaFechaDevolucionStr);
            System.out.print("Ingrese el nuevo ID del usuario (" + prestamo.getIdUsuario() + "): ");
            int nuevoIdUsuario = scanner.nextInt();

            prestamo.setIdLibro(nuevoIdLibro);
            prestamo.setFechaPrestamo(nuevaFechaPrestamo);
            prestamo.setFechaDevolucion(nuevaFechaDevolucion);
            prestamo.setIdUsuario(nuevoIdUsuario);
        }

        try {
            if (prestamo.actualizarPrestamo()) {
                System.out.println("Préstamo actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el préstamo");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarPrestamo() throws Exception {
        System.out.println(prestamo.mostrarPrestamosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del préstamo que desea eliminar: ");
        int prestamoId = scanner.nextInt();
        scanner.nextLine();
        prestamo.setId(prestamoId);

        try {
            if(prestamo.cargarPrestamo()){
                if (prestamo.eliminarPrestamo()) {
                    System.out.println("Préstamo eliminado correctamente");
                } else {
                    System.out.println("Error al eliminar el préstamo");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
