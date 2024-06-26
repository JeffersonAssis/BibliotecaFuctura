package com.fuctura.biblioteca.controllers;

import java.util.List;
import java.util.Objects;

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

import com.fuctura.biblioteca.Dtos.LivroDto;
import com.fuctura.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
 
  private final LivroService livroService;

  @Autowired
  public LivroController(LivroService livroService){
    this.livroService = livroService;
  }

  @PostMapping("salva")
  public ResponseEntity<?> salvaCategoria(@RequestBody LivroDto livro){
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvaLivro(livro));

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro ao tentar Cadastra o livro");
    }
  }

  @GetMapping("/")
  public ResponseEntity<List<LivroDto>> obterTodosLivros(){
    List<LivroDto> livros = livroService.obterTodasLivros();
    if(Objects.nonNull(livros))
      return ResponseEntity.status(HttpStatus.OK).body(livros);
    
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

  @GetMapping("/nome")
  public ResponseEntity<LivroDto> obterLivro(@RequestParam(value ="nome", required = false) String nome){
    LivroDto livro = livroService.obterLivro(nome);
    if(Objects.nonNull(livro))
      return ResponseEntity.status(HttpStatus.OK).body(livro);
    
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> excluirLivro(@RequestParam(value = "nome")String nome){
    String del = livroService.excluirLivro(nome);
    if(del != null){
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(del);
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/atulizar")
  public ResponseEntity<?> updateCategoria(@RequestBody LivroDto livro, @RequestParam("nome")String nome){
    LivroDto li = livroService.updateLivro(livro, nome);
    if(Objects.nonNull(li)){
      return ResponseEntity.status(HttpStatus.OK).body(li);
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
  
  @GetMapping("/buscar")
  public ResponseEntity<List<LivroDto>> obterTodosLivros(@RequestParam(value = "categoria", required = false)String categoria, @RequestParam(value ="nomeautor", required = false)String nomeAutor){
    List<LivroDto> livros = null;
    if(categoria != null && !categoria.isEmpty()){
      livros = livroService.listaLivrosPorCategoria(categoria);
    }if(nomeAutor != null && !nomeAutor.isEmpty()){
      livros = livroService.listaLivrosPorAutor(nomeAutor);     
    }
    if(Objects.nonNull(livros))  
     return ResponseEntity.status(HttpStatus.OK).body(livros);
  
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
  }

}
