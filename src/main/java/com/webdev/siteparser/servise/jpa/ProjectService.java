package com.webdev.siteparser.servise.jpa;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PageService pageService;

    public Project save(Project project){
        projectRepository.save(project);

        if (project.getPages().size() == 0){
            Page indexPage = new Page();
            indexPage.setUrl(project.getDomain());
            indexPage.setProject(project);
            pageService.save(indexPage);
        }

        return project;
    }

    public Project getById(long id){
        return projectRepository.findById(id).get();
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public void delete(Project p){
        projectRepository.delete(p);
    }

    public void clear(){ projectRepository.deleteAll();}

    public List<Project> getProjectWithEnabledParsing(){
        return projectRepository.getProjectWithParsingEnabled();
    }



}
