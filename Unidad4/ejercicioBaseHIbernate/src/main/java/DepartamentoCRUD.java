import acdat.org.entidad.DepartamentosEntidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DepartamentoCRUD {

    public static void mostrarDepartamentos(Session miSession) {
        Query<DepartamentosEntidad> miQuery = miSession.createQuery("from acdat.org.entidad.DepartamentosEntidad");
        List<DepartamentosEntidad> listaDepartamentos = miQuery.list();
        for (DepartamentosEntidad departamento : listaDepartamentos) {
            System.out.println("Departamento: " + departamento.getNombre() + ", Numero: " + departamento.getDepno());
        }
    }

    public static boolean actualizarDepartamento(Session miSession, int depno, String nuevoNombre, String nuevaUbicacion) {
        Query<DepartamentosEntidad> miQuery = miSession.createQuery("from acdat.org.entidad.DepartamentosEntidad where depno = :depno");
        miQuery.setParameter("depno", depno);

        List<DepartamentosEntidad> listaDepartamentos = miQuery.list();

        if (!listaDepartamentos.isEmpty()) {
            DepartamentosEntidad departamento = listaDepartamentos.get(0);
            departamento.setNombre(nuevoNombre);
            departamento.setUbicacion(nuevaUbicacion);

            Transaction transaction = miSession.beginTransaction();
            miSession.update(departamento);
            transaction.commit();
            return true;
        } else {
            return false;
        }
    }

    public static boolean insertarDepartamento(Session miSession, int depno, String nombre, String ubicacion) {
        DepartamentosEntidad depnoRepetido = miSession.get(DepartamentosEntidad.class, depno);

        if (depnoRepetido != null) {
            return false;
        } else {
            Transaction transaction = miSession.beginTransaction();

            DepartamentosEntidad departamento = new DepartamentosEntidad();
            departamento.setDepno(depno);
            departamento.setNombre(nombre);
            departamento.setUbicacion(ubicacion);

            miSession.save(departamento);
            transaction.commit();

            return true;
        }
    }

    public static boolean borrarDepartamento(Session miSession, int depno) {
        Query<DepartamentosEntidad> miQuery = miSession.createQuery("from acdat.org.entidad.DepartamentosEntidad where depno = :depno");
        miQuery.setParameter("depno", depno);

        List<DepartamentosEntidad> listaDepartamentos = miQuery.list();

        if (!listaDepartamentos.isEmpty()) {
            DepartamentosEntidad departamento = listaDepartamentos.get(0);

            Transaction transaction = miSession.beginTransaction();
            miSession.delete(departamento);
            transaction.commit();

            return true;
        } else {
            return false;
        }
    }

}
