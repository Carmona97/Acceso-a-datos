package org.acdat.negocio;

import org.acdat.jdbc.DestinoDao;
import org.acdat.jdbc.MiJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Destino {
    protected int id;
    protected String destino;
    protected String descripcion;
    protected double coste;

    public Destino(int id, String destino, String descripcion, double coste) {
        this.id = id;
        this.destino = destino;
        this.descripcion = descripcion;
        this.coste = coste;
    }
    public Destino(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destino)) return false;

        Destino destino1 = (Destino) o;

        if (getId() != destino1.getId()) return false;
        if (Double.compare(getCoste(), destino1.getCoste()) != 0) return false;
        if (!getDestino().equals(destino1.getDestino())) return false;
        return getDescripcion().equals(destino1.getDescripcion());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getDestino().hashCode();
        result = 31 * result + getDescripcion().hashCode();
        temp = Double.doubleToLongBits(getCoste());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Destino{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", coste=" + coste +
                '}';
    }

    public String mostrarDestinos() throws SQLException {
        String respuesta = "";
        List<Destino> destinoList = new ArrayList<>();

        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
        DestinoDao destinoDao = new DestinoDao();

        destinoList = destinoDao.mostrarDestinos(miJDBC.getConnection());

        for (Destino destino : destinoList) {
            respuesta = respuesta + "\n" + destino.toString();
        }

        return respuesta;
    }

    public boolean agregarDestino() throws SQLException {
        boolean respuesta = false;
        DestinoDao destinoDao = new DestinoDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = destinoDao.agregarDestino(miJDBC.getConnection(), this);
        }
        return respuesta;
    }

    public boolean actualizarDestino() throws SQLException {
        boolean respuesta = false;
        DestinoDao destinoDao = new DestinoDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = destinoDao.actualizarDestino(miJDBC.getConnection(), this);
        }
        return respuesta;
    }
    public boolean cargarDestino() throws SQLException {
        boolean respuesta = false;
        Destino destino = new Destino();
        DestinoDao destinoDao = new DestinoDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            destino = destinoDao.cargarDestino(miJDBC.getConnection(), this.id);
            this.id = destino.id;
            this.destino = destino.destino;
            this.descripcion = destino.descripcion;
            this.coste = destino.coste;
            respuesta = true;
        }

        return respuesta;
    }

    public boolean existeDestino() throws SQLException {
        boolean respuesta = false;
        DestinoDao destinoDao = new DestinoDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = destinoDao.existeDestino(miJDBC.getConnection(), this.id);
        }

        return respuesta;
    }

    public boolean eliminarDestino() throws SQLException {
        boolean respuesta = false;
        DestinoDao destinoDao = new DestinoDao();
        MiJDBC miJDBC = new MiJDBC();

        if (miJDBC.abrirConexion()) {
            respuesta = destinoDao.eliminarDestino(miJDBC.getConnection(), this.id);
        }
        return respuesta;
    }

    public void crearTablaDestino() {
        MiJDBC miJDBC = new MiJDBC();
        DestinoDao destinoDao = new DestinoDao();
        boolean conexionCorrecta = miJDBC.abrirConexion();

        if (conexionCorrecta) {
            destinoDao.crearTablaDestino(miJDBC.getConnection());
        }
    }

    public void eliminarTablaDestino() {
        MiJDBC miJDBC = new MiJDBC();
        DestinoDao destinoDao = new DestinoDao();
        boolean conexionCorrecta = miJDBC.abrirConexion();

        if (conexionCorrecta) {
            destinoDao.eliminarTablaDestino(miJDBC.getConnection());
        }
    }
}
