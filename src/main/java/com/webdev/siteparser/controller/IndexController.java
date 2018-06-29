package com.webdev.siteparser.controller;

import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/")
public class IndexController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ModelAndView index(@RequestParam(required = false, defaultValue = "42") String value){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("value", value);
        modelAndView.addObject("projects", projectService.getAll());
        return modelAndView;
    }

    @GetMapping("/projects")
    @ResponseBody
    public List<Project> list(){
        return projectService.getAll();
    }

    @GetMapping("/project/get/{projectId}")
    @ResponseBody
    public Object getProjectById(@PathVariable long projectId){
        if (projectService.exist(projectId)){
            return projectService.getById(projectId);
        } else {
            return "Project not found";
        }
    }
}
