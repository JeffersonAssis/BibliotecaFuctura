package com.fuctura.biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuctura.biblioteca.config.ModelMapperConfig;
import com.fuctura.biblioteca.dtos.LivroDto;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
 
  private LivroRepository livroRepository;

  private ModelMapperConfig modelMapper;

  @Autowired
  public LivroService( LivroRepository livroRepository, ModelMapperConfig modelMapper){
    this.livroRepository = livroRepository;
    this.modelMapper = modelMapper;
  }

  public LivroDto salvaLivro(LivroDto livro) {
   Livro l = modelMapper.modelMapper().map(livro, Livro.class);
   return modelMapper.modelMapper().map( livroRepository.save(l), LivroDto.class);
    
  }

  public List<LivroDto> obterTodasLivros() {
    return  livroRepository.findAll().stream().map( l -> modelMapper.modelMapper().map(l, LivroDto.class)).collect(Collectors.toList());
  }

  public LivroDto obterLivro(String nome) {
    Optional<Livro> optLivro = livroRepository.findByNome(nome);
    if(Objects.nonNull(optLivro)){
      return modelMapper.modelMapper().map(optLivro.get(), LivroDto.class);
    }
    return new LivroDto();
  }

  public String excluirLivro(String nome) {
    LivroDto li = obterLivro(nome);
    if(Objects.nonNull(li)){
      livroRepository.delete(modelMapper.modelMapper().map(li,Livro.class));
      return "O livro foi excluido com Sucesso: "+nome;
    }
    return null;
  }

  public LivroDto updateLivro(LivroDto livro, String nome) {
    LivroDto li = obterLivro(nome);
    if(Objects.nonNull(li)){
      livro.setId(li.getId());
      return salvaLivro(livro);
    }
    return null;
  }

  public List<LivroDto> listaLivrosPorCategoria(String categoria) {
    Optional<List<Livro>> optListLivro = livroRepository.findByCategoria(categoria);
    if(optListLivro.isPresent()){

      List<Livro> l = optListLivro.get();
      return l.stream().map(i -> modelMapper.modelMapper().map(l, LivroDto.class)).collect(Collectors.toList());      
    }
    return new ArrayList<>();
  }
  
  public List<LivroDto> listaLivrosPorAutor(String nomeAutor) {
    Optional<List<Livro>> optListLivro = livroRepository.findByAutor(nomeAutor);
    if(optListLivro.isPresent()){
      List<Livro> l = optListLivro.get();
      return l.stream().map(i -> modelMapper.modelMapper().map(l, LivroDto.class)).collect(Collectors.toList());      
    }
    return new ArrayList<>();
  }

}
