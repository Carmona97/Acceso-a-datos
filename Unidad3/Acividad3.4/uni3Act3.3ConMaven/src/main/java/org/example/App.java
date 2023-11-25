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
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/InstitutoFP";
        String user = "postgres";
        String pass = "123";
        Connection conn = DriverManager.getConnection(url, user, pass);

        Statement stmt = conn.createStatement();
        stmt.execute("ALTER TABLE Asignatura ADD COLUMN Horas INTEGER");

    }
}
