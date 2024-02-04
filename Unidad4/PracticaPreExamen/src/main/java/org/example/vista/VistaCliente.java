
package org.example.vista;

import org.example.negocio.Cliente;

import java.sql.SQLException;
import java.util.Scanner;

public class VistaCliente {
 Cliente cliente = new Cliente();

    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Clientes");
        System.out.println("1. Mostrar Clientes");
        System.out.println("2. Agregar Cliente");
        System.out.println("3. Actualizar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("0. Volver al menu principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudCliente() throws Exception {
        String respuesta = "";

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(cliente.mostrarClientesLogica());
                    break;
                case 2:
                    this.agregarCliente();
                    break;
                case 3:
                    this.actualizarCliente();
                    break;
                case 4:
                    this.eliminarCliente();
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

    public void agregarCliente() {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();

        System.out.print("Ingrese el nombre del cliente: ");
        cliente.setNombre(scanner.nextLine());
        System.out.print("Ingrese el correo del cliente: ");
        cliente.setCorreo(scanner.nextLine());
        System.out.print("Ingrese el teléfono del cliente: ");
        cliente.setTelefono(scanner.nextLine());

        try {
            if (cliente.agregarCliente()) {
                System.out.println("Cliente agregado correctamente");
            } else {
                System.out.println("Error al crear el cliente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarCliente() throws Exception {
        System.out.println(this.mostrarClientes());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente que desea actualizar: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine();
        cliente.setId(clienteId);

        if (cliente.cargarCliente()) {
            System.out.print("Ingrese el nuevo nombre del cliente (" + cliente.getNombre() + "): ");
            cliente.setNombre(scanner.nextLine());
            System.out.print("Ingrese el nuevo correo del cliente (" + cliente.getCorreo() + "): ");
            cliente.setCorreo(scanner.nextLine());
            System.out.print("Ingrese el nuevo teléfono del cliente (" + cliente.getTelefono() + "): ");
            cliente.setTelefono(scanner.nextLine());
        }

        try {
            if (cliente.actualizarCliente()) {
                System.out.println("Cliente actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el cliente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarCliente() throws Exception {
        System.out.println(cliente.mostrarClientesLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente que desea eliminar: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine();
        cliente.setId(clienteId);

        try {
            if (cliente.cargarCliente()) {
                if (cliente.eliminarCliente()) {
                    System.out.println("El cliente se eliminó correctamente");
                }
            } else {
                System.out.println("Error al eliminar el cliente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostrarClientes() throws Exception {
        Cliente clienteLogica = new Cliente();
        String datosClientes = clienteLogica.mostrarClientesLogica();
        return datosClientes;
    }
}

