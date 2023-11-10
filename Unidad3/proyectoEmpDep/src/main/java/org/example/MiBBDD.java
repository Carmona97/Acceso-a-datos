package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiBBDD {

     private Connection conn;
     private String url;
     private String usuario;
     private String pass;

    public MiBBDD(String url,String usuario,String pass) throws SQLException{

        this.url = url;
        this.usuario = usuario;
        this.pass = pass;
        this.conn = DriverManager.getConnection(url,usuario,pass);
    }

    public boolean iniciarConexion(){
        boolean conexionEstablecida= true;
        try{
            Class.forName("org.postgresql.Driver");
            Connection conn1 = this.conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            conexionEstablecida =false;
        }
        return conexionEstablecida;
    }
    public boolean cerrarConexion(){
        boolean conexionCerrada=true;
        try {
            conn.close();
        } catch (SQLException e) {
            conexionCerrada = false;
            e.printStackTrace();
        }
        return conexionCerrada;
    }
}
