package callableStatement;

import java.sql.*;

public class Main {

    static final String url = "jdbc:postgresql://localhost:5432/institutoFP";
    static final String user = "postgres";
    static final String pwd = "123";
    public static void main(String[] args) throws ClassNotFoundException, SQLException{

        try(Connection conn = DriverManager.getConnection(url,user,pwd)){
            CallableStatement statement = conn.prepareCall("{call asignaturasanuales(1)}");
            ResultSet resultados = statement.executeQuery();
            System.out.println("Codigo"+"\t"+"Nombre");
            System.out.println("-------------------------------------------------");
            while(resultados.next()){
                System.out.println(resultados.getString(1)+"\t");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
