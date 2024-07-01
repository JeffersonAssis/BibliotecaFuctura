package com.fuctura.biblioteca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fuctura.biblioteca.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
  
  Optional<Livro> findByNome(String nome);

  Optional<List<Livro>> findByAutor(String nome);

  @Query("SELECT l FROM Livro l JOIN l.categoria c WHERE c.nome = :categoria")
  Optional<List<Livro>> findByCategoria(@Param("categoria")String categoria);
  
} 
