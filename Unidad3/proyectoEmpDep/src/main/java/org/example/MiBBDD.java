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
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();
            rs = query.executeQuery("SELECT apellido1 FROM empleado");
            System.out.println("Los apellidos son:");
            while(rs.next()){
                System.out.println(rs.getString("apellido1"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void apellidosEmpSinRepetir(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();
            rs = query.executeQuery("SELECT DISTINCT apellido1 FROM empleado");
            System.out.println("Los apellidos sin repetir son:");
            while(rs.next()){
                System.out.println(rs.getString("apellido1"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deptMenorGasto(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();
            rs = query.executeQuery("SELECT nombre, presupuesto FROM departamento ORDER BY presupuesto LIMIT 2;");
            System.out.println("Los departamentos son:");
            while(rs.next()){
                System.out.println(rs.getString("nombre")+ ", presupuesto: " +rs.getDouble("presupuesto"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deptPresupuestoMayor150k(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();
            rs = query.executeQuery("SELECT nombre, presupuesto FROM departamento WHERE presupuesto >= 150000;");
            System.out.println("Los departamentos son:");
            while(rs.next()){
                System.out.println(rs.getString("nombre")+ ", presupuesto: " +rs.getDouble("presupuesto"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void empleadosYSusDeptos(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();

            rs = query.executeQuery("SELECT empleado.nombre AS empNombre, empleado.apellido1 AS empApellido1, empleado.apellido2 AS empApellido2, departamento.nombre AS deptNombre FROM empleado JOIN departamento ON empleado.id_departamento = departamento.id;");
            System.out.println("Los datos son:");
            while(rs.next()){
                System.out.println(rs.getString("empNombre")+" "+rs.getString("empApellido1")+" "+rs.getString("empApellido2")+ ", departamento: " +rs.getString("deptNombre"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void empleadoDetallesDeptoOrdenado(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();

            rs = query.executeQuery("SELECT empleado.nombre AS empNombre, empleado.apellido1 AS empApellido1, empleado.apellido2 AS empApellido2, departamento.nombre AS deptNombre FROM empleado JOIN departamento ON empleado.id_departamento = departamento.id ORDER BY departamento.nombre, empleado.apellido1, empleado.apellido2;");
            System.out.println("Los datos son:");
            while(rs.next()){
                System.out.println(rs.getString("empNombre")+" "+rs.getString("empApellido1")+" "+rs.getString("empApellido2")+ ", departamento: " +rs.getString("deptNombre"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deptoConEmpleados(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();

            rs = query.executeQuery("SELECT id, nombre FROM departamento WHERE id NOT IN (SELECT id_departamento FROM empleado);");
            System.out.println("Los departamentos son:");
            while(rs.next()){
                System.out.println(rs.getString("nombre")+" "+rs.getInt("id"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void empleadoPorNif(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();

            rs = query.executeQuery("SELECT nombre FROM departamento WHERE id = (SELECT id_departamento FROM empleado WHERE nif = '38382980M');");
            System.out.println("Los departamentos son:");
            while(rs.next()){
                System.out.println(rs.getString("nombre"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void sumaPresupuestos(){
        ResultSet rs = null;
        try {
            Statement query = conn.createStatement();

            rs = query.executeQuery("SELECT SUM(presupuesto) AS PresupuestoTotal FROM departamento;");
            System.out.println("El presupuesto total es:");
            while(rs.next()){
                System.out.println(rs.getDouble("PresupuestoTotal"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
