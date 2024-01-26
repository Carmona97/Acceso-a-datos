package org.acdat.jdbc;
import org.acdat.negocio.Vuelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class VueloDao {
    public List<Vuelo> mostrarVuelos(Connection connection) throws SQLException {
        List<Vuelo> VueloList = new ArrayList<Vuelo>();
        Vuelo vuelo = null;
        String sql = "SELECT * FROM vuelos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                vuelo = new Vuelo();
                vuelo.setId(resultSet.getInt(1));
                vuelo.setOrigen(resultSet.getString("origen"));
                vuelo.setDestino(resultSet.getString("destino"));
                vuelo.setFecha_salida(resultSet.getString("fecha_salida"));
                vuelo.setFecha_llegada(resultSet.getString("fecha_llegada"));
                vuelo.setCosto(resultSet.getDouble("costo"));
                VueloList.add(vuelo);
            }
            return VueloList;
        }
    }
        public boolean agregarVuelo(Connection connection, Vuelo Vuelo) throws SQLException {
            boolean respuesta = false;
            int res = 0;
            String sql = "INSERT INTO vuelos (origen, destino, fecha_salida, fecha_llegada, costo) VALUES(?, ?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Vuelo.getOrigen());
                preparedStatement.setString(2, Vuelo.getDestino());
                preparedStatement.setString(3, Vuelo.getFecha_salida());
                preparedStatement.setString(4, Vuelo.getFecha_llegada());
                preparedStatement.setDouble(5, Vuelo.getCosto());
                res = preparedStatement.executeUpdate();
                if (res > 0) {
                    respuesta = true;
                }
            }
            return respuesta;
        }

        public boolean actualizarVuelo(Connection connection, Vuelo Vuelo) throws SQLException {
            boolean respuesta = false;
            int res = 0;

            String sql = "UPDATE vuelos SET origen=?, destino=?, fecha_salida=?, fecha_llegada=?, costo=? WHERE vuelo_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Vuelo.getOrigen());
                preparedStatement.setString(2, Vuelo.getDestino());
                preparedStatement.setString(3, Vuelo.getFecha_salida());
                preparedStatement.setString(4, Vuelo.getFecha_llegada());
                preparedStatement.setDouble(5, Vuelo.getCosto());
                res = preparedStatement.executeUpdate();
                if (res > 0) {
                    respuesta = true;
                }
            }
            return respuesta;
        }

        public Vuelo cargarVuelo(Connection connection,int id) throws SQLException {

            Vuelo vuelo = null;
            String sql = "SELECT * FROM vuelos WHERE vuelo_id = " + id;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                //System.out.println("ID\tNombre\t\tCorreo\t\tTeléfono");
                //System.out.println("--------------------------------------------------------------------------");
                while (resultSet.next()) {
                    vuelo = new Vuelo();
                    vuelo.setOrigen(resultSet.getString("origen"));
                    vuelo.setDestino(resultSet.getString("destino"));
                    vuelo.setFecha_salida(resultSet.getString("fecha_salida"));
                    vuelo.setFecha_llegada(resultSet.getString("fecha_llegada"));
                    vuelo.setCosto(resultSet.getDouble("costo"));
                }
                // System.out.println();
                return vuelo;
            }

        }

        public boolean existeVuelo(Connection connection,int id) throws SQLException {
            Vuelo vuelo = null;
            String sql = "SELECT * FROM vuelos WHERE vuelo_id = " + id;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        public boolean eliminarVuelo(Connection connection,int VueloId) throws SQLException {
            boolean respuesta = false;
            int res = 0;
            String sql = "DELETE FROM vuelos WHERE vuelo_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, VueloId);
                res = preparedStatement.executeUpdate();
                if (res == 1) {
                    respuesta = true;
                }
            }
            return respuesta;
        }

        public void crearTablaVuelo(Connection connection){
            String sql = "CREATE TABLE vuelos (\n" +
                    "                        vuelo_id SERIAL PRIMARY KEY,\n" +
                    "                        origen VARCHAR(50),\n" +
                    "                        destino VARCHAR(50),\n" +
                    "                        fecha_salida DATE,\n" +
                    "                        fecha_llegada DATE,\n" +
                    "                        costo NUMERIC(10, 2)\n" +
                    ");";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void eliminarTablaVuelo(Connection connection){
            String sql = "DROP TABLE IF EXISTS vuelos;";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

