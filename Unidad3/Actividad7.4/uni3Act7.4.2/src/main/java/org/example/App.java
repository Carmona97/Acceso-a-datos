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
        String url = "jdbc:postgresql://localhost:5432/empleados";
        String user = "postgres";
        String pass = "123";
        try(Connection conn = DriverManager.getConnection(url, user, pass)){

            CallableStatement cStatement = conn.prepareCall("{call lista_empleados_departamento('20')}");
            ResultSet rs = cStatement.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            }

        }catch (SQLException e){
            e.printStackTrace();

        }
    }
}
