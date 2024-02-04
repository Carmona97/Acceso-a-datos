
package org.example.vista;

import org.example.negocio.Destino;

import java.sql.SQLException;
import java.util.Scanner;

public class VistaDestino {
    Destino destino = new Destino();

    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Destinos");
        System.out.println("1. Mostrar Destinos");
        System.out.println("2. Agregar Destino");
        System.out.println("3. Actualizar Destino");
        System.out.println("4. Eliminar Destino");
        System.out.println("0. Volver al menu principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudDestino() throws Exception {
        String respuesta = "";

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(destino.mostrarDestinosLogica());
                    break;
                case 2:
                    this.agregarDestino();
                    break;
                case 3:
                    this.actualizarDestino();
                    break;
                case 4:
                    this.eliminarDestino();
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

    public void agregarDestino() {
        Scanner scanner = new Scanner(System.in);
        Destino destino = new Destino();

        System.out.print("Ingrese el destino: ");
        destino.setDestino(scanner.nextLine());
        System.out.print("Ingrese la descripción del destino: ");
        destino.setDescripcion(scanner.nextLine());
        System.out.print("Ingrese el coste del destino: ");
        destino.setCoste(scanner.nextDouble());

        try {
            if (destino.agregarDestino()) {
                System.out.println("Destino agregado correctamente");
            } else {
                System.out.println("Error al crear el destino");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarDestino() throws Exception {
        System.out.println(this.mostrarDestinos());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del destino que desea actualizar: ");
        int destinoId = scanner.nextInt();
        scanner.nextLine();
        destino.setId(destinoId);

        if (destino.cargarDestino()) {
            System.out.print("Ingrese el nuevo destino (" + destino.getDestino() + "): ");
            destino.setDestino(scanner.nextLine());
            System.out.print("Ingrese la nueva descripción del destino (" + destino.getDescripcion() + "): ");
            destino.setDescripcion(scanner.nextLine());
            System.out.print("Ingrese el nuevo coste del destino (" + destino.getCoste() + "): ");
            destino.setCoste(scanner.nextDouble());
        }

        try {
            if (destino.actualizarDestino()) {
                System.out.println("Destino actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el destino");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarDestino() throws Exception {
        System.out.println(destino.mostrarDestinosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del destino que desea eliminar: ");
        int destinoId = scanner.nextInt();
        scanner.nextLine();
        destino.setId(destinoId);

        try {
            if (destino.cargarDestino()) {
                if (destino.eliminarDestino()) {
                    System.out.println("El destino se eliminó correctamente");
                }
            } else {
                System.out.println("Error al eliminar el destino");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostrarDestinos() throws Exception {
        Destino destinoLogica = new Destino();
        String datosDestinos = destinoLogica.mostrarDestinosLogica();
        return datosDestinos;
    }
}

