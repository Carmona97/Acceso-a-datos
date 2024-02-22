import entidades.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Principal {
    public static void main(String[] args) {
        Empleado nuevoEmpleado = new Empleado();

        nuevoEmpleado.setNombre("Pepe");
        nuevoEmpleado.setApellido("Gonzalez");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(nuevoEmpleado);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
