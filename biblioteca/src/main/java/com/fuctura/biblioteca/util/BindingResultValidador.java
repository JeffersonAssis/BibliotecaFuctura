package com.fuctura.biblioteca.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class BindingResultValidador {

  private final BindingResult bindingResult;

  public BindingResultValidador (BindingResult bindingResult){
    this.bindingResult=bindingResult;
  }

  public boolean hasErrors(){
    return bindingResult.hasErrors();
  }

  public List<String> getErrors(){
    return bindingResult.getAllErrors().stream()
                         .map(DefaultMessageSourceResolvable::getDefaultMessage)
                         .collect(Collectors.toList());
  }
  
}