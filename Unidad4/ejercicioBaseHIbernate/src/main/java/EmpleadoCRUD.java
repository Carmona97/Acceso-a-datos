import acdat.org.entidad.EmpleadosEntidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class EmpleadoCRUD {


    public static void mostrarEmpleados(Session miSession){
        Query<EmpleadosEntidad> miQuery = miSession.createQuery("from acdat.org.entidad.EmpleadosEntidad");
        List<EmpleadosEntidad> listaEmpleados = miQuery.list();
        for(Object obj: listaEmpleados){
            EmpleadosEntidad empleado = (EmpleadosEntidad) obj;
            System.out.println("Empleado: "+empleado.getNombre()+", número: "+empleado.getEmpno());
        }
    }

    public static boolean actualizarEmpleado (Session miSession,int empno,String nuevoNombre,String nuevoPuesto,int nuevoDepno){
        Query<EmpleadosEntidad> miQuery = miSession.createQuery( "from acdat.org.entidad.EmpleadosEntidad where empno = :empno");
        miQuery.setParameter("empno", empno);

        List<EmpleadosEntidad> listaEmpleados = miQuery.list();

        if (!listaEmpleados.isEmpty()) {
            EmpleadosEntidad empleado = listaEmpleados.get(0);
            empleado.setNombre(nuevoNombre);
            empleado.setPuesto(nuevoPuesto);
            empleado.setDepno(nuevoDepno);

            Transaction transaction = miSession.beginTransaction();
            miSession.update(empleado);
            transaction.commit();
        } else {
            return false;
        }
        return true;
    }

    public static boolean insertarEmpleado(Session miSession,int empno,String nombre,String puesto,int depno){

        EmpleadosEntidad empnoRepetido = miSession.get(EmpleadosEntidad.class, empno);
        if (empnoRepetido != null){

            return false;

        }else{


            Query<EmpleadosEntidad> miQuery = miSession.createQuery( "from acdat.org.entidad.EmpleadosEntidad");
            Transaction transaction = miSession.beginTransaction();

            EmpleadosEntidad empleado = new EmpleadosEntidad();
            empleado.setEmpno(empno);
            empleado.setNombre(nombre);
            empleado.setPuesto(puesto);
            empleado.setDepno(depno);

            miSession.save(empleado);
            transaction.commit();

            return true;
        }

    }

    public static boolean borrarEmpleado(Session miSession,int empno){
        Scanner scn = new Scanner(System.in);
        String eliminar = "";
        boolean eliminadoConExito = true;

        EmpleadosEntidad empnoRepetido = miSession.get(EmpleadosEntidad.class, empno);
        if (empnoRepetido != null){

            eliminadoConExito = false;

        }else{
            Query<EmpleadosEntidad> miQuery = miSession.createQuery( "from acdat.org.entidad.EmpleadosEntidad where empno = :empno");
            miQuery.setParameter("empno", empno);


            List<EmpleadosEntidad> listaEmpleados = miQuery.list();
            EmpleadosEntidad empleado = listaEmpleados.get(0);
            if (listaEmpleados.size() > 0){
                System.out.println("Los datos que se eliminaran son: "+empleado.toString()+"" +
                        "\nEsta seguro (S)i/(N)o");

                eliminar =scn.next();

                if("s".equalsIgnoreCase(eliminar)){
                    Transaction transaction = miSession.beginTransaction();
                    miSession.delete(empleado);
                    transaction.commit();

                } else {
                    eliminadoConExito = false;
                }
            }
        }
        return eliminadoConExito;
    }
}
