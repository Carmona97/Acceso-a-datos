package com.acdat.springboot.demo.dao;

import com.acdat.springboot.demo.modelo.EntidadClientes;
import com.acdat.springboot.demo.modelo.EntidadDestinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DestinoDAO extends JpaRepository<EntidadDestinos, Integer> {
    EntidadDestinos findByNombreDestino(String nombreDestino);

    @Query("select d from EntidadDestinos d where d.nombreDestino like %:patron%")
    EntidadDestinos findByName(@Param("patron") String patron);
}
