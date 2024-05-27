package com.fuctura.biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

  private final LivroRepository livroRepository;

  @Autowired
  public LivroService( LivroRepository livroRepository){
    this.livroRepository = livroRepository;
  }

  public Object salvaLivro(Livro livro) {
    return livroRepository.save(livro);
  }

  public List<Livro> obterTodasLivros() {
    return livroRepository.findAll();
  }

  public Livro obterLivro(String nome) {
    Optional<Livro> optLivro = livroRepository.findByNome(nome);
    if(Objects.nonNull(optLivro)){
      return optLivro.get();
    }
    return new Livro();
  }

  public String excluirLivro(String nome) {
    Livro li = obterLivro(nome);
    if(Objects.nonNull(li)){
      livroRepository.delete(li);
      return "O livro foi excluido com Sucesso: "+nome;
    }
    return null;
  }

  public Livro updateLivro(Livro livro, String nome) {
    Livro li = obterLivro(nome);
    if(Objects.nonNull(li)){
      livro.setId(li.getId());
      livroRepository.save(livro);
      return livro;
    }
    return null;
  }

  public List<Livro> listaLivrosPorCategoria(String categoria) {
    Optional<List<Livro>> optListLivro = livroRepository.findByCategoria(categoria);
    if(optListLivro.isPresent()){
      return optListLivro.get();
    }
    return new ArrayList<>();
  }
  
  public List<Livro> listaLivrosPorAutor(String nomeAutor) {
    Optional<List<Livro>> optListLivro = livroRepository.findByAutor(nomeAutor);
    if(optListLivro.isPresent()){
      return optListLivro.get();
    }
    return new ArrayList<>();
  }

}
