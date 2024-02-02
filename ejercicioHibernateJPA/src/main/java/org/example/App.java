package org.example;

import org.example.entidades.EntidadEmpleados;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class App 
{
    static Session abrirSession() throws Exception{
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if(session == null){
            throw new Exception("Error abriendo la sesion");
        }
        return session;
    }
    public static void cargarEmpleados(){
        try(Session session = abrirSession()){
            EntidadEmpleados empleadosJPA = new EntidadEmpleados();

            Query<EntidadEmpleados> empleadosDaoQuery = session.createQuery("from org.example.entidades.EntidadEmpleados");
            List<EntidadEmpleados> empleadosDaoList = empleadosDaoQuery.list();

            System.out.println("EMPLEADOS");

            for (Object obj:empleadosDaoList) {
                EntidadEmpleados empleado = (EntidadEmpleados) obj;
                System.out.printf("Numero: %d\tNombre: %s\n", empleado.getEmpno(), empleado.getNombre());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main( String[] args )
    {
        try{

            cargarEmpleados();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
