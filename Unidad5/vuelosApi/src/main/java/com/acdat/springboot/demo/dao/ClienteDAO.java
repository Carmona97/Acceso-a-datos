package com.acdat.springboot.demo.dao;

import com.acdat.springboot.demo.modelo.EntidadAgencias;
import com.acdat.springboot.demo.modelo.EntidadClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteDAO extends JpaRepository<EntidadClientes, Integer> {
    EntidadClientes findByNombreCliente(String nombreCliente);

    @Query("select d from EntidadClientes d where d.nombreCliente like %:patron%")
    EntidadClientes findByName(@Param("patron") String patron);
}
