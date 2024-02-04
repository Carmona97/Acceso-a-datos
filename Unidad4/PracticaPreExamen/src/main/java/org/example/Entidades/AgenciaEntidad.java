package org.example.Entidades;

import jakarta.persistence.*;
import org.example.negocio.Agencia;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Agencia",schema = "public", catalog = "TuViajePreExamen")
public class AgenciaEntidad {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "id", nullable = false)
        protected int idAgencia;

        @Basic
        @Column(name = "nombre", nullable = false)
        protected String nombreAgencia;
        @Basic
        @Column(name = "direccion", nullable = false)
        protected String direccionAgencia;
        @Basic
        @Column(name = "telefono", nullable = false)
        protected String telefonoAgencia;

        public int getIdAgencia() {
                return idAgencia;
        }

        public void setIdAgencia(int idAgencia) {
                this.idAgencia = idAgencia;
        }

        public String getNombreAgencia() {
                return nombreAgencia;
        }

        public void setNombreAgencia(String nombreAgencia) {
                this.nombreAgencia = nombreAgencia;
        }

        public String getDireccionAgencia() {
                return direccionAgencia;
        }

        public void setDireccionAgencia(String direccionAgencia) {
                this.direccionAgencia = direccionAgencia;
        }

        public String getTelefonoAgencia() {
                return telefonoAgencia;
        }

        public void setTelefonoAgencia(String telefonoAgencia) {
                this.telefonoAgencia = telefonoAgencia;
        }

        public ArrayList<Agencia> mostrarAgenciasPersistencia() throws Exception {

                ArrayList<AgenciaEntidad> listaAgenciaPersistencia = new ArrayList<>();
                ArrayList<Agencia> listaAgenciaLogica = new ArrayList<>();

                Session miSesion = JPAPersistencia.abrirSession();
                JPAPersistencia.eliminarWarnings();
                listaAgenciaPersistencia = (ArrayList<AgenciaEntidad>) miSesion.createNativeQuery("SELECT * FROM Agencia", AgenciaEntidad.class).list();
                for(AgenciaEntidad agenciaPersistencia : listaAgenciaPersistencia){
                        Agencia agregarElementoLogica = new Agencia();
                        agregarElementoLogica.setId(agenciaPersistencia.getIdAgencia());
                        agregarElementoLogica.setDireccion(agenciaPersistencia.getDireccionAgencia());
                        agregarElementoLogica.setNombre(agenciaPersistencia.getNombreAgencia());
                        agregarElementoLogica.setTelefono(agenciaPersistencia.getTelefonoAgencia());

                        listaAgenciaLogica.add(agregarElementoLogica);
                }

                miSesion.close();

                return listaAgenciaLogica;
        }

        public boolean existeAgencia(){
                boolean existe = true;
                List<AgenciaEntidad> comprobarCantidad = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        JPAPersistencia.eliminarWarnings();
                        comprobarCantidad = miSesion.createNativeQuery("SELECT * FROM AGENCIA WHERE ID = ?", AgenciaEntidad.class).setParameter(1, idAgencia).list();
                }catch (Exception e){
                        e.printStackTrace();
                }

                if (comprobarCantidad.size() != 1){
                        existe = false;
                }
                return existe;
        }

        public Agencia mostrarAgencia(){
                Agencia agencia = new Agencia();
                AgenciaEntidad AgenciaEntidad = null;
                List<AgenciaEntidad> agenciaSeleccionada = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()){
                        JPAPersistencia.eliminarWarnings();
                        if (miSesion != null){
                                agenciaSeleccionada = miSesion.createNativeQuery("SELECT * FROM Agencia WHERE id = ?",AgenciaEntidad.class).setParameter(1,idAgencia).list();

                        }
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                AgenciaEntidad = agenciaSeleccionada.get(0);

                agencia.setId(AgenciaEntidad.getIdAgencia());
                agencia.setNombre(AgenciaEntidad.getNombreAgencia());
                agencia.setDireccion(AgenciaEntidad.getDireccionAgencia());
                agencia.setTelefono(AgenciaEntidad.getTelefonoAgencia());

                return agencia;

        }

        public boolean agregarAgencia(Agencia agencia){
                boolean anadidaConExito = true;
                AgenciaEntidad nuevaAgencia = new AgenciaEntidad();
                Transaction transaction = null;
                if(!nuevaAgencia.existeAgencia()){
                    try (Session miSesion = JPAPersistencia.abrirSession()){
                        JPAPersistencia.eliminarWarnings();
                            transaction = miSesion.beginTransaction();
                            nuevaAgencia.idAgencia = agencia.getId();
                            nuevaAgencia.nombreAgencia = agencia.getNombre();
                            nuevaAgencia.direccionAgencia = agencia.getDireccion();
                            nuevaAgencia.telefonoAgencia = agencia.getTelefono();

                            miSesion.persist(nuevaAgencia);

                            miSesion.flush();
                            transaction.commit();

                    } catch (Exception e) {
                        anadidaConExito = false;
                    }

                }
                return anadidaConExito;
        }
        public boolean actualizarAgencia(Agencia agencia) throws SQLException {
                boolean actualizadoConExito = true;
                Transaction transaccion = null;

                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        transaccion = miSesion.beginTransaction();

                        // Obtener la entidad a actualizar por su ID
                        AgenciaEntidad agenciaJPA = miSesion.get(AgenciaEntidad.class, agencia.getId());

                        if (agenciaJPA != null) {
                                // Actualizar los campos de la entidad
                                agenciaJPA.setNombreAgencia(agencia.getNombre());
                                agenciaJPA.setDireccionAgencia(agencia.getDireccion());
                                agenciaJPA.setTelefonoAgencia(agencia.getTelefono());

                                miSesion.saveOrUpdate(agenciaJPA);
                                transaccion.commit();
                        } else {
                                System.out.println("La agencia con ID " + agencia.getId() + " no existe en la base de datos.");
                                actualizadoConExito = false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        actualizadoConExito = false;
                }
                return actualizadoConExito;
        }

        public boolean eliminarAgencia(Agencia agencia){
                boolean eliminadoConExito = true;
                Transaction transaccion = null;
                try(Session miSesion = JPAPersistencia.abrirSession()){
                        JPAPersistencia.eliminarWarnings();
                        transaccion = miSesion.beginTransaction();
                        AgenciaEntidad agenciaJPA = miSesion.get(AgenciaEntidad.class, agencia.getId());
                        miSesion.delete(agenciaJPA);
                        transaccion.commit();

                }catch(Exception e){
                        eliminadoConExito = false;
                }
                return eliminadoConExito;
        }

        public Agencia cargarAgencia (int id) throws SQLException {
                Agencia agencia = new Agencia();
                AgenciaEntidad agenciaJPAEntity = null;
                List<AgenciaEntidad> agenciaJPAEntities = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()){
                        if (miSesion != null){
                                agenciaJPAEntities = miSesion.createNativeQuery("SELECT * FROM Agencia WHERE id = ?",AgenciaEntidad.class).setParameter(1,id).list();
                        }
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                agenciaJPAEntity = agenciaJPAEntities.get(0);

                agencia.setId(agenciaJPAEntity.getIdAgencia());
                agencia.setNombre(agenciaJPAEntity.getNombreAgencia());
                agencia.setDireccion(agenciaJPAEntity.getDireccionAgencia());
                agencia.setTelefono(agenciaJPAEntity.getTelefonoAgencia());

                return agencia;
        }

}
