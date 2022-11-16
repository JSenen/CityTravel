package com.juansenen.citytravel.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LineConfig {

    //TODO ModelMapper
   @Bean
   public ModelMapper modelMapper(){
       return new ModelMapper();
   }
}
