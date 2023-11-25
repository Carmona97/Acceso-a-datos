package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class App 
{
    public static void main( String[] args )
    {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String pass = "123";

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS cursos(Codigo SERIAL PRIMARY KEY, nombre VARCHAR(90)); ");
            ps.execute();
            PreparedStatement psInsert = conn.prepareStatement("INSERT INTO cursos(nombre)  VALUES (?)");
            psInsert.setString(1,"Desarrollo aplicaciones multiplataforma");
            psInsert.executeUpdate();
            psInsert.setString(1,"Desarrollo aplicaciones web");
            psInsert.executeUpdate();
            psInsert.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
