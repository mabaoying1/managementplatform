package com.bsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.bsoft.mapper")
@ImportResource("classpath:spring-rpc.xml")
public class SpringBootWebApplication
      //  extends SpringBootServletInitializer
{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

 /*  @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }*/
}
