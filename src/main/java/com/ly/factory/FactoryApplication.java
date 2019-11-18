package com.ly.factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Tarry
 * @create 2019/10/18 15:42
 */
@SpringBootApplication
@MapperScan("com.ly.factory.mapper")
public class FactoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(FactoryApplication.class);
    }
}
