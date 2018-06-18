package com.webdev.siteparser.servise.jpa;

import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProgectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project){
        projectRepository.save(project);
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

}
