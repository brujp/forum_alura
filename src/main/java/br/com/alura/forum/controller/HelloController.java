package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody //não quero navegar para uma página ("JSP", Java Server Pages, ou Thymeleaf)
    public String hello(){
        return "Hello, World!";
    }
}
