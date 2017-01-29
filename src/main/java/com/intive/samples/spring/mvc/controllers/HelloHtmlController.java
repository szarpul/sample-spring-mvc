package com.intive.samples.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloHtmlController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hello() {
        return "index";
    }

    @RequestMapping("/{value}")
    @ResponseBody
    public String helloName(@PathVariable String value) {
        return "<h1>Hello " + value + "!</h1>";
    }
}
