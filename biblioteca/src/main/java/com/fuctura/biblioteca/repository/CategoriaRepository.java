package com.fuctura.biblioteca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fuctura.biblioteca.models.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

  Optional<Categoria> findByNomeIgnoreCase(String nome);
 
  List<Categoria> findByNomeContainingIgnoreCase(String nome);
  
  boolean existsByNomeIgnoreCase(String nome);
  
  
}
