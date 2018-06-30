package com.webdev.siteparser.controller;

import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/changeParsingState")
    public String changeParsingSate(
            @RequestParam(name = "projectId", required = true) long projectId,
            @RequestParam(name = "parsingEnabled", required = true) boolean parsingEnabled
    ){
        if (projectService.exist(projectId)){
            Project project = projectService.getById(projectId);
            project.setParsingEnabled(parsingEnabled);
            projectService.save(project);
        }
        return "redirect:/";
    }

    @GetMapping("/project/delete")
    public String deleteProject(
            @RequestParam(name = "projectId", required = true) long projectId
    ){
        if (projectService.exist(projectId)) {
            projectService.delete(projectService.getById(projectId));
        }
        return "redirect:/";
    }

    @GetMapping("/project/add")
    public ModelAndView createProject(){
        return new ModelAndView("/project/create");
    }

    @PostMapping("/project/add")
    public String handleProjectCreate(@ModelAttribute Project project){
        projectService.save(project);
        return "redirect:/";
    }

}
