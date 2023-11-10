package org.example;

import java.sql.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        boolean conexionIniciada;
        boolean conexionCerrada;
        MiBBDD empresa;
        ArrayList<Empleado> empleadosdpto1 = new ArrayList<>();
        ArrayList<Empleado> empleadosdpto2 = new ArrayList<>();
        ArrayList<Empleado> empleadosdpto3 = new ArrayList<>();
        ArrayList<Empleado> empleadosdpto4 = new ArrayList<>();
        ArrayList<Empleado> empleadosdpto5 = new ArrayList<>();
        ArrayList<Empleado> empleadosdpto6 = new ArrayList<>();
        ArrayList<Empleado> empleadosdpto7 = new ArrayList<>();
        Departamento dpto1 = new Departamento(1, "Desarrollo", 120000, 6000,empleadosdpto1);
        Departamento dpto2 = new Departamento(2, "Sistemas", 150000, 21000,empleadosdpto2);
        Departamento dpto3 = new Departamento(3, "Recursos Humanos", 280000, 25000, empleadosdpto3);
        Departamento dpto4 = new Departamento(4, "Contabilidad", 110000, 3000, empleadosdpto4);
        Departamento dpto5 = new Departamento(5, "I+D", 375000, 380000, empleadosdpto5);
        Departamento dpto6 = new Departamento(6, "Proyectos", 0, 0,empleadosdpto6);
        Departamento dpto7 = new Departamento(7, "Publicidad", 0, 1000,empleadosdpto7);
        Empleado emp1 = new Empleado(1, "32481596F", "Aarón", "Rivero", "Gómez", 1);
        Empleado emp2 = new Empleado(2, "Y5575632D", "Adela", "Salas", "Díaz", 2);
        Empleado emp3 = new Empleado(3, "R6970642B", "Adolfo", "Rubio", "Flores", 3);
        Empleado emp4 = new Empleado(4, "77705545E", "Adrián", "Suárez", null, 4);
        Empleado emp5 = new Empleado(5, "17087203C", "Marcos", "Loyola", "Méndez", 5);
        Empleado emp6 = new Empleado(6, "38382980M", "María", "Santana", "Moreno", 1);
        Empleado emp7 = new Empleado(7, "80576669X", "Pilar", "Ruiz", null, 2);
        Empleado emp8 = new Empleado(8, "71651431Z", "Pepe", "Ruiz", "Santana", 3);
        Empleado emp9 = new Empleado(9, "56399183D", "Juan", "Gómez", "López", 2);
        Empleado emp10 = new Empleado(10, "46384486H", "Diego", "Flores", "Salas", 5);


        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            empresa = new MiBBDD("jdbc:postgresql://localhost:5432/empresa","postgres","123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexionIniciada = empresa.iniciarConexion();
        if (conexionIniciada){
            System.out.println("Se ha iniciado la conexion correctamente");
        }else{
            System.out.println("Error en la conexion");
        }

        empresa.crearTablaDepartamento();
        empresa.crearTablaEmpleado();

        conexionCerrada = empresa.cerrarConexion();
        if (conexionCerrada){
            System.out.println("Se ha cerrado la conexion");
        }else{
            System.out.println("Error en la conexion");
        }


    }
}
