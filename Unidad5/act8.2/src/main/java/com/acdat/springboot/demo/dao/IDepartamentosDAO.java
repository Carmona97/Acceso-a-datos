package com.acdat.springboot.demo.dao;

import com.acdat.springboot.demo.modelo.EntidadDepartamentos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IDepartamentosDAO extends CrudRepository<EntidadDepartamentos,Integer> {
    EntidadDepartamentos findByUbicacion(String ubicacion);
    EntidadDepartamentos findByDepnoGreaterThanEqual(int depno);

    @Query("select d from EntidadDepartamentos d where d.nombre like %:patron%")
    EntidadDepartamentos findByName(@Param("patron") String patron);
}
