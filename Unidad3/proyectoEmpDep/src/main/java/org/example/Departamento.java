package org.example;

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
}
