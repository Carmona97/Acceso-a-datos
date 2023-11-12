package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        boolean conexionIniciada;
        boolean conexionCerrada;
        MiBBDD empresa;
        int eleccionMenu = 0;

        ArrayList<Empleado> listaCompletaEmpleados = new ArrayList<>();
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
        listaCompletaEmpleados.add(emp1);
        listaCompletaEmpleados.add(emp2);
        listaCompletaEmpleados.add(emp3);
        listaCompletaEmpleados.add(emp4);
        listaCompletaEmpleados.add(emp5);
        listaCompletaEmpleados.add(emp6);
        listaCompletaEmpleados.add(emp7);
        listaCompletaEmpleados.add(emp8);
        listaCompletaEmpleados.add(emp9);
        listaCompletaEmpleados.add(emp10);
        for(Empleado emp : listaCompletaEmpleados){
            switch (emp.getIdDepartamento()){
                case 1 -> empleadosdpto1.add(emp);
                case 2 -> empleadosdpto2.add(emp);
                case 3 -> empleadosdpto3.add(emp);
                case 4 -> empleadosdpto4.add(emp);
                case 5 -> empleadosdpto5.add(emp);
                case 6 -> empleadosdpto6.add(emp);
                case 7 -> empleadosdpto7.add(emp);
            }
        }

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

        empresa.borrarTablaEmpleado();
        empresa.borrarTablaDepartamento();

        empresa.crearTablaDepartamento();
        empresa.crearTablaEmpleado();

        System.out.println(empresa.anadirDpto(dpto1));
        System.out.println(empresa.anadirDpto(dpto2));
        System.out.println(empresa.anadirDpto(dpto3));
        System.out.println(empresa.anadirDpto(dpto4));
        System.out.println(empresa.anadirDpto(dpto5));
        System.out.println(empresa.anadirDpto(dpto6));
        System.out.println(empresa.anadirDpto(dpto7));
        System.out.println(empresa.anadirEmpleado(emp1));
        System.out.println(empresa.anadirEmpleado(emp2));
        System.out.println(empresa.anadirEmpleado(emp3));
        System.out.println(empresa.anadirEmpleado(emp4));
        System.out.println(empresa.anadirEmpleado(emp5));
        System.out.println(empresa.anadirEmpleado(emp6));
        System.out.println(empresa.anadirEmpleado(emp7));
        System.out.println(empresa.anadirEmpleado(emp8));
        System.out.println(empresa.anadirEmpleado(emp9));
        System.out.println(empresa.anadirEmpleado(emp10));

        do {
            System.out.println("Indique que desea hacer: \n" +
                    "1. Lista el primer apellido de todos los empleados\n" +
                    "2. Lista el primer apellido de los empleados eliminando los apellidos que estén repetidos\n" +
                    "3. Devuelve una lista con el nombre y el gasto de los 2 departamentos que tienen menor gasto\n" +
                    "4. Devuelve una lista con el nombre de los departamentos y el presupuesto de aquellos que tienen un presupuesto mayor o igual a 150,000 euros\n" +
                    "5. Devuelve un listado con los empleados y los datos de los departamentos donde trabaja cada uno\n" +
                    "6. Devuelve un listado con los empleados y los datos de los departamentos donde trabaja cada uno. Ordena el resultado, en primer lugar por el nombre del departamento (en orden alfabético) y en segundo lugar por los apellidos y el nombre de los empleados\n" +
                    "7. Devuelve un listado con el identificador y el nombre del departamento, solamente de aquellos departamentos que tienen empleados\n" +
                    "8. Devuelve el nombre del departamento donde trabaja el empleado que tiene el NIF 38382980M\n" +
                    "9. Calcula la suma del presupuesto de todos los departamentos\n" +
                    "0. Cerrar la conexion a la base de datos");
            eleccionMenu = scn.nextInt();
            switch (eleccionMenu) {
                case 1 -> empresa.apellidosEmp();
                case 2 -> empresa.apellidosEmpSinRepetir();
                case 3 -> empresa.deptMenorGasto();
                case 4 -> empresa.deptPresupuestoMayor150k();
                case 5 -> empresa.empleadosYSusDeptos();
                case 6 -> empresa.empleadoDetallesDeptoOrdenado();
                case 7 ->empresa.deptoConEmpleados();
                case 8 -> empresa.empleadoPorNif();
                case 9 -> empresa.sumaPresupuestos();
                    default -> System.out.println("El numero introducido es incorrecto, vuelva a introducirlo");

            }
        } while (eleccionMenu != 0);

        conexionCerrada = empresa.cerrarConexion();
        if (conexionCerrada){
            System.out.println("Se ha cerrado la conexion");
        }else{
            System.out.println("Error en la conexion");
        }
    }
}
