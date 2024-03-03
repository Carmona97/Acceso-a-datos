package com.acdat.springboot.demo.dao;

import com.acdat.springboot.demo.modelo.EntidadEmpleados;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IEmpleadosDAO extends CrudRepository<EntidadEmpleados, Integer> {

    EntidadEmpleados findByPuesto(String puesto);
    EntidadEmpleados findByDepnoGreaterThanEqual(int depno);

    @Query("select e from EntidadEmpleados e where e.nombre like %:patron%")
    EntidadEmpleados findByName(@Param("patron") String patron);

}
