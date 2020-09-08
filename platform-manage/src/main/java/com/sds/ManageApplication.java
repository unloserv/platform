package com.sds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.sds.mapper")
@SpringBootApplication
public class ManageApplication {

  public static void main(String... args) {
    SpringApplication.run(ManageApplication.class, args);
  }

}
