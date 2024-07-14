package com.fuctura.biblioteca.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class LivroDto {


  private Long id;

  @NotBlank(message = "O campo Nome não pode ser Vazio!")
  @NotNull(message = "O campo Nome não pode ser nulo!")
  @Size(min = 2, message = "O valor minimo é dois caracteres!")
  private String nome;
  @NotBlank(message = "O campo Autor não pode ser Vazio!")
  @NotNull(message = "O campo Autor não pode ser nulo!")
  @Size(min = 3, message = "O valor minimo é três caracteres!")
  private String autor;
  @NotBlank(message = "O campo texto não pode ser vazio!")
  @NotNull(message = "O campo texto não pode ser nulo!")
  private String texto;

  @NotNull(message = "Informe o tamanho do livro!")
  private int tamanho;
  

  private CategoriaDto categoria;

  public LivroDto(Long id, String nome, String autor, String texto, int tamanho, CategoriaDto categoria) {
    this.id = id;
    this.nome = nome;
    this.autor = autor;
    this.texto = texto;
    this.tamanho = tamanho;
    this.categoria = categoria;
  }

  public LivroDto(){

  }
 
  
  public String getAutor() {
    return autor;
  }
  public Long getId() {
    return id;
  }
  public CategoriaDto getCategoria() {
    return categoria;
  }
  public String getNome() {
    return nome;
  }
  public String getTexto() {
    return texto;
  }
  public void setAutor(String autor) {
    this.autor = autor;
  }
  public void setCategoria(CategoriaDto categoria) {
    this.categoria = categoria;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setTexto(String texto) {
    this.texto = texto;
  }

  public TamanhoDto getTamanho(){
    return TamanhoDto.valeuOf(tamanho);
  }

  public void setTamanho(TamanhoDto tamanho){
    if(tamanho != null){
      this.tamanho= tamanho.getCod();
    }
  }

 
}
