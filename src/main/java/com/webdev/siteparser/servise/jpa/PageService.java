package com.webdev.siteparser.servise.jpa;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    //basic
    public void delete(Page p){
        pageRepository.delete(p);
    }
    public Page save(Page page){
        long projectId = page.getProject().getId();
        String url = page.getUrl();

        Page page1 = pageRepository.getPagesByProjectId(projectId, url);
        System.out.println(page.getDescription());

        if (isSaves(page, page1)){
            pageRepository.save(page);
        } else {
            System.out.println("page already exist");
        }


//        if ((page1 == null) || (page1.getContent() == null)){
//            if (page.getContent() == null && page.getTitle() == null && page.getDescription() == null);
//            pageRepository.save(page);
//        } else {
//            System.out.println("page already exist");
//        }
        return page;
    }

    public Page getById(long id){
        return pageRepository.findById(id).get();
    }
    public List<Page> getAll(){
        return pageRepository.findAll();
    }

    //additional
    public Page getPageByUrlByProjectId(String url, long prId){
        return pageRepository.getPagesByProjectId(prId, url);
    }
    public List<Page> getPageByUrl(String url){
        return pageRepository.findPageByUrl(url);
    }

    public boolean hasProjectUnparsedPages(Project project){
        if (null == project){
            System.out.println("project == null");
            return false;
        }

        if (pageRepository.getUnparsedPagesByProjectId(project.getId()).size() == 0){
            return false;
        }

        return true;
    }
    public List<Page> getProjectUnparsedPages(Project project){
        return pageRepository.getUnparsedPagesByProjectId(project.getId());
    }

    private boolean isSaves(Page newPage, Page oldPage){
        if (oldPage == null) {
            return true;
        } else {
            if ((oldPage.getDescription() == null & newPage.getDescription() != null)||
                (oldPage.getContent() == null & newPage.getContent() != null)||
                (oldPage.getTitle() == null & newPage.getTitle() != null)){
                return true;
            }
        }
        return false;
    }
}
