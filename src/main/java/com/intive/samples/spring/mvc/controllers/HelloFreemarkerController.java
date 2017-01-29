package com.intive.samples.spring.mvc.controllers;

import com.intive.samples.spring.mvc.samples.SampleData;
import com.intive.samples.spring.mvc.samples.Spring;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/hello")
public class HelloFreemarkerController {

    @RequestMapping("/{name}")
    public ModelAndView welcomePage(@PathVariable String name, ModelMap modelMap) {

        Spring spring = new Spring();
        spring.setModule("MVC");
        spring.setVersion("4.3.4.RELEASE");

//        modelMap.addAttribute("name", name); No need to do that. 'Name' is already in model due to path variable.
        modelMap.addAttribute("spring", spring);
        modelMap.addAttribute("springClasses", SampleData.getSampleSpringClasses());

        return new ModelAndView("hello", modelMap);
    }
}
