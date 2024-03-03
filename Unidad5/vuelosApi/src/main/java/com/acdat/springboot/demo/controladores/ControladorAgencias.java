package com.acdat.springboot.demo.controladores;

import com.acdat.springboot.demo.dao.AgenciaDAO;
import com.acdat.springboot.demo.modelo.EntidadAgencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api=rest/agencias")
public class ControladorAgencias {


    @Autowired
    private AgenciaDAO agenciaDAO;

    @GetMapping
    public List<EntidadAgencias> buscarAgencias(){
        return (List<EntidadAgencias>)  agenciaDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadAgencias> buscarAgenciaPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadAgencias> agencia = agenciaDAO.findById(id);

        if (agencia.isPresent()) {
            return ResponseEntity.ok().body(agencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntidadAgencias guardarAgencia(@Validated @RequestBody EntidadAgencias agencia) {
        return agenciaDAO.save(agencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarAgencia(@PathVariable(value = "id") int id) {
        Optional<EntidadAgencias> agencia = agenciaDAO.findById(id);
        if (agencia.isPresent()) {
            agenciaDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAgencia(@RequestBody EntidadAgencias nuevaAgencia, @PathVariable(value = "id") int id) {
        Optional<EntidadAgencias> agencia = agenciaDAO.findById(id);
        if (agencia.isPresent()) {
            EntidadAgencias agenciaExistente = agencia.get();
            agenciaExistente.setNombreAgencia(nuevaAgencia.getNombreAgencia());
            agenciaExistente.setDireccionAgencia(nuevaAgencia.getDireccionAgencia());
            agenciaExistente.setTelefonoAgencia(nuevaAgencia.getTelefonoAgencia());
            agenciaDAO.save(agenciaExistente);
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
