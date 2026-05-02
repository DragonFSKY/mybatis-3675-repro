package com.example.demo;

import com.example.demo.mapper.DictionaryMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner verifyMapper(DictionaryMapper dictionaryMapper) {
    return args -> System.out.println("dictionary count=" + dictionaryMapper.countDictionaries());
  }
}
