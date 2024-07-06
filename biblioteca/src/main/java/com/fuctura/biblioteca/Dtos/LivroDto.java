package com.fuctura.biblioteca.dtos;


public class LivroDto {


  private Long id;

  private String nome;
  
  private String autor;
  
  private String texto;

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
