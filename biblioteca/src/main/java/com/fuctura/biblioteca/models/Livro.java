package com.fuctura.biblioteca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_livro")
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  
  private String autor;
  
  private String texto;

  private int tamanho;
  
  @ManyToOne
  private Categoria categoria;

  public Tamanho getTamanho(){
    return Tamanho.valeuOf(tamanho);
  }

  public void setTamanho(Tamanho tamanho){
    if(tamanho != null){
      this.tamanho= tamanho.getCod();
    }
  }

 
}
