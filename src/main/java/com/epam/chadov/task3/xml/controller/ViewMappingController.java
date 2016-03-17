package com.epam.chadov.task3.xml.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewMappingController {

    @RequestMapping("/main")
    public ModelAndView mainPage() {
        return new ModelAndView("main");
    }

    @RequestMapping("/xml-validate")
    public ModelAndView xmlValidatePage() {
        return new ModelAndView("xml-validate");
    }

    @RequestMapping("/xml-parsing")
    public ModelAndView xmlParseSuccessPage(){
        return new ModelAndView("xml-parsing-success");
    }
}
