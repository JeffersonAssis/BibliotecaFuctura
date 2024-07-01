package com.fuctura.biblioteca.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuctura.biblioteca.models.Categoria;
import com.fuctura.biblioteca.models.Livro;
import com.fuctura.biblioteca.repository.CategoriaRepository;
import com.fuctura.biblioteca.repository.LivroRepository;

@Service
public class DBService {
  
  @Autowired
  private CategoriaRepository categoriaRepository;
  @Autowired
  private LivroRepository livroRepository;

 
  public void instanceDB(){
        Categoria cat1 = new Categoria(null, "Informática", "Livro de informática");
        Categoria cat2 = new Categoria(null, "Ficção Científica", "Ficção Científica");
        Categoria cat3 = new Categoria(null, "Biografias", "Livros de Biografias");

        Livro l1 = new Livro(null, "Clean code", "Robertin Martin", "Lorem ipsum", 1, cat1);
        Livro l2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem ipsum", 2, cat1);
        Livro l3 = new Livro(null, "The time machine", "H. G. Wells", "Lorem ipsum", 1, cat2);
        Livro l4 = new Livro(null, "The war of the worlds", "H. G. Wells", "Lorem ipsum", 0, cat2);
        Livro l5 = new Livro(null, "I, robot", "Isaac Asimov", "Lorem ipsum", 2, cat2);

        this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));

        this.livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5));

  }

}
