package com.reallyfun.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.reallyfun.server.mapper")
public class ReallyFunServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReallyFunServerApplication.class, args);
    }

}
