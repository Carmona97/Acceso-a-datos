package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        MiBBDD empresa = null;
        try {
            empresa = new MiBBDD("jdbc:postgresql://localhost:5432/empresa","postgres","123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        boolean conexionIniciada = false;
        boolean conexionCerrada = false;
        conexionIniciada = empresa.iniciarConexion();
        if (conexionIniciada){
            System.out.println("Se ha iniciado la conexion correctamente");
        }else{
            System.out.println("Error en la conexion");
        }
        /*conexionCerrada = empresa.cerrarConexion();
        if (conexionCerrada){
            System.out.println("Se ha cerrado la conexion");
        }else{
            System.out.println("Error en la conexion");
        }*/


    }
}
