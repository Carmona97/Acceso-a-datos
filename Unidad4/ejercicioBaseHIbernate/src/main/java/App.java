import acdat.org.entidad.EmpleadosEntidad;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class App
{
    public static void main( String[] args )
    {
        Scanner scn = new Scanner(System.in);
        try{

            @SuppressWarnings("unused")
            org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

            Session miSession = abrirSession();

            int numMenu = 0;

            do {
                System.out.println("Indique qué desea hacer:" +
                        "\n1. Mostrar empleados" +
                        "\n2. Actualizar empleado" +
                        "\n3. Insertar empleado" +
                        "\n4. Borrar empleado" +
                        "\n5. Mostrar departamentos" +
                        "\n6. Actualizar departamento" +
                        "\n7. Insertar departamento" +
                        "\n8. Borrar departamento" +
                        "\n0. Salir");

                numMenu = scn.nextInt();
                scn.nextLine();
                switch (numMenu) {

                    case 0: {
                        System.out.println("¡Hasta pronto!");
                        break;
                    }

                    case 1: {
                        EmpleadoCRUD.mostrarEmpleados(miSession);
                        break;
                    }

                    case 2: {
                        System.out.println("Indique el número del empleado que se desea modificar");
                        int empno = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Indique el nuevo nombre");
                        String nuevoNombre = scn.next();
                        System.out.println("Indique el nuevo puesto");
                        String nuevoPuesto = scn.next();
                        System.out.println("Indique el nuevo número de departamento");
                        int nuevoDepno = scn.nextInt();

                        EmpleadoCRUD.actualizarEmpleado(miSession, empno, nuevoNombre, nuevoPuesto, nuevoDepno);

                        break;
                    }

                    case 3: {
                        System.out.println("Indique el número del empleado que desea añadir");
                        int empno = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Indique el nombre");
                        String nombre = scn.next();
                        System.out.println("Indique el puesto");
                        String puesto = scn.next();
                        System.out.println("Indique el número de departamento");
                        int depno = scn.nextInt();

                        boolean insertExito = EmpleadoCRUD.insertarEmpleado(miSession, empno, nombre, puesto, depno);

                        if (insertExito) {
                            System.out.println("Empleado añadido correctamente");
                        } else {
                            System.out.println("El número de empleado ya existe, por favor seleccione uno que no aparezca en la lista:");
                            EmpleadoCRUD.mostrarEmpleados(miSession);
                        }
                        break;
                    }

                    case 4: {
                        System.out.println("Introduzca un número de empleado a eliminar:");
                        int empno = scn.nextInt();
                        scn.nextLine();
                        boolean empleadoBorrado = EmpleadoCRUD.borrarEmpleado(miSession, empno);
                        if (empleadoBorrado) {
                            System.out.println("Empleado eliminado con éxito");
                        } else {
                            System.out.println("No se ha eliminado el empleado");
                        }
                        break;
                    }

                    case 5: {
                        DepartamentoCRUD.mostrarDepartamentos(miSession);
                        break;
                    }

                    case 6: {
                        System.out.println("Indique el número del departamento que se desea modificar");
                        int depno = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Indique el nuevo nombre del departamento");
                        String nuevoNombre = scn.next();
                        System.out.println("Indique la nueva ubicación del departamento");
                        String nuevaUbicacion = scn.next();

                        DepartamentoCRUD.actualizarDepartamento(miSession, depno, nuevoNombre, nuevaUbicacion);

                        break;
                    }

                    case 7: {
                        System.out.println("Indique el número del departamento que desea añadir");
                        int depno = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Indique el nombre del departamento");
                        String nombre = scn.next();
                        System.out.println("Indique la ubicación del departamento");
                        String ubicacion = scn.next();

                        boolean insertExito = DepartamentoCRUD.insertarDepartamento(miSession, depno, nombre, ubicacion);

                        if (insertExito) {
                            System.out.println("Departamento añadido correctamente");
                        } else {
                            System.out.println("El número de departamento ya existe, por favor seleccione uno que no aparezca en la lista:");
                            DepartamentoCRUD.mostrarDepartamentos(miSession);
                        }
                        break;
                    }

                    case 8: {
                        System.out.println("Introduzca un número de departamento a eliminar:");
                        int depno = scn.nextInt();
                        scn.nextLine();
                        boolean departamentoBorrado = DepartamentoCRUD.borrarDepartamento(miSession, depno);
                        if (departamentoBorrado) {
                            System.out.println("Departamento eliminado con éxito");
                        } else {
                            System.out.println("No se ha eliminado el departamento");
                        }
                        break;
                    }

                    default:
                        System.out.println("Número erróneo, vuelva a intentarlo");
                        break;
                }
            } while (numMenu != 0);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static Session abrirSession() throws Exception{
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session == null){
            throw new Exception("Error abriendo la sesion");
        }
        return session;
    }

}


