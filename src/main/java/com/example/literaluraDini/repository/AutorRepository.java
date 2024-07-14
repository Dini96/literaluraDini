package com.example.literaluraDini.repository;

import com.example.literaluraDini.Model.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE (:year BETWEEN a.nacimiento AND a.muerte) OR (:year >= a.nacimiento AND a.muerte IS NULL)")
    List<Autor> findAutoresVivosEnElAnio(Integer year);
}
