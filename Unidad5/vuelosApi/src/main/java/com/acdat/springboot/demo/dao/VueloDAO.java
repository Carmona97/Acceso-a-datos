package com.acdat.springboot.demo.dao;

import com.acdat.springboot.demo.modelo.EntidadVuelos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VueloDAO extends JpaRepository<EntidadVuelos,Integer> {
    EntidadVuelos findByOrigen(String origenVuelo);
    EntidadVuelos findByVueloId(int id);

    @Query("select v from EntidadVuelos v where v.origen like %:patron%")
    EntidadVuelos findByOrigenLike(@Param("patron") String patron);

    EntidadVuelos findByDestino(String destinoVuelo);

    @Query("select v from EntidadVuelos v where v.destino like %:patron%")
    EntidadVuelos findByDestinoLike(@Param("patron") String patron);
}
