package com.warren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 只能加载和它在同一个根目录下的包：com.warren
@SpringBootApplication
public class Springboot06SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot06SecurityApplication.class, args);
    }

}
