package com.blue.spring.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json; charsets=utf-8")
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "welcome my website!!";
    }

}
