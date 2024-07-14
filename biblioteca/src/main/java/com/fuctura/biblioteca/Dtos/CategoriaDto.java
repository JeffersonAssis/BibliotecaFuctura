package com.fuctura.biblioteca.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoriaDto {

    private Long id;

    @NotBlank(message = "O campo Nome não pode ser Vazio!")
    @NotNull(message = "O campo Nome não pode ser nulo!")
    @Size(min = 2, message = "O valor minimo é dois caracteres!")
    private String nome;

    @NotBlank(message = "O campo descricão não pode ser Vazio!")
    @NotNull(message = "O campo descrição não pode ser nulo!")
    private String descricao;

    public CategoriaDto() {
    }

    public CategoriaDto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   
}
