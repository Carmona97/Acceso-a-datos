package org.example.vista;

import org.example.negocio.Vuelo;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class VistaVuelo {
    Vuelo vuelo = new Vuelo();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Vuelos");
        System.out.println("1. Mostrar Vuelos");
        System.out.println("2. Agregar Vuelo");
        System.out.println("3. Actualizar Vuelo");
        System.out.println("4. Eliminar Vuelo");
        System.out.println("0. Volver al menú principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudVuelo() throws Exception {
        String respuesta = "";

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(vuelo.mostrarVuelosLogica());
                    break;
                case 2:
                    this.agregarVuelo();
                    break;
                case 3:
                    this.actualizarVuelo();
                    break;
                case 4:
                    this.eliminarVuelo();
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

    public void agregarVuelo() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Vuelo vuelo = new Vuelo();

        System.out.print("Ingrese el origen del vuelo: ");
        vuelo.setOrigenVuelo(scanner.nextLine());
        System.out.print("Ingrese el destino del vuelo: ");
        vuelo.setDestinoVuelo(scanner.nextLine());
        System.out.print("Ingrese la fecha de salida del vuelo (en formato dd/mm/yyyy): ");
        String fechaSalidaString = scanner.nextLine();
        vuelo.setFechaSalidaVuelo(dateFormat.parse(fechaSalidaString));
        System.out.print("Ingrese la fecha de llegada del vuelo (en formato dd/mm/yyyy): ");
        String fechaLlegadaString = scanner.nextLine();
        vuelo.setFechaLlegadaVuelo(dateFormat.parse(fechaLlegadaString));
        System.out.print("Ingrese el coste del vuelo: ");
        vuelo.setCosteVuelo(scanner.nextDouble());

        try {
            if (vuelo.agregarVuelo()) {
                System.out.println("Vuelo agregado correctamente");
            } else {
                System.out.println("Error al crear el vuelo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarVuelo() throws Exception {
        System.out.println(this.mostrarVuelos());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del vuelo que desea actualizar: ");
        int vueloId = scanner.nextInt();
        scanner.nextLine();
        vuelo.setIdVuelo(vueloId);

        if (vuelo.cargarVuelo()) {
            System.out.print("Ingrese el nuevo origen del vuelo (" + vuelo.getOrigenVuelo() + "): ");
            vuelo.setOrigenVuelo(scanner.nextLine());
            System.out.print("Ingrese el nuevo destino del vuelo (" + vuelo.getDestinoVuelo() + "): ");
            vuelo.setDestinoVuelo(scanner.nextLine());
            System.out.print("Ingrese el nuevo coste del vuelo (" + vuelo.getCosteVuelo() + "): ");
            vuelo.setCosteVuelo(scanner.nextDouble());
        }

        try {
            if (vuelo.actualizarVuelo()) {
                System.out.println("Vuelo actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el vuelo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarVuelo() throws Exception {
        System.out.println(vuelo.mostrarVuelosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del vuelo que desea eliminar: ");
        int vueloId = scanner.nextInt();
        scanner.nextLine();
        vuelo.setIdVuelo(vueloId);

        try {
            if (vuelo.cargarVuelo()) {
                if (vuelo.eliminarVuelo()) {
                    System.out.println("El vuelo se eliminó correctamente");
                }
            } else {
                System.out.println("Error al eliminar el vuelo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostrarVuelos() throws Exception {
        Vuelo vueloLogica = new Vuelo();
        String datosVuelos = vueloLogica.mostrarVuelosLogica();
        return datosVuelos;
    }
}
