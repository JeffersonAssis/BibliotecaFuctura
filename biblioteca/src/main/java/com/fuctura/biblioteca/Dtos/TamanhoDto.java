package com.fuctura.biblioteca.Dtos;

public enum TamanhoDto {
  PEQUENO(0),
  MEDIO(1),
  GRANDE(2);
  
  private int cod;

  private TamanhoDto(int cod){
    this.cod = cod;
  }

  public int getCod(){
    return cod;
  }

  public static TamanhoDto valeuOf(int cod){
    for( TamanhoDto t : TamanhoDto.values()){
      if(cod == t.getCod()){
        return t;
      }
    }
    throw new IllegalArgumentException("Tamanho Inválido!");
  }
}
