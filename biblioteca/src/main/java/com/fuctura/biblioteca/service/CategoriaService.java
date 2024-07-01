package com.fuctura.biblioteca.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuctura.biblioteca.Dtos.CategoriaDto;
import com.fuctura.biblioteca.config.ModelMapperConfig;
import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {

  private CategoriaRepository categoriaRepository;

  private ModelMapperConfig modelMapper;

  @Autowired
  public CategoriaService(CategoriaRepository categoriaRepository, ModelMapperConfig modelMapper){
    this.categoriaRepository = categoriaRepository;
    this.modelMapper = modelMapper;
  }

  public CategoriaDto salvaCategoria(CategoriaDto categoria) {
    Categoria c = modelMapper.modelMapper().map(categoria, Categoria.class);
    return modelMapper.modelMapper().map(categoriaRepository.save(c), CategoriaDto.class);
  }

  public List<CategoriaDto> obterTodasCategorias(){
    return categoriaRepository.findAll().stream().map(c -> modelMapper.modelMapper().map(c, CategoriaDto.class)).collect(Collectors.toList());
  }

 
  public CategoriaDto obterCategoria(String nome){
    Optional<Categoria> optCat = categoriaRepository.findByNome(nome);
    if(optCat.isPresent()){
      Categoria cat = optCat.get();
      return modelMapper.modelMapper().map(cat,CategoriaDto.class);
    }
    return new CategoriaDto();
      
  }

  public String excluirCategoria(String nome){
    Categoria cat = modelMapper.modelMapper().map(obterCategoria(nome), Categoria.class);
    if(Objects.nonNull(cat)){
      categoriaRepository.delete(cat);
      return "Categoria Excluida com Sucesso";
    }
     return null;
  }

  public CategoriaDto updateCategoria(CategoriaDto categoria, String nome) {
    Categoria cat = modelMapper.modelMapper().map(obterCategoria(nome), Categoria.class);
    if(Objects.nonNull(cat)){
      categoria.setId(cat.getId());
      salvaCategoria(categoria);
      return categoria;
    }
    return new CategoriaDto();
  }
}
