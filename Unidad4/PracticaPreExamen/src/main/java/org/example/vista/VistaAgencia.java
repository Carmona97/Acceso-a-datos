package org.example.vista;

import org.example.negocio.Agencia;

import java.sql.SQLException;
import java.util.Scanner;

public class VistaAgencia {
    Agencia agencia = new Agencia();


    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Agencias");
        System.out.println("1. Mostrar Agencias");
        System.out.println("2. Agregar Agencia");
        System.out.println("3. Actualizar Agencia");
        System.out.println("4. Eliminar Agencia");
        System.out.println("0. Volver al menu principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudAgencia() throws Exception {
        String respuesta = "";

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(agencia.mostrarAgenciasLogica());
                    break;
                case 2:
                    this.agregarAgencia();
                    break;
                case 3:
                    this.actualizarAgencia();
                    break;
                case 4:
                    this.eliminarAgencia();
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

    public String mostrarAgencias() throws Exception {

        Agencia agenciaLogica = new Agencia();
        String datosAgencias = agenciaLogica.mostrarAgenciasLogica();
        return datosAgencias;

    }

    public void agregarAgencia() {
        Scanner scanner = new Scanner(System.in);
        Agencia agencia = new Agencia();

        System.out.print("Ingrese el nombre del agencia: ");
        agencia.setNombre(scanner.nextLine());
        System.out.print("Ingrese la direccion de la agencia: ");
        agencia.setDireccion(scanner.nextLine());
        System.out.print("Ingrese el teléfono del agencia: ");
        agencia.setTelefono(scanner.nextLine());

        try {
            if (agencia.agregarAgencia()) {
                System.out.println("agencia agregado correctamente");
            } else {
                System.out.println("Error al crear la agencia");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarAgencia() throws Exception {
        System.out.println(this.mostrarAgencias());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del agencia que desea actualizar: ");
        int agenciaId = scanner.nextInt();
        scanner.nextLine();
        agencia.setId(agenciaId);

        if (agencia.cargarAgencia()) {
            System.out.print("Ingrese el nuevo nombre de la agencia (" + agencia.getNombre() + "): ");
            agencia.setNombre(scanner.nextLine());
            System.out.print("Ingrese la nueva direccion de la agencia(" + agencia.getDireccion() + "): ");
            agencia.setDireccion(scanner.nextLine());
            System.out.print("Ingrese el nuevo teléfono de la agencia(" + agencia.getTelefono() + "): ");
            agencia.setTelefono(scanner.nextLine());
        }
        try {
            if (agencia.actualizarAgencia()) {
                System.out.println("agencia actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el agencia");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarAgencia() throws Exception {

        System.out.println(agencia.mostrarAgenciasLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del agencia que desea eliminar: ");
        int agenciaId = scanner.nextInt();
        scanner.nextLine();
        agencia.setId(agenciaId);
            try {
                if (agencia.cargarAgencia()) {
                    if (agencia.eliminarAgencia()){
                        System.out.println("La agencia se elimino correctamente");
                    }

                } else {
                    System.out.println("Error al eliminar el agencia");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
