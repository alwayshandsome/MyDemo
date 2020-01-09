package com.lcs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lcs")
public class DemoApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(DemoApplication.class, args);
    }

}
