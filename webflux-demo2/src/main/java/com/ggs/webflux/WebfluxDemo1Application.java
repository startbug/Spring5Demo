package com.ggs.webflux;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ggs.webflux.mapper")
@SpringBootApplication
public class WebfluxDemo1Application {

  public static void main(String[] args) {
    SpringApplication.run(WebfluxDemo1Application.class, args);
  }
}
