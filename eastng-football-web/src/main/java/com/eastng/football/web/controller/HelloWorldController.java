package com.eastng.football.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @RequestMapping("helloWorld")
    @ResponseBody
    public String helloWorld() {
    	
        return "helloWorld";
    }
}