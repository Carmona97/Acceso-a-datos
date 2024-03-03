package com.acdat.springboot.demo.controladores;

import com.acdat.springboot.demo.dao.ClienteDAO;
import com.acdat.springboot.demo.modelo.EntidadClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api=rest/clientes")
public class ControladorClientes {

    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping
    public List<EntidadClientes> buscarClientes() {
        return (List<EntidadClientes>) clienteDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadClientes> buscarClientePorId(@PathVariable(value = "id") int id) {
        Optional<EntidadClientes> cliente = clienteDAO.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntidadClientes guardarCliente(@Validated @RequestBody EntidadClientes cliente) {
        return clienteDAO.save(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCliente(@PathVariable(value = "id") int id) {
        Optional<EntidadClientes> cliente = clienteDAO.findById(id);
        if (cliente.isPresent()) {
            clienteDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@RequestBody EntidadClientes nuevoCliente, @PathVariable(value = "id") int id) {
        Optional<EntidadClientes> cliente = clienteDAO.findById(id);
        if (cliente.isPresent()) {
            EntidadClientes clienteExistente = cliente.get();
            clienteExistente.setNombreCliente(nuevoCliente.getNombreCliente());
            clienteExistente.setCorreoCliente(nuevoCliente.getCorreoCliente());
            clienteExistente.setTelefonoCliente(nuevoCliente.getTelefonoCliente());
            clienteDAO.save(clienteExistente);
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
