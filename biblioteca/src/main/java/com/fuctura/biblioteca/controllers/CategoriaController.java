package com.fuctura.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;

import com.fuctura.biblioteca.dtos.CategoriaDto;
import com.fuctura.biblioteca.service.CategoriaService;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {

  private CategoriaService categoriaService;


  @Autowired
  public CategoriaController( CategoriaService categoriaService){
    this.categoriaService = categoriaService;

  }

  @PostMapping("salva")
  public ResponseEntity<?> salvaCategoria(@RequestBody CategoriaDto categoria){
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvaCategoria(categoria));

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao tentar Cadastra categoria");
    }
  }


  @GetMapping("/")
  public ResponseEntity<List<CategoriaDto>> obterTodasCategorias(){
    List<CategoriaDto> lCategorias = categoriaService.obterTodasCategorias();
    if(Objects.nonNull(lCategorias))
      return ResponseEntity.status(HttpStatus.OK).body(lCategorias);
    
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

  @GetMapping("/nome")
  public ResponseEntity<CategoriaDto> obterTodasCategorias(@RequestParam(value ="nome", required = false) String nome){
    CategoriaDto categoria = categoriaService.obterCategoria(nome);
    if(Objects.nonNull(categoria))
      return ResponseEntity.status(HttpStatus.OK).body(categoria);
    
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> excluirCategoria(@RequestParam(value = "nome")String nome){
    String del = categoriaService.excluirCategoria(nome);
    if(del != null){
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(del);
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/atulizar")
  public ResponseEntity<?> updateCategoria(@RequestBody CategoriaDto categoria, @RequestParam("nome")String nome){
    CategoriaDto cat = categoriaService.updateCategoria(categoria, nome);
    if(Objects.nonNull(cat)){
      return ResponseEntity.status(HttpStatus.OK).body(cat);
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
