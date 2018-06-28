package com.webdev.siteparser.servise.cli;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.ProjectService;
import com.webdev.siteparser.servise.parse.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service("searchProject")
public class SearchProjectCliHandler implements CLICommandHandler {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SearchService searchService;

    private static SearchService.SearchSpecification specification;

    @Override
    public void handleCommand(String commandLine, HtmlAnaliseCLI cli) {
        //commandLine = search PROJECT_ID FIELD_SEARCHING(url|title|description|content) [KEYWORDS...]
        String[] commandParts = commandLine.split(" ");

        String searchItem = commandParts[2];
        specification = new SearchService.SearchSpecification();
        specification.reset();
        specification.setParam(searchItem);

        long projectId = Long.parseLong(commandParts[1]);
        Project project = projectService.getById(projectId);

        int parts = commandParts.length - 3;
        String keywords[] = new String[parts];
        for (int i = 0; i < parts; i++) {
            keywords[i] = commandParts[i+3];
        }

        Set<Page> result = searchService.search(project, specification, keywords);
        System.out.printf("Found %d pages:\n", result.size());

        result.forEach(r -> System.out.println(r.getUrl()));
    }
}
