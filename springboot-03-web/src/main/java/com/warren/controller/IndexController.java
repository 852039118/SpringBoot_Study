package com.warren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class IndexController {

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","<h1>Hello SpringBoot</h1>");
        model.addAttribute("users", Arrays.asList("warren","springboot","thymeleaf"));
        return "test";
    }
}
