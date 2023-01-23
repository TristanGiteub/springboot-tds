package edu.spring.td0.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
 
    @GetMapping("/hello")
    public @ResponseBody String index() {
        return "Hello world!";
    }
    
    @GetMapping("/hello/{msg}")
    public @ResponseBody String getPerson(@PathVariable String msg, HttpServletRequest request) {
		return msg;
    }
}
