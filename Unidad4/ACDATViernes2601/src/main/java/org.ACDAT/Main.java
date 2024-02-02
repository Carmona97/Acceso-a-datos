package org.ACDAT;

import org.ACDAT.Entidades.EntidadPersona;
import org.ACDAT.Entidades.EntidadTelefono;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.logging.Level;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
      @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        Session miSession = abrirSession();

        Transaction transaccion = null;

        transaccion = miSession.beginTransaction();

        try {
            EntidadPersona paco = new EntidadPersona("Paco");
            miSession.persist(paco);
            EntidadTelefono telefono = new EntidadTelefono("678182948");
            telefono.setPersona(paco);
            miSession.persist(telefono);
            miSession.flush();
            transaccion.commit();
            miSession.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
