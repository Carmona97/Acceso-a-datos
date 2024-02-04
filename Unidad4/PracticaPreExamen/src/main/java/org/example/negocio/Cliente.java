package org.example.negocio;
import org.example.Entidades.ClienteEntidad;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    protected int id;
    protected String nombre;
    protected String correo;
    protected String telefono;

    public Cliente(int id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Cliente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;

        Cliente cliente = (Cliente) o;

        if (getId() != cliente.getId()) return false;
        if (!getNombre().equals(cliente.getNombre())) return false;
        if (!getCorreo().equals(cliente.getCorreo())) return false;
        return getTelefono().equals(cliente.getTelefono());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getNombre().hashCode();
        result = 31 * result + getCorreo().hashCode();
        result = 31 * result + getTelefono().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public boolean existeCliente() throws SQLException {
        boolean respuesta = false;
        ClienteEntidad clienteJPAEntity = new ClienteEntidad();
        respuesta = clienteJPAEntity.existeCliente();

        return respuesta;
    }

    public String mostrarClientesLogica() throws Exception {
        String respuesta = "";
        ClienteEntidad clientePersistencia = new ClienteEntidad();
        ArrayList<Cliente> clienteLogica = clientePersistencia.mostrarClientesPersistencia();

        for (Cliente cliente : clienteLogica) {
            respuesta = respuesta + "\n" + cliente.toString();
        }

        return respuesta;
    }

    public boolean agregarCliente() throws SQLException {
        boolean respuesta = false;

        ClienteEntidad clienteNuevo = new ClienteEntidad();
        respuesta = clienteNuevo.agregarCliente(this);

        return respuesta;
    }

    public boolean actualizarCliente() throws SQLException {
        boolean respuesta = false;

        ClienteEntidad clienteJPAEntity = new ClienteEntidad();
        respuesta = clienteJPAEntity.actualizarCliente(this);

        return respuesta;
    }

    public boolean eliminarCliente() throws SQLException {
        boolean respuesta = false;

        ClienteEntidad clienteJPAEntity = new ClienteEntidad();
        respuesta = clienteJPAEntity.eliminarCliente(this);

        return respuesta;
    }

    public boolean cargarCliente() throws SQLException {
        boolean respuesta = false;

        ClienteEntidad clienteJPAEntity = new ClienteEntidad();
        Cliente cliente = clienteJPAEntity.cargarCliente(this.id);
        if (cliente != null) {
            this.id = cliente.id;
            this.nombre = cliente.nombre;
            this.correo = cliente.correo;
            this.telefono = cliente.telefono;

            respuesta = true;
        }
        return respuesta;
    }


    public boolean precargarClientes() throws SQLException{
        boolean respuesta = false;
        String sql = "INSERT INTO clientes (nombre_cliente, correo_cliente, telefono_cliente) VALUES\n" +
                "                                                                            ('Luisa Martínez', 'luisa@email.com', '+1234567890'),\n" +
                "                                                                            ('Pedro González', 'pedro@email.com', '+2345678901'),\n" +
                "                                                                            ('María Rodríguez', 'maria@email.com', '+3456789012'),\n" +
                "                                                                            ('Alejandro López', 'alejandro@email.com', '+4567890123'),\n" +
                "                                                                            ('Ana Sánchez', 'ana@email.com', '+5678901234'),\n" +
                "                                                                            ('Javier Ramírez', 'javier@email.com', '+6789012345'),\n" +
                "                                                                            ('Isabel Fernández', 'isabel@email.com', '+7890123456'),\n" +
                "                                                                            ('Carlos Pérez', 'carlos@email.com', '+8901234567'),\n" +
                "                                                                            ('Elena Gómez', 'elena@email.com', '+9012345678'),\n" +
                "                                                                            ('Andrés Díaz', 'andres@email.com', '+0123456789'),\n" +
                "                                                                            ('Laura Torres', 'laura@email.com', '+9876543210'),\n" +
                "                                                                            ('Roberto Herrera', 'roberto@email.com', '+8765432109'),\n" +
                "                                                                            ('Silvia Castro', 'silvia@email.com', '+7654321098'),\n" +
                "                                                                            ('Curro García', 'Curro@email.com', '+6543210987'),\n" +
                "                                                                            ('Carmen Ruiz', 'carmen@email.com', '+5432109876'),\n" +
                "                                                                            ('Fernando Méndez', 'fernando@email.com', '+4321098765'),\n" +
                "                                                                            ('Sara Vargas', 'sara@email.com', '+3210987654'),\n" +
                "                                                                            ('Héctor Navarro', 'hector@email.com', '+2109876543'),\n" +
                "                                                                            ('Lucía Medina', 'lucia@email.com', '+1098765432'),\n" +
                "                                                                            ('Diego Ríos', 'diego@email.com', '+9876543210'),\n" +
                "                                                                            ('Patricia Silva', 'patricia@email.com', '+8765432109'),\n" +
                "                                                                            ('Adrián Mendoza', 'adrian@email.com', '+7654321098'),\n" +
                "                                                                            ('Natalia Romero', 'natalia@email.com', '+6543210987'),\n" +
                "                                                                            ('Martín Castro', 'martin@email.com', '+5432109876'),\n" +
                "                                                                            ('Eva Morales', 'eva@email.com', '+4321098765'),\n" +
                "                                                                            ('Raúl Guzmán', 'raul@email.com', '+3210987654'),\n" +
                "                                                                            ('Lorena Ortega', 'lorena@email.com', '+2109876543'),\n" +
                "                                                                            ('Pablo Roca', 'pablo@email.com', '+1098765432'),\n" +
                "                                                                            ('Beatriz León', 'beatriz@email.com', '+9876543210'),\n" +
                "                                                                            ('Andrea Díaz', 'andrea@email.com', '+8765432109'),\n" +
                "                                                                            ('Óscar Muñoz', 'oscar@email.com', '+7654321098'),\n" +
                "                                                                            ('Marta Cordero', 'marta@email.com', '+6543210987'),\n" +
                "                                                                            ('Ricardo Soto', 'ricardo@email.com', '+5432109876'),\n" +
                "                                                                            ('Cristina Paredes', 'cristina@email.com', '+4321098765'),\n" +
                "                                                                            ('Jorge Gallego', 'jorge@email.com', '+3210987654'),\n" +
                "                                                                            ('Natalie Rojas', 'natalie@email.com', '+2109876543'),\n" +
                "                                                                            ('Gabriel Gil', 'gabriel@email.com', '+1098765432'),\n" +
                "                                                                            ('Pepita', 'pepita@email.com', '+9876543210'),\n" +
                "                                                                            ('Manuel Mora', 'manuel@email.com', '+8765432109'),\n" +
                "                                                                            ('Rosa Serrano', 'rosa@email.com', '+7654321098'),\n" +
                "                                                                            ('Hugo Guerra', 'hugo@email.com', '+6543210987'),\n" +
                "                                                                            ('Julia Torres', 'julia@email.com', '+5432109876'),\n" +
                "                                                                            ('Eduardo Alvarado', 'eduardo@email.com', '+4321098765'),\n" +
                "                                                                            ('Mónica Pinto', 'monica@email.com', '+3210987654'),\n" +
                "                                                                            ('Gustavo Ramos', 'gustavo@email.com', '+2109876543'),\n" +
                "                                                                            ('Paola Jiménez', 'paola@email.com', '+1098765432'),\n" +
                "                                                                            ('Daniel Salgado', 'daniel@email.com', '+9876543210');";

        return respuesta;
    }

}
