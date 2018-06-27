package com.webdev.siteparser.servise.cli;

import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListProjectCliHandler implements CLICommandHandler {

    @Autowired
    private ProjectService projectService;

    @Override
    public void handleCommand(String command, HtmlAnaliseCLI cli) {
        System.out.println("List of projects:");
        for (Project p:
             projectService.getAll()) {
            String domain = p.getDomain();
            long id = p.getId();
            long countPages = p.getPages().size();
            System.out.printf("Project %s, count of pages: %d, ID: %d\n",
                                domain, countPages, id);
        }
    }
}
