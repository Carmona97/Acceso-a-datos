package com.acdat.springboot.demo.controladores;

import com.acdat.springboot.demo.dao.DestinoDAO;
import com.acdat.springboot.demo.modelo.EntidadDestinos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api=rest/destinos")
public class ControladorDestinos {

    @Autowired
    private DestinoDAO destinoDAO;

    @GetMapping
    public List<EntidadDestinos> buscarDestinos() {
        return (List<EntidadDestinos>) destinoDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadDestinos> buscarDestinoPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadDestinos> destino = destinoDAO.findById(id);

        if (destino.isPresent()) {
            return ResponseEntity.ok().body(destino.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntidadDestinos guardarDestino(@Validated @RequestBody EntidadDestinos destino) {
        return destinoDAO.save(destino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDestino(@PathVariable(value = "id") int id) {
        Optional<EntidadDestinos> destino = destinoDAO.findById(id);
        if (destino.isPresent()) {
            destinoDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDestino(@RequestBody EntidadDestinos nuevoDestino, @PathVariable(value = "id") int id) {
        Optional<EntidadDestinos> destino = destinoDAO.findById(id);
        if (destino.isPresent()) {
            EntidadDestinos destinoExistente = destino.get();
            destinoExistente.setNombreDestino(nuevoDestino.getNombreDestino());
            destinoExistente.setDescripcion(nuevoDestino.getDescripcion());
            destinoExistente.setCostoEstadia(nuevoDestino.getCostoEstadia());
            destinoDAO.save(destinoExistente);
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}