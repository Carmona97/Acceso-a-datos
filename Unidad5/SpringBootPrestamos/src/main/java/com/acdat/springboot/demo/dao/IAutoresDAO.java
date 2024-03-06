package com.acdat.springboot.demo.dao;

import com.acdat.springboot.demo.modelo.EntidadAutores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAutoresDAO extends JpaRepository<EntidadAutores,Integer> {
    EntidadAutores findByNombreAutor(String nombre_autor);
    EntidadAutores findByIdAutor(int id_autor);

    @Query("select a from EntidadAutores a where a.nombreAutor like %:patron%")
    EntidadAutores findByNombreAutorLike(@Param("patron") String patron);
}
