package com.webdev.siteparser.servise.parser;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.PageService;
import com.webdev.siteparser.servise.jpa.ProjectService;
import com.webdev.siteparser.servise.parse.HtmlLoadService;
import com.webdev.siteparser.servise.parse.MetaTagService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParserService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PageService pageService;

    @Autowired
    private HtmlLoadService htmlLoadService;

    @Autowired
    private MetaTagService metaTagService;

    @Scheduled(fixedDelay = 10000)
    public void parse(){
        System.out.println("test delay");
        List<Project> projectList = projectService.getProjectWithEnabledParsing();

        System.out.println(projectList.size());
        for (Project project:
             projectList) {
            if (pageService.hasProjectUnparsedPages(project)) {
                System.out.println("inside foreach");
                parseProject(project);
            }
        }
    }

    private void parseProject(Project project){
        List<Page> pageToParce = pageService.getProjectUnparsedPages(project);
        Page firstPage = pageToParce.get(0);
        parsePage(firstPage);
    }

    private void parsePage(Page page){
        String url = page.getUrl();

        Document document = htmlLoadService.getDocument(url);
        String content = document.body().text();
        String title = metaTagService.parceTitle(document);
        String description = metaTagService.parceDescription(document);

        page.setContent(content);
        page.setDescription(description);
        page.setTitle(title);
        System.out.println("title: " + title);

        pageService.save(page);

    }

}
