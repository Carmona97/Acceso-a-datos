package com.acdat.springboot.demo.dao;

import com.acdat.springboot.demo.modelo.EntidadAgencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgenciaDAO extends JpaRepository<EntidadAgencias, Integer> {
    EntidadAgencias findByNombreAgencia(String nombreAgencia);

    @Query("select d from EntidadAgencias d where d.nombreAgencia like %:patron%")
    EntidadAgencias findByName(@Param("patron") String patron);
}
