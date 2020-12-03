package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

 

@RestController
public class info{

 

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "This is the main page on TOMCAT SERVER *_* ";
    }
    
    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "***welcome to Spring Boot***";
    
    }
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return " *** Hello Spring Boot *** ";
    }
    }
