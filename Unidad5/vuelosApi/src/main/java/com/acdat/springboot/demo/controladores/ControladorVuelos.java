package com.acdat.springboot.demo.controladores;

import com.acdat.springboot.demo.dao.VueloDAO;
import com.acdat.springboot.demo.modelo.EntidadVuelos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api=rest/vuelos")
public class ControladorVuelos {

    @Autowired
    private VueloDAO vueloDAO;

    @GetMapping
    public List<EntidadVuelos> buscarVuelos() {
        return vueloDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadVuelos> buscarVueloPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadVuelos> vuelo = vueloDAO.findById(id);

        if (vuelo.isPresent()) {
            return ResponseEntity.ok().body(vuelo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntidadVuelos guardarVuelo(@Validated @RequestBody EntidadVuelos vuelo) {
        return vueloDAO.save(vuelo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarVuelo(@PathVariable(value = "id") int id) {
        Optional<EntidadVuelos> vuelo = vueloDAO.findById(id);
        if (vuelo.isPresent()) {
            vueloDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVuelo(@RequestBody EntidadVuelos nuevoVuelo, @PathVariable(value = "id") int id) {
        Optional<EntidadVuelos> vuelo = vueloDAO.findById(id);
        if (vuelo.isPresent()) {
            EntidadVuelos vueloExistente = vuelo.get();
            vueloExistente.setOrigen(nuevoVuelo.getOrigen());
            vueloExistente.setDestino(nuevoVuelo.getDestino());
            vueloExistente.setFechaSalida(nuevoVuelo.getFechaSalida());
            vueloExistente.setFechaLlegada(nuevoVuelo.getFechaLlegada());
            vueloExistente.setCosto(nuevoVuelo.getCosto());
            vueloDAO.save(vueloExistente);
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
