package com.qxst.e.meatballstartup.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class APPController {
    @GetMapping("/index")
    public ModelAndView index(ModelMap map) {
        //逻辑处理
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("msg", "Hello Freemarker");
        return modelAndView;
    }
}
