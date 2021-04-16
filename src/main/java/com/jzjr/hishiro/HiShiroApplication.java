package com.jzjr.hishiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jzjr.hishiro.dao")
public class HiShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiShiroApplication.class, args);
    }

}
