package com.fuctura.biblioteca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuctura.biblioteca.config.ModelMapperConfig;
import com.fuctura.biblioteca.dtos.LivroDto;
import com.fuctura.biblioteca.exception.IllegalArgumentException;
import com.fuctura.biblioteca.exception.ObjectNotFoundException;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

  @Autowired
  private CategoriaService categoriaService;
  @Autowired
  private LivroRepository livroRepository;
  @Autowired
  private ModelMapperConfig modelMapper;


  public LivroDto salvaLivro(LivroDto livro) {
    if(categoriaService.isExistsNome(livro.getCategoria().getNome())){
      Livro l = modelMapper.modelMapper().map(livro, Livro.class);
      return modelMapper.modelMapper().map( livroRepository.save(l), LivroDto.class);
    }
    throw new ObjectNotFoundException("Categoria não Cadastrada!");
  }

  public List<LivroDto> obterTodasLivros() {
    return  livroRepository.findAll().stream().map( l -> modelMapper.modelMapper().map(l, LivroDto.class)).collect(Collectors.toList());
  }

  public LivroDto obterLivro(String nome) {
    Optional<Livro> optLivro = livroRepository.findByNomeContainingIgnoreCase(nome);
    if(optLivro.isPresent()){
      return modelMapper.modelMapper().map(optLivro.get(), LivroDto.class);
    }
    throw new ObjectNotFoundException("Livro não encontrado!");
  }

  public String excluirLivro(String nome) {
    LivroDto li = obterLivro(nome);
    livroRepository.delete(modelMapper.modelMapper().map(li,Livro.class));
  
    return "O livro foi excluido com Sucesso: "+nome;   
  }

  public LivroDto updateLivro(LivroDto livro, String nome) {
    LivroDto li = obterLivro(nome);
    if(!livroRepository.existsByNomeIgnoreCase(nome)){
        livro.setId(li.getId());
      return salvaLivro(livro);
      }
        throw new IllegalArgumentException("Não foi possivel cadastrado o livro, o mesmo já está cadastrado!");
  }

  public List<LivroDto> listaLivrosPorCategoria(String categoria) {
    List<Livro> l = livroRepository.findByCategoria(categoria);  
      return l.stream().map(i -> modelMapper.modelMapper().map(l, LivroDto.class)).collect(Collectors.toList());      
  }
  
  public List<LivroDto> listaLivrosPorAutor(String nomeAutor) {
   List<Livro> l = livroRepository.findByAutorContainingIgnoreCase(nomeAutor); 
    if(!l.isEmpty())    
      return l.stream().map(i -> modelMapper.modelMapper().map(l, LivroDto.class)).collect(Collectors.toList());      

     throw new ObjectNotFoundException("Autor não encontrado"); 
  }


 public List<LivroDto> listaLivrosCategoriaNome(String nome){
  if(categoriaService.isExistsNome(nome)){
   List<Livro>  list =  livroRepository.findByCategoriaNomeContainingIgnoreCase(nome);
  if(!list.isEmpty()) 
  return list.stream().map(c -> modelMapper.modelMapper().map(c, LivroDto.class)).collect(Collectors.toList());
  
  throw new ObjectNotFoundException("Não tem livro para categoria cadastrada!"); 
  
  }
  throw new ObjectNotFoundException("Categoria não Cadastrada!"); 
} 

}
