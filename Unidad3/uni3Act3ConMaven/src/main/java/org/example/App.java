package org.example;

import java.sql.*;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String pass = "123";
        Connection conn = DriverManager.getConnection(url, user, pass);

        PreparedStatement ps = null;

        ps = conn.prepareStatement("INSERT INTO asignatura(nombre,anyo) VALUES (?,?)");
        ps.setString(1, "Lenguaje de marcas");
        ps.setInt(2, 1);
        int registrosAfectados = ps.executeUpdate();
        if (registrosAfectados != 0) {
            System.out.println("Se ha añadido el registro correctamente ");
        } else {
            System.out.println("No se ha podido añadir correctamente el registro");
        }
        Statement statement = conn.createStatement();
        String SQLsentence = "SELECT * FROM asignatura ORDER BY codigo";
        ResultSet rs = statement.executeQuery(SQLsentence);
        System.out.println("Codigo" + "\t" + "Nombre" + "\t" + "Anyo");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
        }
        rs.close();
        conn.close();
    }
}