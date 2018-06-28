package com.webdev.siteparser.servise.parse;


import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.jpa.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SearchService {

    @Autowired
    private PageService pageService;

    public static class SearchSpecification{
        public static final SearchSpecification ALL = new SearchSpecification();
        public boolean url = true;
        public boolean title = true;
        public boolean content = true;
        public boolean description = true;

        public void reset(){
            url = false;
            title = false;
            content = false;
            description = false;
        }

        public void setParam(String param){
            if (param.toLowerCase().equals("url")){
                url = true;
            }
            if (param.toLowerCase().equals("title")){
                title = true;
            }
            if (param.toLowerCase().equals("content")){
                content = true;
            }
            if (param.toLowerCase().equals("description")){
                description = true;
            }
        }
    }

    public Set<Page> search(Project project, String...keywords){
        return search(project, SearchSpecification.ALL, keywords);
    }
    public Set<Page> search(Project project, SearchSpecification searchSpecification, String ...keywords){
        Set<Page> result = new HashSet<>();

        if (searchSpecification.url) {
            result.addAll(pageService.findUrlByKeywords(project, keywords));
        }
        if (searchSpecification.title) {
            result.addAll(pageService.findTitleByKeywords(project, keywords));
        }
        if (searchSpecification.description) {
            result.addAll(pageService.findDescriptionByKeywords(project, keywords));
        }
        if (searchSpecification.content) {
            result.addAll(pageService.findContentByKeywords(project, keywords));
        }
        return result;
    }
}
