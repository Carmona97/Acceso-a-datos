package usoStatements;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/institutoFP";
        String user = "postgres";
        String pass = "123";
        Connection conn = DriverManager.getConnection(url,user,pass);
        Statement statement = conn.createStatement();
        String SQLsentence = "SELECT * FROM asignaturas ORDER BY codigo";
        ResultSet rs = statement.executeQuery(SQLsentence);
        System.out.println("Codigo"+"\t"+"Nombre");
        while(rs.next()){
            System.out.println(rs.getString(1)+"\t"+rs.getString(2));
        }
        rs.close();
        conn.close();
    }
}
