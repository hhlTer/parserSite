package com.webdev.siteparser.controller;

import com.webdev.siteparser.servise.secority.SecurityGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class IndexController extends MainController{

    @Autowired
    private SecurityGenerate securityGenerate;

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = generateModel("index");
        return modelAndView;
    }

//    public String index(){
//
//        return "index";
//    }





//    @GetMapping("/projects")
//    @ResponseBody
//    public List<Project> list(){
//        return projectService.getAll();
//    }
//
//    @GetMapping("/project/get/{projectId}")
//    @ResponseBody
//    public Object getProjectById(@PathVariable long projectId){
//        if (projectService.exist(projectId)){
//            return projectService.getById(projectId);
//        } else {
//            return "Project not found";
//        }
//    }
}
