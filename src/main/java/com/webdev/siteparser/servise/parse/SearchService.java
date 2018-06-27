package com.webdev.siteparser.servise.parse;


import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.PageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchService {

    @Autowired
    private PageService pageService;

    public static class SearchSpecification{
        public static final SearchSpecification ALL = new SearchSpecification();
        public boolean url = true;
        public boolean title = true;
        public boolean content = true;
        public boolean descripion = true;
    }

    public Set<Page> search(Project project, String...keywords){
        return search(project, SearchSpecification.ALL, keywords);
    }
    public Set<Page> search(Project project, SearchSpecification searchSpecification, String ...keywords){
        Set<Page> result = new HashSet<>();

        if (searchSpecification.url) {
            pageService.findUrlByKeywords(project, keywords);
        }
        if (searchSpecification.title) {
            pageService.findTitleByKeywords(project, keywords);
        }
        if (searchSpecification.descripion) {
            pageService.findDescriptionByKeywords(project, keywords);
        }
        if (searchSpecification.content) {
            pageService.findContentByKeywords(project, keywords);
        }
        return result;
    }
}
