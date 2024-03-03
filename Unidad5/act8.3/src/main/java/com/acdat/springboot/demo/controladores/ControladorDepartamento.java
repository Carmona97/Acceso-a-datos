package com.acdat.springboot.demo.controladores;

import com.acdat.springboot.demo.dao.IDepartamentosDAO;
import com.acdat.springboot.demo.modelo.EntidadDepartamentos;
import com.acdat.springboot.demo.modelo.EntidadEmpleados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("api=rest/departamentos")
public class ControladorDepartamento {
    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping

    public List<EntidadDepartamentos> buscarDepartamentos(){
        return (List<EntidadDepartamentos>)  departamentosDAO.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntidadDepartamentos> buscarDepartamentoPorDepno(@PathVariable(value = "id")int id){
        Optional<EntidadDepartamentos> departamento = departamentosDAO.findById(id);

        if(departamento.isPresent()){
            return  ResponseEntity.ok().body(departamento.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntidadDepartamentos guardarDepartamento(@Validated @RequestBody EntidadDepartamentos departamento) {
        return departamentosDAO.save(departamento);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDepartamento(@PathVariable(value="id")int id){
        Optional<EntidadDepartamentos> departamento = departamentosDAO.findById(id);
        if(departamento.isPresent()){
            departamentosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDepartamento(@RequestBody EntidadDepartamentos nuevoDepartamento,@PathVariable(value="id")int id){
        Optional<EntidadDepartamentos> departamento = departamentosDAO.findById(id);
        if(departamento.isPresent()){
            departamento.get().setDepno(nuevoDepartamento.getDepno());
            departamento.get().setNombre(nuevoDepartamento.getNombre());
            departamento.get().setUbicacion(nuevoDepartamento.getUbicacion());
            departamentosDAO.save(departamento.get());
            return ResponseEntity.ok().body("Update");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
