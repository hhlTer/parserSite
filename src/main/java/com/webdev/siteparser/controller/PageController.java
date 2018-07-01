package com.webdev.siteparser.controller;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.convert.SubstringService;
import com.webdev.siteparser.servise.jpa.PageService;
import com.webdev.siteparser.servise.jpa.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    private final static int COUNT_OF_VISIBLE_NOTE = 50;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PageService pageService;

    @Autowired
    private SubstringService substringService;

    @GetMapping("/project/pages")
    public ModelAndView dashboard(
            @RequestParam long projectId,
            @RequestParam(required = false, defaultValue = "0") String pageNumber
    ){
        ModelAndView modelAndView = new ModelAndView("/project/pages/dashboard");
        if (projectService.exist(projectId)){
            Project project = projectService.getById(projectId);
            modelAndView.addObject("project", project);

            int pageNumberInt = Integer.parseInt(pageNumber);

            List<Page> pages = pageService.getPageOffset(projectId, pageNumberInt, COUNT_OF_VISIBLE_NOTE);
            modelAndView.addObject("pages", pages);

            Map<Long, String> contentSubstring = new HashMap<>();
            Map<Long, Integer> indices = new HashMap<>();

            int c = 0;

            for (Page page:
                 pages) {
                contentSubstring.put(page.getId(), substringService.prepareToShow(page.getContent()));
                indices.put(page.getId(), pageNumberInt*50 + c++);
            }
              modelAndView.addObject("contentSubstring", contentSubstring);
              modelAndView.addObject("indices", indices);
              modelAndView.addObject("pageCount", pageNumberInt);

              int countOfPagesSite = getCountOfPagesSite(pageService.getCountOfPagesByProjectId(projectId), COUNT_OF_VISIBLE_NOTE);
              modelAndView.addObject("countOfPagesSite", countOfPagesSite);
        }
        return modelAndView;
    }

    private int getCountOfPagesSite(int countOfAllPagesByProjectId, int countVisibleNote){
        int a = countOfAllPagesByProjectId%countVisibleNote;
        int b = countOfAllPagesByProjectId/countVisibleNote;
        return a > 0 ? ++b : b;
    }
}
