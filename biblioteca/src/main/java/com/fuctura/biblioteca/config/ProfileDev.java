package com.fuctura.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.fuctura.biblioteca.service.DBService;


@Configuration
@Profile(value = "dev")
public class ProfileDev {
  
  @Autowired
  private DBService dbService;

  @Value(value = "${spring.jpa.hibernate.ddl-auto}")
  public String  ddl;

  @Bean
  public boolean instanceDB(){
    if(ddl.equals("create-drop")){
       this.dbService.instanceDB();
      return true;
    }
    return false;
  }

  
}