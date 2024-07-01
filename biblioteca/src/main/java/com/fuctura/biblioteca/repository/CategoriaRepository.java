package com.fuctura.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fuctura.biblioteca.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
 
  Optional<Categoria> findByNome(String nome);
  
}
