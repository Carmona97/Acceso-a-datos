package com.acdat.springboot.demo.controladores;

import com.acdat.springboot.demo.DTO.EmpleadosDTO;
import com.acdat.springboot.demo.dao.IEmpleadosDAO;
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
@RequestMapping("api=rest/empleados")
public class ControladorEmpleados {

    @Autowired
    IEmpleadosDAO empleadosDAO;

    @GetMapping
    public List<EntidadEmpleados> buscarEmpleados(){return  (List<EntidadEmpleados>) empleadosDAO.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<EntidadEmpleados> buscarEmpleadosPorId(@PathVariable(value="id")int id){
        Optional <EntidadEmpleados> empleado = empleadosDAO.findById(id);

        if(empleado.isPresent()){
            return ResponseEntity.ok().body(empleado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public EntidadEmpleados guardarEmpleado(@Validated @RequestBody EntidadEmpleados empleado){
        return empleadosDAO.save(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value="id")int id){
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);
        if(empleado.isPresent()){
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EntidadEmpleados nuevoEmpleado,@PathVariable(value="id")int id){
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);
        if(empleado.isPresent()){
            empleado.get().setNombre(nuevoEmpleado.getNombre());
            empleado.get().setPuesto(nuevoEmpleado.getPuesto());
            empleado.get().setDepartamentosByDepno(nuevoEmpleado.getDepartamentosByDepno());
            empleadosDAO.save(empleado.get());
            return ResponseEntity.ok().body("Update");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("dto/{id}")
    public ResponseEntity<EmpleadosDTO> buscarEmpleadoDTOPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent()) {
            EntidadDepartamentos departamento = empleado.get().getDepartamentosByDepno();
            EmpleadosDTO empleadosDTO = new EmpleadosDTO();
            empleadosDTO.setEmpno(empleado.get().getEmpno());
            empleadosDTO.setNombre(empleado.get().getNombre());
            empleadosDTO.setPuesto(empleado.get().getPuesto());
            empleadosDTO.setDepno(departamento.getDepno());
            empleadosDTO.setDepartamentoNombre(departamento.getNombre());
            empleadosDTO.setDepartamentoUbicacion(departamento.getUbicacion());
            return ResponseEntity.ok().body(empleadosDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
