package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
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

        try(Connection conn = DriverManager.getConnection(url, user, pass)) {

        Statement stmt = conn.createStatement();
        stmt.execute("ALTER TABLE Asignatura ADD COLUMN Horas INTEGER");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
