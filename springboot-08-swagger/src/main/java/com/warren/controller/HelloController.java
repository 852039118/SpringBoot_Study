package com.warren.controller;

import com.warren.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "Hello-yo Swagger !!!";
    }

    // 只要接口返回值中存在实体类，它就会被扫描，在Swagger中显示
    @PostMapping(value = "/user")
    public User user(){
        return new User();
    }
}
