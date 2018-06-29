package com.webdev.siteparser.servise.parser;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.PageService;
import com.webdev.siteparser.servise.jpa.ProjectService;
import com.webdev.siteparser.servise.parse.HtmlLoadService;
import com.webdev.siteparser.servise.parse.MetaTagService;
import com.webdev.siteparser.servise.parse.TextFilterService;
import com.webdev.siteparser.servise.parse.UrlService;
import com.webdev.siteparser.servise.parse.stats.ExtractLinksService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ExtractLinksService extractLinksService;

    @Autowired
    private TextFilterService textFilterService;

    @Autowired
    private UrlService urlService;

    @Scheduled(fixedDelay = 5000)
    public void parse(){
        List<Project> projectList = projectService.getProjectWithEnabledParsing();

        for (Project project:
             projectList) {
            if (pageService.hasProjectUnparsedPages(project)) { //if exist project with enabled parsing
                parseProject(project);
            }
        }
    }

    private void parseProject(Project project){
        List<Page> pageToParse = pageService.getProjectUnparsedPages(project); //set all page, where content == null
        if (pageToParse.size() > 0) {
            Page firstPage = pageToParse.get(0);
            parsePage(firstPage); //parse first page
        }
    }

    private void parsePage(Page page){
        String url = urlService.normalizeUrl(page.getUrl());

        Document document = htmlLoadService.getDocument(url); //get document from page, where content == null
        String content = document.body().text();
        String title = metaTagService.parceTitle(document);
        String description = metaTagService.parceDescription(document);

        content = textFilterService.cleanText(content);
        title = textFilterService.cleanText(title);
        description = textFilterService.cleanText(description);

        page.setContent(content);
        page.setDescription(description);
        page.setTitle(title);
        System.out.println("title: " + title);

        pageService.save(page);

        List<String> linksList = extractLinksService.linkList(page.getProject(), document);
        for (String s:
             linksList) {
            Page page1 = new Page();
            page1.setProject(page.getProject());
            page1.setUrl(s);
            pageService.save(page1);
        }
    }

}
