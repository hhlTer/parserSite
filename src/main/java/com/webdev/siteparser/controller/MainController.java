package com.webdev.siteparser.controller;

import com.webdev.siteparser.servise.secority.SecurityGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    protected SecurityGenerate securityGenerate;

    public ModelAndView generateModel(String uri){
        return securityGenerate.modelGenerate(uri);
    }
}
