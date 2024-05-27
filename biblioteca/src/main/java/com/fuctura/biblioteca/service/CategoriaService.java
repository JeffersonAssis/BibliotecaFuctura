package com.fuctura.biblioteca.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  @Autowired
  public CategoriaService(CategoriaRepository categoriaRepository){
    this.categoriaRepository = categoriaRepository;
  }

  public Categoria salvaCategoria(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  public List<Categoria> obterTodasCategorias(){
    return categoriaRepository.findAll();
  }

  public Categoria obterCategoria(String nome){
    Optional<Categoria> optCat = categoriaRepository.findByNome(nome);
    if(optCat.isPresent()){
      Categoria cat = optCat.get();
      return cat;
    }
    return new Categoria();
      
  }

  public String excluirCategoria(String nome){
    Categoria cat = obterCategoria(nome);
    if(Objects.nonNull(cat)){
      categoriaRepository.delete(cat);
      return "Categoria Excluida com Sucesso";
    }
     return null;
  }

  public Categoria updateCategoria(Categoria categoria, String nome) {
    Categoria cat = obterCategoria(nome);
    if(Objects.nonNull(cat)){
      categoria.setId(cat.getId());
      categoriaRepository.save(categoria);
      return categoria;
    }
    return new Categoria();
  }
}
