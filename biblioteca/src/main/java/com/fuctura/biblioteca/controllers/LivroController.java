package com.fuctura.biblioteca.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.fuctura.biblioteca.dtos.LivroDto;
import com.fuctura.biblioteca.service.LivroService;
import com.fuctura.biblioteca.util.BindingResultValidador;

@RestController
@RequestMapping("/livros")
public class LivroController {
  
  @Autowired
  private LivroService livroService;


  @PostMapping("salva")
  public ResponseEntity<?> salvaCategoria(@Valid @RequestBody LivroDto livro, BindingResult bindingResult){
    BindingResultValidador bindingResultValidador = new BindingResultValidador(bindingResult);
    if(bindingResult.hasErrors()){
        return ResponseEntity.badRequest().body(bindingResultValidador.getErrors());
    }
      return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvaLivro(livro));
  }

  @GetMapping("/")
  public ResponseEntity<List<LivroDto>> obterTodosLivros(){
      return ResponseEntity.status(HttpStatus.OK).body(livroService.obterTodasLivros());
  }

  @GetMapping("/nome")
  public ResponseEntity<LivroDto> obterLivro(@RequestParam(value ="nome", required = false) String nome){
      return ResponseEntity.status(HttpStatus.OK).body(livroService.obterLivro(nome));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> excluirLivro(@RequestParam(value = "nome")String nome){
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(livroService.excluirLivro(nome));
  }

  @PutMapping("/atulizar")
  public ResponseEntity<?> updateCategoria(@RequestBody LivroDto livro, @RequestParam("nome")String nome){
      return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(livro, nome));
  }

  @GetMapping("nome/{nome}")
  public ResponseEntity<?> buscarCategroiaNome(@PathVariable("nome") String nome){
    return ResponseEntity.ok().body(livroService.listaLivrosCategoriaNome(nome));
  }
  
  @GetMapping("/buscar")
  public ResponseEntity<List<LivroDto>> obterTodosLivros(@RequestParam(value = "categoria", required = false)String categoria, @RequestParam(value ="nomeautor", required = false)String nomeAutor){
    List<LivroDto> livros = null;
    if(categoria != null && !categoria.isEmpty()){
      livros = livroService.listaLivrosPorCategoria(categoria);
    }if(nomeAutor != null && !nomeAutor.isEmpty()){
      livros = livroService.listaLivrosPorAutor(nomeAutor);     
    }
     return ResponseEntity.status(HttpStatus.OK).body(livros); 
  }

}
