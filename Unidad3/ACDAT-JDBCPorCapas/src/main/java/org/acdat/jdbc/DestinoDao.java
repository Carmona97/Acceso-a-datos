package org.acdat.jdbc;

import org.acdat.negocio.Destino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DestinoDao {
    public List<Destino> mostrarDestinos(Connection connection) throws SQLException {
        List<Destino> destinoList = new ArrayList<>();
        Destino destino = null;
        String sql = "SELECT * FROM destinos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                destino = new Destino();
                destino.setDestino(resultSet.getString("nombre_destino"));
                destino.setDescripcion(resultSet.getString("descripcion"));
                destino.setCoste(resultSet.getDouble("costo_estadia"));
                destinoList.add(destino);
            }
            return destinoList;
        }
    }

    public boolean agregarDestino(Connection connection, Destino destino) throws SQLException {
        boolean respuesta = false;
        int res = 0;
        String sql = "INSERT INTO destinos (nombre_destino, descripcion, costo_estadia) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, destino.getDestino());
            preparedStatement.setString(2, destino.getDescripcion());
            preparedStatement.setDouble(3, destino.getCoste());
            res = preparedStatement.executeUpdate();
            if (res > 0) respuesta = true;
        }
        return respuesta;
    }

    public boolean actualizarDestino(Connection connection, Destino destino) throws SQLException {
        boolean respuesta = false;
        int res = 0;

        String sql = "UPDATE destinos SET nombre_destino=?, descripcion=?, costo_estadia=? WHERE destino_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, destino.getDestino());
            preparedStatement.setString(2, destino.getDescripcion());
            preparedStatement.setDouble(3, destino.getCoste());
            preparedStatement.setInt(4, destino.getId());
            res = preparedStatement.executeUpdate();
            if (res == 1) respuesta = true;
        }
        return respuesta;
    }

    public Destino cargarDestino(Connection connection, int id) throws SQLException {
        Destino destino = null;
        String sql = "SELECT * FROM destinos WHERE destino_id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                destino = new Destino();
                destino.setId(resultSet.getInt("destino_id"));
                destino.setDestino(resultSet.getString("nombre_destino"));
                destino.setDescripcion(resultSet.getString("descripcion"));
                destino.setCoste(resultSet.getDouble("costo_estadia"));
            }
            return destino;
        }
    }

    public boolean existeDestino(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM destinos WHERE destino_id = " + id;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            return resultSet.next();
        }
    }

    public boolean eliminarDestino(Connection connection, int destinoId) throws SQLException {
        boolean respuesta = false;
        int res = 0;

        String sql = "DELETE FROM destinos WHERE destino_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, destinoId);
            res = preparedStatement.executeUpdate();
            if (res == 1) respuesta = true;
        }
        return respuesta;
    }
    public void crearTablaDestino(Connection connection){
        String sql = "CREATE TABLE destinos (\n" +
                "                          destino_id SERIAL PRIMARY KEY,\n" +
                "                          nombre_destino VARCHAR(100) NOT NULL,\n" +
                "                          descripcion TEXT,\n" +
                "                          costo_estadia NUMERIC\n" +
                ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTablaDestino(Connection connection){
        String sql = "DROP TABLE IF EXISTS destinos;";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

