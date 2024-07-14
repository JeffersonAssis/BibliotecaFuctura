package com.fuctura.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.validation.Valid;

import com.fuctura.biblioteca.dtos.CategoriaDto;
import com.fuctura.biblioteca.service.CategoriaService;
import com.fuctura.biblioteca.util.BindingResultValidador;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @PostMapping("salva")
  public ResponseEntity<?> salvaCategoria(@Valid @RequestBody CategoriaDto categoria, BindingResult bindingResult){
    BindingResultValidador bindingResultValidador = new BindingResultValidador(bindingResult);
    if(bindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(bindingResultValidador.getErrors());
    }
      return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvaCategoria(categoria));
  }


  @GetMapping("/")
  public ResponseEntity<List<CategoriaDto>> obterTodasCategorias(){
      return ResponseEntity.status(HttpStatus.OK).body(categoriaService.obterTodasCategorias());
  }

  @GetMapping("/nome")
  public ResponseEntity<?> obterTodasCategorias(@RequestParam(value ="nome", required = false) String nome){
      return ResponseEntity.status(HttpStatus.OK).body(categoriaService.obterCategoriaLista(nome));
  }

  @GetMapping("/{nome}")
  public ResponseEntity<?> obterTodasCategoriasPorNome(@PathVariable("nome") String nome){
      return ResponseEntity.status(HttpStatus.OK).body(categoriaService.obterCategoria(nome));   
  }

  @GetMapping("/existe")
  public ResponseEntity<?> VericifaCategorias(@RequestParam(value ="nome", required = false) String nome){
      return ResponseEntity.status(HttpStatus.OK).body("Categoria já cadastrada "+ nome);
  }
      

  @DeleteMapping("/delete")
  public ResponseEntity<?> excluirCategoria(@RequestParam(value = "nome")String nome){
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoriaService.excluirCategoria(nome));
   
  }

  @PutMapping("/atulizar")
  public ResponseEntity<?> updateCategoria(@RequestBody CategoriaDto categoria, @RequestParam("nome")String nome){
       return ResponseEntity.status(HttpStatus.OK).body(categoriaService.updateCategoria(categoria, nome));
  }

}
