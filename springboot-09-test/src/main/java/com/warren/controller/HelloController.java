package com.warren.controller;

import com.warren.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    AsyncService asyncService;

    @RequestMapping("hello")
    public String hello() throws InterruptedException {

        asyncService.hello();

        return "Success 2020/7/17版本";
    }
}
