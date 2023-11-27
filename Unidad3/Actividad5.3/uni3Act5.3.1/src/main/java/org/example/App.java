package org.example;

import java.sql.*;

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
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            Statement statement = conn.createStatement();
            String SQLsentence = "SELECT * FROM asignatura ORDER BY codigo";
            ResultSet rs = statement.executeQuery(SQLsentence);
            System.out.println("Codigo" + "\t" + "Nombre" + "\t" + "Anyo");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            }
            rs.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
