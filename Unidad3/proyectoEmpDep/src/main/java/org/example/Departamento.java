package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Departamento {

    private int id;
    private String nombre;
    private double presupuesto;
    private double gastos;
    private ArrayList<Empleado> empleados;

    public Departamento(int id, String nombre, double presupuesto, double gastos, ArrayList<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.gastos = gastos;
        this.empleados = empleados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
    public boolean insertarEnDepartamento(Connection conn, String nombre, double presupuesto, double gastos) {
        String query = "INSERT INTO departamento (nombre,presupuesto,gastos) VALUES (?,?,?)";

        boolean insercionCorrecta =false;
        if (conn != null) {

            try (PreparedStatement consulta = conn.prepareStatement(query)) {

                consulta.setString(1, nombre);
                consulta.setDouble(2, presupuesto);
                consulta.setDouble(3, gastos);

                int registrosAfectados = consulta.executeUpdate();
                if (registrosAfectados != 0) {

                    insercionCorrecta = true;

                } else {

                    insercionCorrecta = false;

                }

            } catch (SQLException ex) {
                System.err.printf("Se ha producido un error al ejecutar la consulta SQL.");
            }
        }
        return insercionCorrecta;
    }
}
