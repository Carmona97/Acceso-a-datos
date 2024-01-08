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
                System.out.println("Indique que desea hacer:" +
                        "\n1.Mostrar empleados" +
                        "\n2.Actualizar empleado");
                numMenu = scn.nextInt();
                scn.nextLine();
                switch (numMenu){

                    case 0 : {
                        System.out.println("Hasta pronto!");
                        break;
                    }

                    case 1 : {
                        mostrarEmpleados(miSession);
                        break;
                    }

                    case 2 : {
                        System.out.println("Indique el numero del empleado que se desea modificar");
                        int empno = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Indique el nuevo nombre");
                        String nuevoNombre = scn.next();
                        System.out.println("Indique el nuevo puesto");
                        String nuevoPuesto =scn.next();
                        System.out.println("Indique el nuevo numero de departamento");
                        int nuevoDepno =scn.nextInt();

                        actualizarEmpleado(miSession,empno,nuevoNombre,nuevoPuesto,nuevoDepno);

                        break;
                    }

                    default:
                        System.out.println("Número erroneo, vuelva a intentarlo");
                        break;
                }
            }while (numMenu!=0);

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

    public static void mostrarEmpleados(Session miSession){
        Query<EmpleadosEntidad> miQuery = miSession.createQuery("from acdat.org.entidad.EmpleadosEntidad");
        List<EmpleadosEntidad> listaEmpleados = miQuery.list();
        for(Object obj: listaEmpleados){
            EmpleadosEntidad empleado = (EmpleadosEntidad) obj;
            System.out.println("Empleado: "+empleado.getNombre()+", número: "+empleado.getEmpno());
        }
    }

    public static void actualizarEmpleado (Session miSession,int empno,String nuevoNombre,String nuevoPuesto,int nuevoDepno){
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
            System.out.println("Empleado con empno " + empno + " no encontrado.");
        }
    }
}


