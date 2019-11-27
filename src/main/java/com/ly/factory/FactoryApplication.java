package com.ly.factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
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
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
