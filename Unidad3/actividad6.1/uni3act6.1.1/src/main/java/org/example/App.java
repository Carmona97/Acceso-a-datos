package org.example;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String pass = "123";
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            Scanner scn = new Scanner(System.in);
            String asignatura;
            int anoAsignatura;
            int cantidadAsignaturas;

            System.out.println("Indique cuantas asignaturas desea añadir:");
            cantidadAsignaturas = scn.nextInt();
            scn.nextLine();

            for (int i = 0; i < cantidadAsignaturas; i++) {

                System.out.println("Introduce el nombre de la asignatura:");
                asignatura = scn.nextLine();
                System.out.println("Introduce el año de la asignatura:");
                anoAsignatura = scn.nextInt();
                scn.nextLine();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO asignatura(nombre,anyo) VALUES (?,?)");
                ps.setString(1, asignatura);
                ps.setInt(2, anoAsignatura);
                int registrosAfectados = ps.executeUpdate();
                if (registrosAfectados != 0) {
                    System.out.println("Se ha añadido el registro correctamente ");
                } else {
                    System.out.println("No se ha modificado correctamente ningun registro");
                }

            }
            Statement statement = conn.createStatement();
            String SQLsentence = "SELECT * FROM asignatura ORDER BY codigo";
            ResultSet rs = statement.executeQuery(SQLsentence);
            System.out.println("Codigo" + "\t" + "Nombre" + "\t" + "Anyo");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
