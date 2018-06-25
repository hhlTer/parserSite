package com.webdev.siteparser.listener;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.repository.PageRepository;
import com.webdev.siteparser.servise.cli.HtmlAnaliseCLI;
import com.webdev.siteparser.servise.jpa.PageService;
import com.webdev.siteparser.servise.jpa.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AppReadyListener {

    @Value("${launchMode}")
    private String launchMode;

    @Autowired
    private HtmlAnaliseCLI cli;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageService pageService;
    @Autowired
    private ProjectService projectService;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady(){

//        String url = "https://habr.com";
//        Project project = new Project();
//        project.setDomain("habr.com");
//
//        projectService.save(project);

//        Page page1 = new Page();
//        page1.setProject(project);
//        page1.setUrl("https://habr.com/url2");
//
//        Page page2 = new Page();
//        page2.setProject(project);
//        page2.setUrl("https://habr.com/url2");
//
//        project.getPages().add(page1);
//        project.getPages().add(page2);
//
//
//        pageService.save(page1);
//        pageService.save(page2);


        pageService.clear();
        projectService.clear();

        Project project = new Project();
        project.setDomain("https://habr.com");
        project.setParsingEnabled(true);

        Page page = new Page();
        page.setUrl("https://habr.com");
        page.setProject(project);

        projectService.save(project);
        pageService.save(page);

        if (null != launchMode && launchMode.equals("cli")){
            cli.run();
        }
    }
}
