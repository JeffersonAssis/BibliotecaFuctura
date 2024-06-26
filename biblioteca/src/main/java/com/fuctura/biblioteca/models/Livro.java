package com.fuctura.biblioteca.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_livro")
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

 @Column(nullable = false)
  private String nome;
  
  private String autor;
  
  private String texto;

  private int tamanho;
  
  @ManyToOne
  private Categoria categoria;

  public Livro(Long id, String nome, String autor, String texto, int tamanho, Categoria categoria) {
    this.id = id;
    this.nome = nome;
    this.autor = autor;
    this.texto = texto;
    this.tamanho = tamanho;
    this.categoria = categoria;
  }

  public Livro(){

  }
 
  
  public String getAutor() {
    return autor;
  }
  public Long getId() {
    return id;
  }
  public Categoria getCategoria() {
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
  public void setCategoria(Categoria categoria) {
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

  public Tamanho getTamanho(){
    return Tamanho.valeuOf(tamanho);
  }

  public void setTamanho(Tamanho tamanho){
    if(tamanho != null){
      this.tamanho= tamanho.getCod();
    }
  }

 
}
