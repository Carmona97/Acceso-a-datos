package com.acdat.springboot.demo.controladores;

import com.acdat.springboot.demo.dao.IAutoresDAO;
import com.acdat.springboot.demo.dto.AutoresDTO;
import com.acdat.springboot.demo.modelo.EntidadAutores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api-rest/autores")
public class controladorAutores {

    @Autowired
    private IAutoresDAO autoresDAO;

    @GetMapping
    public List<EntidadAutores> buscarAutores(){
        return (List<EntidadAutores>)  autoresDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadAutores> buscarAutoresPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadAutores> autor = autoresDAO.findById(id);

        if (autor.isPresent()) {
            return ResponseEntity.ok().body(autor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntidadAutores guardarAutor(@Validated @RequestBody EntidadAutores autor) {
        return autoresDAO.save(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarAutor(@PathVariable(value = "id") int id) {
        Optional<EntidadAutores> autor = autoresDAO.findById(id);
        if (autor.isPresent()) {
            autoresDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAutor(@RequestBody EntidadAutores nuevoAutor, @PathVariable(value = "id") int id) {
        Optional<EntidadAutores> autor = autoresDAO.findById(id);
        if (autor.isPresent()) {
            EntidadAutores autorExistente = autor.get();
            autorExistente.setNombreAutor(nuevoAutor.getNombreAutor());
            autorExistente.setPais(nuevoAutor.getPais());
            autoresDAO.save(autorExistente);
            return ResponseEntity.ok().body("Update");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
/*    @PostMapping
    public void guardar3autores(@Validated @RequestBody EntidadAutores autor1,EntidadAutores autor2,EntidadAutores autor3){
        AutoresDTO autoresDTO1 = new AutoresDTO();
        AutoresDTO autoresDTO2 = new AutoresDTO();
        AutoresDTO autoresDTO3 = new AutoresDTO();
        autoresDTO1.setIdAutor(autor1.getIdAutor());
        autoresDTO1.setNombreAutor(autor1.getNombreAutor());
        autoresDTO1.setPais(autor1.getPais());
        autoresDTO2.setIdAutor(autor2.getIdAutor());
        autoresDTO2.setNombreAutor(autor2.getNombreAutor());
        autoresDTO2.setPais(autor2.getPais());
        autoresDTO3.setIdAutor(autor3.getIdAutor());
        autoresDTO3.setNombreAutor(autor3.getNombreAutor());
        autoresDTO3.setPais(autor3.getPais());
    }*/
}
