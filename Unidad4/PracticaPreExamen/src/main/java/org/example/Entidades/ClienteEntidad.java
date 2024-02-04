package org.example.Entidades;

import jakarta.persistence.*;
import org.example.negocio.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cliente",schema = "public", catalog = "TuViajePreExamen")
public class ClienteEntidad {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "id", nullable = false)
        private int idCliente;

        @Basic
        @Column(name = "nombre", nullable = false)
        private String nombreCliente;
        @Basic
        @Column(name = "correo", nullable = false)
        private String correoCliente;
        @Basic
        @Column(name = "telefono", nullable = false)
        private String telefonoCliente;

        public int getIdCliente() {
                return idCliente;
        }

        public void setIdCliente(int idCliente) {
                this.idCliente = idCliente;
        }

        public String getNombreCliente() {
                return nombreCliente;
        }

        public void setNombreCliente(String nombreCliente) {
                this.nombreCliente = nombreCliente;
        }

        public String getCorreoCliente() {
                return correoCliente;
        }

        public void setCorreoCliente(String correoCliente) {
                this.correoCliente = correoCliente;
        }

        public String getTelefonoCliente() {
                return telefonoCliente;
        }

        public void setTelefonoCliente(String telefonoCliente) {
                this.telefonoCliente = telefonoCliente;
        }

        public ArrayList<Cliente> mostrarClientesPersistencia() throws Exception {
                ArrayList<ClienteEntidad> listaClientePersistencia = new ArrayList<>();
                ArrayList<Cliente> listaClienteLogica = new ArrayList<>();

                Session miSesion = JPAPersistencia.abrirSession();
                JPAPersistencia.eliminarWarnings();
                listaClientePersistencia = (ArrayList<ClienteEntidad>) miSesion.createNativeQuery("SELECT * FROM Cliente", ClienteEntidad.class).list();

                for (ClienteEntidad clientePersistencia : listaClientePersistencia) {
                        Cliente agregarElementoLogica = new Cliente();
                        agregarElementoLogica.setId(clientePersistencia.getIdCliente());
                        agregarElementoLogica.setNombre(clientePersistencia.getNombreCliente());
                        agregarElementoLogica.setCorreo(clientePersistencia.getCorreoCliente());
                        agregarElementoLogica.setTelefono(clientePersistencia.getTelefonoCliente());

                        listaClienteLogica.add(agregarElementoLogica);
                }

                miSesion.close();

                return listaClienteLogica;
        }

        public boolean existeCliente() {
                boolean existe = true;
                List<ClienteEntidad> comprobarCantidad = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        JPAPersistencia.eliminarWarnings();
                        comprobarCantidad = miSesion.createNativeQuery("SELECT * FROM Cliente WHERE ID = ?", ClienteEntidad.class).setParameter(1, idCliente).list();
                } catch (Exception e) {
                        e.printStackTrace();
                }

                if (comprobarCantidad.size() != 1) {
                        existe = false;
                }
                return existe;
        }

        public Cliente mostrarCliente() {
                Cliente cliente = new Cliente();
                ClienteEntidad clienteEntidad = null;
                List<ClienteEntidad> clienteSeleccionado = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        JPAPersistencia.eliminarWarnings();
                        if (miSesion != null) {
                                clienteSeleccionado = miSesion.createNativeQuery("SELECT * FROM Cliente WHERE id = ?", ClienteEntidad.class).setParameter(1, idCliente).list();
                        }
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                clienteEntidad = clienteSeleccionado.get(0);

                cliente.setId(clienteEntidad.getIdCliente());
                cliente.setNombre(clienteEntidad.getNombreCliente());
                cliente.setCorreo(clienteEntidad.getCorreoCliente());
                cliente.setTelefono(clienteEntidad.getTelefonoCliente());

                return cliente;
        }

        public boolean agregarCliente(Cliente cliente) {
                boolean anadidoConExito = true;
                ClienteEntidad nuevoCliente = new ClienteEntidad();
                Transaction transaccion = null;
                if (!nuevoCliente.existeCliente()) {
                        try (Session miSesion = JPAPersistencia.abrirSession()) {
                                JPAPersistencia.eliminarWarnings();
                                transaccion = miSesion.beginTransaction();
                                nuevoCliente.idCliente = cliente.getId();
                                nuevoCliente.nombreCliente = cliente.getNombre();
                                nuevoCliente.correoCliente = cliente.getCorreo();
                                nuevoCliente.telefonoCliente = cliente.getTelefono();

                                miSesion.persist(nuevoCliente);

                                miSesion.flush();
                                transaccion.commit();

                        } catch (Exception e) {
                                anadidoConExito = false;
                        }

                }
                return anadidoConExito;
        }

        public boolean actualizarCliente(Cliente cliente) {
                boolean actualizadoConExito = true;
                Transaction transaccion = null;

                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        transaccion = miSesion.beginTransaction();

                        // Obtener la entidad a actualizar por su ID
                        ClienteEntidad clienteJPA = miSesion.get(ClienteEntidad.class, cliente.getId());

                        if (clienteJPA != null) {
                                // Actualizar los campos de la entidad
                                clienteJPA.setNombreCliente(cliente.getNombre());
                                clienteJPA.setCorreoCliente(cliente.getCorreo());
                                clienteJPA.setTelefonoCliente(cliente.getTelefono());

                                miSesion.saveOrUpdate(clienteJPA);
                                transaccion.commit();
                        } else {
                                System.out.println("El cliente con ID " + cliente.getId() + " no existe en la base de datos.");
                                actualizadoConExito = false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        actualizadoConExito = false;
                }
                return actualizadoConExito;
        }

        public boolean eliminarCliente(Cliente cliente) {
                boolean eliminadoConExito = true;
                Transaction transaccion = null;
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        JPAPersistencia.eliminarWarnings();
                        transaccion = miSesion.beginTransaction();
                        ClienteEntidad clienteJPA = miSesion.get(ClienteEntidad.class, cliente.getId());
                        miSesion.delete(clienteJPA);
                        transaccion.commit();

                } catch (Exception e) {
                        eliminadoConExito = false;
                }
                return eliminadoConExito;
        }

        public Cliente cargarCliente(int id){
                Cliente cliente = new Cliente();
                ClienteEntidad clienteJPAEntity = null;
                List<ClienteEntidad> clienteJPAEntities = new ArrayList<>();
                try (Session miSesion = JPAPersistencia.abrirSession()) {
                        if (miSesion != null) {
                                clienteJPAEntities = miSesion
                                        .createNativeQuery("SELECT * FROM Cliente WHERE id = ?", ClienteEntidad.class)
                                        .setParameter(1, id)
                                        .list();
                        }
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }

                clienteJPAEntity = clienteJPAEntities.get(0);

                cliente.setId(clienteJPAEntity.getIdCliente());
                cliente.setNombre(clienteJPAEntity.getNombreCliente());
                cliente.setCorreo(clienteJPAEntity.getCorreoCliente());
                cliente.setTelefono(clienteJPAEntity.getTelefonoCliente());

                return cliente;
        }
}
