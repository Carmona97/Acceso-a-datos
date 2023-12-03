package org.acdat.negocio;

import org.acdat.jdbc.AgenciaDao;
import org.acdat.jdbc.ClienteDao;
import org.acdat.jdbc.MiJDBC;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Agencia {
    protected int id;
    protected String nombre;
    protected String direccion;
    protected String telefono;

    public Agencia(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Agencia() {

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agencia)) return false;

        Agencia agencia = (Agencia) o;

        if (id != agencia.id) return false;
        if (!nombre.equals(agencia.nombre)) return false;
        if (!direccion.equals(agencia.direccion)) return false;
        return telefono.equals(agencia.telefono);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + telefono.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Agencia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }


    public String mostrarAgencias() throws SQLException {
        String respuesta="";
        List<Agencia> agenciaList = new ArrayList<Agencia>();

        MiJDBC miJDBC = new MiJDBC();
        miJDBC.abrirConexion();
        AgenciaDao agenciaDao = new AgenciaDao();

        agenciaList = agenciaDao.mostrarAgencia(miJDBC.getConnection());

        for (Agencia agencia : agenciaList) {
            respuesta = respuesta + "\n" + agencia.toString();
        }

        return respuesta;
    }

    public boolean  agregarAgencia() throws SQLException {
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        boolean conexionCorrecta = miJDBC.abrirConexion();
        AgenciaDao agenciaDao = new AgenciaDao();
        if (conexionCorrecta) {
            agenciaDao.agregarAgencia(miJDBC.getConnection(),this);
        }

        return respuesta;
    }

    public boolean actualizarAgencia() throws SQLException {
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        boolean conexionCorrecta = miJDBC.abrirConexion();
        AgenciaDao agenciaDao = new AgenciaDao();
        if (conexionCorrecta){
            respuesta = agenciaDao.actualizarAgencia(miJDBC.getConnection(),this);
        }
        return respuesta;
    }

    public boolean cargarAgencia() throws SQLException {
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        Agencia agencia = new Agencia();
        boolean conexionCorrecta = miJDBC.abrirConexion();
        AgenciaDao agenciaDao = new AgenciaDao();
        if(conexionCorrecta){
            agenciaDao.cargarAgencia(miJDBC.getConnection(), this.getId());
            this.id = agencia.id;
            this.nombre = agencia.nombre;
            this.direccion = agencia.direccion;
            this.telefono = agencia.telefono;
        }
        return respuesta;
    }

    public boolean existeAgencia() throws SQLException {
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        Agencia agencia = new Agencia();
        boolean conexionCorrecta = miJDBC.abrirConexion();
        AgenciaDao agenciaDao = new AgenciaDao();
        if(conexionCorrecta){
            if (agenciaDao.existeAgencia(miJDBC.getConnection(), this.getId())) {
                respuesta = true;
            }
        }


        return respuesta;
    }

    public boolean eliminarAgencia() throws SQLException {
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        AgenciaDao agenciaDao = new AgenciaDao();
        boolean conexionCorrecta = miJDBC.abrirConexion();
        if(conexionCorrecta){
            agenciaDao.eliminarAgencia(miJDBC.getConnection(), this.getId());
        }

        return respuesta;
    }

    public boolean crearTablaAgencia(){
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        AgenciaDao agenciaDao = new AgenciaDao();
        boolean conexionCorrecta = miJDBC.abrirConexion();
        if(conexionCorrecta){
           agenciaDao.crearTablaAgencia(miJDBC.getConnection());
           respuesta = true;
        }
        return respuesta;
    }

    public boolean eliminarTablaAgencia(){
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        AgenciaDao agenciaDao = new AgenciaDao();
        boolean conexionCorrecta = miJDBC.abrirConexion();
        if(conexionCorrecta){
           agenciaDao.eliminarTablaAgencia(miJDBC.getConnection());
           respuesta = true;
        }
        return respuesta;
    }

    public boolean precargarAgencias() throws SQLException{
        boolean respuesta = false;
        MiJDBC miJDBC = new MiJDBC();
        AgenciaDao agenciaDao = new AgenciaDao();
        String sql = "INSERT INTO agencias (nombre_agencia, direccion_agencia, telefono_agencia) VALUES\n" +
                "                                                                               ('Viajes Fantásticos', 'Calle Principal 123', '+999888777'),\n" +
                "                                                                               ('Aventuras Globales', 'Avenida Central 456', '+333222111'),\n" +
                "                                                                               ('Destinos Soñados', 'Plaza de la Libertad 789', '+555444333'),\n" +
                "                                                                               ('Mundo Viajero', 'Calle Viajera 101', '+777666555'),\n" +
                "                                                                               ('Turismo Excelente', 'Avenida de los Sueños 202', '+888777666'),\n" +
                "                                                                               ('Rutas Inolvidables', 'Paseo del Descanso 303', '+111000999'),\n" +
                "                                                                               ('Viajes y Más', 'Carrera Aventurera 505', '+222111000'),\n" +
                "                                                                               ('Explora el Mundo', 'Rincón del Viajero 606', '+444333222'),\n" +
                "                                                                               ('Destinos Únicos', 'Calle de la Aventura 707', '+666555444'),\n" +
                "                                                                               ('Aventuras Extremas', 'Plaza del Viajero 808', '+000999888');";
        boolean conexionCorrecta = miJDBC.abrirConexion();
        Statement stmt = miJDBC.getConnection().createStatement();
        if(conexionCorrecta){
            stmt.executeUpdate(sql);
            respuesta = true;
        }
        return respuesta;
    }
}
