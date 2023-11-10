package org.example;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

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

    public void borrarTablaDepartamento(){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DROP TABLE IF EXISTS departamento");
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void crearTablaDepartamento(){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS departamento(id SERIAL PRIMARY KEY,\n" +
                    "  nombre VARCHAR(100) NOT NULL,\n" +
                    "  presupuesto DOUBLE PRECISION NOT NULL,\n" +
                    "  gastos DOUBLE PRECISION NOT NULL);");
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void borrarTablaEmpleado() {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DROP TABLE IF EXISTS empleado");
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void crearTablaEmpleado() {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS empleado(id SERIAL PRIMARY KEY," +
                    " nif VARCHAR(9) NOT NULL UNIQUE," +
                    " nombre VARCHAR(100) NOT NULL," +
                    " apellido1 VARCHAR(100) NOT NULL," +
                    " apellido2 VARCHAR(100), id_departamento INT" +
                    ", FOREIGN KEY (id_departamento) REFERENCES departamento(id) ON DELETE CASCADE);");
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public String anadirDpto(Departamento depto) {
        PreparedStatement ps = null;
        String anadidoCorrectamente;
        try {
            ps = conn.prepareStatement("INSERT INTO departamento(nombre,presupuesto,gastos) VALUES (?,?,?)");
            ps.setString(1, depto.getNombre());
            ps.setDouble(2, depto.getPresupuesto());
            ps.setDouble(3, depto.getGastos());
            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados !=0) {
                anadidoCorrectamente = "Se ha registrado el departamento: "+ depto.getNombre();
            } else {
                anadidoCorrectamente = "Ha habido algun error al registrar el departamento "+ depto.getNombre();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return anadidoCorrectamente;
    }

    public String anadirEmpleado(Empleado emp) {
        PreparedStatement ps = null;
        String anadidoCorrectamente;
        try {
            ps = conn.prepareStatement("INSERT INTO empleado(nif,nombre,apellido1,apellido2,id_departamento) VALUES (?,?,?,?,?)");
            ps.setString(1, emp.getDni());
            ps.setString(2, emp.getNombre());
            ps.setString(3, emp.getApellido1());
            ps.setString(4, emp.getApellido2());
            ps.setInt(5, emp.getIdDepartamento());

            int registrosAfectados = ps.executeUpdate();
            if (registrosAfectados !=0) {
                anadidoCorrectamente = "Se ha registrado el empleado "+emp.getNombre()+" "+emp.getApellido1()+" "+emp.getApellido2();
            } else {
                anadidoCorrectamente = "Ha habido algun error al registrar el empleado "+emp.getNombre()+" "+emp.getApellido1()+" "+emp.getApellido2();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return anadidoCorrectamente;
    }

    public void apellidosEmp(){

    }



}
