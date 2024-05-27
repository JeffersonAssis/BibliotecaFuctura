package com.fuctura.biblioteca.models;

public enum Tamanho {
  PEQUENO(0),
  MEDIO(1),
  GRANDE(2);
  
  private int cod;

  private Tamanho(int cod){
    this.cod = cod;
  }

  public int getCod(){
    return cod;
  }

  public static Tamanho valeuOf(int cod){
    for( Tamanho t : Tamanho.values()){
      if(cod == t.getCod()){
        return t;
      }
    }
    throw new IllegalArgumentException("Tamanho Inválido!");
  }
}
