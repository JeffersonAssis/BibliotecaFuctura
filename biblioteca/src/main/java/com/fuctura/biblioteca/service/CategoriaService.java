package com.fuctura.biblioteca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fuctura.biblioteca.config.ModelMapperConfig;
import com.fuctura.biblioteca.dtos.CategoriaDto;
import com.fuctura.biblioteca.exception.IllegalArgumentException;
import com.fuctura.biblioteca.exception.ObjectNotFoundException;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {
 
  @Autowired
  private CategoriaRepository categoriaRepository;
  @Autowired
  private ModelMapperConfig modelMapper;

  public CategoriaDto salvaCategoria(CategoriaDto categoria) {
      if(!isExistsNome(categoria.getNome())){
        Categoria c = modelMapper.modelMapper().map(categoria, Categoria.class);
        return modelMapper.modelMapper().map(categoriaRepository.save(c), CategoriaDto.class);
        }

    throw new IllegalArgumentException("Categoria já cadastrada!");
  }

  public List<CategoriaDto> obterTodasCategorias(){
    return categoriaRepository.findAll().stream().map(c -> modelMapper.modelMapper().map(c, CategoriaDto.class)).collect(Collectors.toList());
  }

 
  public List<CategoriaDto> obterCategoriaLista(String nome){
    List<Categoria> optCat = categoriaRepository.findByNomeContainingIgnoreCase(nome);
    if(!optCat.isEmpty()){
     
      return optCat.stream().map(c -> modelMapper.modelMapper().map(c,CategoriaDto.class)).collect(Collectors.toList());
    }
    throw new ObjectNotFoundException("Categoria não Cadastrada!");
      
  }

  public CategoriaDto obterCategoria(String nome){
    Optional<Categoria> optCat = categoriaRepository.findByNomeIgnoreCase(nome);
    if(optCat.isPresent()){ 
      return  modelMapper.modelMapper().map(optCat.get(),CategoriaDto.class);
    }
    throw new ObjectNotFoundException("Categoria não Cadastrada!");
      
  }

  public String excluirCategoria(String nome){
    Categoria cat = modelMapper.modelMapper().map(obterCategoria(nome), Categoria.class);
      categoriaRepository.delete(cat);
      return "Categoria: "+nome+" excluida com Sucesso!";
  
  }

  public CategoriaDto updateCategoria(CategoriaDto categoria, String nome) {
    Categoria cat = modelMapper.modelMapper().map(obterCategoria(nome), Categoria.class);
    if(!isExistsNome(categoria.getNome())){
      categoria.setId(cat.getId());
      salvaCategoria(categoria);
    }
    return categoria;
  }

  public boolean isExistsNome(String nome){
    return categoriaRepository.existsByNomeIgnoreCase(nome);
  }
}
