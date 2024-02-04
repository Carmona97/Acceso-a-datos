package org.example.Entidades;

import jakarta.persistence.*;
import org.example.negocio.Destino;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Destino",schema = "public", catalog = "TuViajePreExamen")
public class DestinoEntidad {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "id", nullable = false)
        private int idDestino;

        @Basic
        @Column(name = "destino", nullable = false)
        private String destinoDestino;
        @Basic
        @Column(name = "descripcion", nullable = false)
        private String descripcionDestino;
        @Basic
        @Column(name = "coste", nullable = false)
        private double costeDestino;

        public int getIdDestino() {
                return idDestino;
        }


        public String getDestinoDestino() {
                return destinoDestino;
        }

        public void setDestinoDestino(String destinoDestino) {
                this.destinoDestino = destinoDestino;
        }

        public String getDescripcionDestino() {
                return descripcionDestino;
        }

        public void setDescripcionDestino(String descripcionDestino) {
                this.descripcionDestino = descripcionDestino;
        }

        public double getCosteDestino() {
                return costeDestino;
        }

        public void setCosteDestino(double costeDestino) {
                this.costeDestino = costeDestino;
        }


        public ArrayList<Destino> mostrarDestinosPersistencia() throws Exception {
                ArrayList<DestinoEntidad> listaDestinoPersistencia = new ArrayList<>();
                ArrayList<Destino> listaDestinoLogica = new ArrayList<>();

                Session miSesion = JPAPersistencia.abrirSession();
                JPAPersistencia.eliminarWarnings();
                listaDestinoPersistencia = (ArrayList<DestinoEntidad>) miSesion
                        .createNativeQuery("SELECT * FROM Destino", DestinoEntidad.class)
                        .list();

                for (DestinoEntidad destinoPersistencia : listaDestinoPersistencia) {
                        Destino agregarElementoLogica = new Destino();
                        agregarElementoLogica.setId(destinoPersistencia.getIdDestino());
                        agregarElementoLogica.setDestino(destinoPersistencia.getDestinoDestino());
                        agregarElementoLogica.setDescripcion(destinoPersistencia.getDescripcionDestino());
                        agregarElementoLogica.setCoste(destinoPersistencia.getCosteDestino());

                        listaDestinoLogica.add(agregarElementoLogica);
                }

                miSesion.close();

                return listaDestinoLogica;
        }

        public boolean existeDestino() {
                boolean existe = true;
                List<DestinoEntidad> comprobarCantidad = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        JPAPersistencia.eliminarWarnings();
                        comprobarCantidad = miSesion.createNativeQuery("SELECT * FROM Destino WHERE ID = ?", DestinoEntidad.class).setParameter(1, idDestino).list();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                if (comprobarCantidad.size() != 1) {
                        existe = false;
                }
                return existe;
        }

        public Destino mostrarDestino() {
                Destino destino = new Destino();
                DestinoEntidad destinoEntidad = null;
                List<DestinoEntidad> destinoSeleccionado = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        JPAPersistencia.eliminarWarnings();
                        if (miSesion != null) {
                                destinoSeleccionado = miSesion
                                        .createNativeQuery("SELECT * FROM Destino WHERE id = ?", DestinoEntidad.class)
                                        .setParameter(1, idDestino)
                                        .list();
                        }
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                destinoEntidad = destinoSeleccionado.get(0);

                destino.setId(destinoEntidad.getIdDestino());
                destino.setDestino(destinoEntidad.getDestinoDestino());
                destino.setDescripcion(destinoEntidad.getDescripcionDestino());
                destino.setCoste(destinoEntidad.getCosteDestino());

                return destino;
        }

        public boolean agregarDestino(Destino destino) {
                boolean anadidoConExito = true;
                DestinoEntidad nuevoDestino = new DestinoEntidad();
                Transaction transaccion = null;
                if (!nuevoDestino.existeDestino()) {
                        try (Session miSesion = JPAPersistencia.abrirSession()) {
                                JPAPersistencia.eliminarWarnings();
                                transaccion = miSesion.beginTransaction();
                                nuevoDestino.idDestino = destino.getId();
                                nuevoDestino.destinoDestino = destino.getDestino();
                                nuevoDestino.descripcionDestino = destino.getDescripcion();
                                nuevoDestino.costeDestino = destino.getCoste();

                                miSesion.persist(nuevoDestino);

                                miSesion.flush();
                                transaccion.commit();

                        } catch (Exception e) {
                                anadidoConExito = false;
                        }

                }
                return anadidoConExito;
        }

        public boolean actualizarDestino(Destino destino) {
                boolean actualizadoConExito = true;
                Transaction transaccion = null;

                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        transaccion = miSesion.beginTransaction();

                        // Obtener la entidad a actualizar por su ID
                        DestinoEntidad destinoJPA = miSesion.get(DestinoEntidad.class, destino.getId());

                        if (destinoJPA != null) {
                                // Actualizar los campos de la entidad
                                destinoJPA.setDestinoDestino(destino.getDestino());
                                destinoJPA.setDescripcionDestino(destino.getDescripcion());
                                destinoJPA.setCosteDestino(destino.getCoste());

                                miSesion.saveOrUpdate(destinoJPA);
                                transaccion.commit();
                        } else {
                                System.out.println("El destino con ID " + destino.getId() + " no existe en la base de datos.");
                                actualizadoConExito = false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        actualizadoConExito = false;
                }
                return actualizadoConExito;
        }

        public boolean eliminarDestino(Destino destino) {
                boolean eliminadoConExito = true;
                Transaction transaccion = null;
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        JPAPersistencia.eliminarWarnings();
                        transaccion = miSesion.beginTransaction();
                        DestinoEntidad destinoJPA = miSesion.get(DestinoEntidad.class, destino.getId());
                        miSesion.delete(destinoJPA);
                        transaccion.commit();

                } catch (Exception e) {
                        e.printStackTrace();
                        eliminadoConExito = false;
                }

                return eliminadoConExito;
        }

        public Destino cargarDestino(int id){
                Destino destino = new Destino();
                DestinoEntidad destinoJPAEntity = null;
                List<DestinoEntidad> destinoJPAEntities = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        if (miSesion != null) {
                                destinoJPAEntities = miSesion.createNativeQuery("SELECT * FROM Destino WHERE id = ?", DestinoEntidad.class).setParameter(1, id).list();
                        }
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                destinoJPAEntity = destinoJPAEntities.get(0);

                destino.setId(destinoJPAEntity.getIdDestino());
                destino.setDestino(destinoJPAEntity.getDestinoDestino());
                destino.setDescripcion(destinoJPAEntity.getDescripcionDestino());
                destino.setCoste(destinoJPAEntity.getCosteDestino());

                return destino;
        }

}