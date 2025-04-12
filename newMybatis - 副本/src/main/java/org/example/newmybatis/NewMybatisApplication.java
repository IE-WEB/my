package org.example.newmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.newmybatis.mapper ")
public class NewMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewMybatisApplication.class, args);
    }

}
