package com.fuctura.biblioteca.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionEntity {
  
    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy")
    private LocalDateTime timetesmp;

    private String mensagem;

    private int status;

    private String path;
    
    public ExceptionEntity(LocalDateTime timetesmp, String mensagem, int status, String path) {
      this.timetesmp = timetesmp;
      this.mensagem = mensagem;
      this.status = status;
      this.path = path;
    }

    public LocalDateTime getTimetesmp() {
      return timetesmp;
    }

    public void setTimetesmpe(LocalDateTime timetesmp) {
      this.timetesmp = timetesmp;
    }

    public String getMensagem() {
      return mensagem;
    }

    public void setMensagem(String mensagem) {
      this.mensagem = mensagem;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }
    
}
