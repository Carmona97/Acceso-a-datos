package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/institutoFP";
            String user = "postgres";
            String pass = "123";
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Conexion establecida");
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el controlador");
        }catch (SQLException e){
            System.out.println("Error al conectar la BD");
        }

    }
}
