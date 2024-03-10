package org.example.vista;

import org.example.Negocio.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class VistaUsuario {
    private Usuario usuario = new Usuario();

    public void mostrarMenu() {
        System.out.println("Menú de CRUD de Usuarios");
        System.out.println("1. Mostrar Usuarios");
        System.out.println("2. Agregar Usuario");
        System.out.println("3. Actualizar Usuario");
        System.out.println("4. Eliminar Usuario");
        System.out.println("0. Volver al menú principal");
        System.out.print("Ingrese la opción deseada: ");
    }

    public void crudUsuarios() throws Exception {
        String respuesta = "";

        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(usuario.mostrarUsuariosLogica());
                    break;
                case 2:
                    this.agregarUsuario();
                    break;
                case 3:
                    this.actualizarUsuario();
                    break;
                case 4:
                    this.eliminarUsuario();
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

    public void agregarUsuario() {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario();

        System.out.print("Ingrese el ID del usuario: ");
        usuario.setIdUsuario(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Ingrese el nombre del usuario: ");
        usuario.setNombreUsuario(scanner.nextLine());
        System.out.print("Ingrese el email del usuario: ");
        usuario.setEmail(scanner.nextLine());

        if (usuario.agregarUsuario()) {
            System.out.println("Usuario agregado correctamente");
        } else {
            System.out.println("Error al crear el usuario");
        }
    }

    public void actualizarUsuario() throws Exception {
        System.out.println(usuario.mostrarUsuariosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del usuario que desea actualizar: ");
        int usuarioId = scanner.nextInt();
        scanner.nextLine();
        usuario.setIdUsuario(usuarioId);

        if (usuario.cargarUsuario()) {
            System.out.print("Ingrese el nuevo nombre del usuario (" + usuario.getNombreUsuario() + "): ");
            String nuevoNombreUsuario = scanner.nextLine();
            System.out.print("Ingrese el nuevo email del usuario (" + usuario.getEmail() + "): ");
            String nuevoEmail = scanner.nextLine();

            usuario.setNombreUsuario(nuevoNombreUsuario);
            usuario.setEmail(nuevoEmail);
        }

        try {
            if (usuario.actualizarUsuario()) {
                System.out.println("Usuario actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el usuario");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarUsuario() throws Exception {
        System.out.println(usuario.mostrarUsuariosLogica());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del usuario que desea eliminar: ");
        int usuarioId = scanner.nextInt();
        scanner.nextLine();
        usuario.setIdUsuario(usuarioId);

        try {
            if(usuario.cargarUsuario()){
                if (usuario.eliminarUsuario()) {
                    System.out.println("Usuario eliminado correctamente");
                } else {
                    System.out.println("Error al eliminar el usuario");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
