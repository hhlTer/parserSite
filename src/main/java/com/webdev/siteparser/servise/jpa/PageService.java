package com.webdev.siteparser.servise.jpa;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private ProjectService projectService;
    //basic
    public void delete(Page p){
        pageRepository.delete(p);
    }
    public Page save(Page page){
        long projectId = page.getProject().getId();
        String url = page.getUrl();

        Page page1 = pageRepository.getPagesByProjectId(projectId, url);

        if (isSaves(page, page1)){
            pageRepository.save(page);
        }
//        else {
//            System.out.println("page already exist");
//        }


//        if ((page1 == null) || (page1.getContent() == null)){
//            if (page.getContent() == null && page.getTitle() == null && page.getDescription() == null);
//            pageRepository.save(page);
//        } else {
//            System.out.println("page already exist");
//        }
        return page;
    }

    public List<Page> getPageOffset(long projectId, int pageNumber, int count){
        int offset = pageNumber * count;
        if (projectService.exist(projectId)) {
            return pageRepository.getPagesByOffset(projectId, offset, count);
        }
        return null;
    }

    public Page getById(long id){
        return pageRepository.findById(id).get();
    }

    public List<Page> getAll(){
        return pageRepository.findAll();
    }

    public List<Page> getAll(long projectId){
        return  pageRepository.getPagesByProjectId(projectId);
    }

    public void clear(){pageRepository.deleteAll();}
    //additional
    public Page getPageByUrlByProjectId(String url, long prId){
        return pageRepository.getPagesByProjectId(prId, url);
    }

    /**
     * @param url
     * @return all pages by mask-url
     */
    public List<Page> getPageByUrl(String url){
        return pageRepository.findPageByUrl(url);
    }

    /**
     * @param project
     * @return check parsing enabled, return true, if true =)
     */

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

    /**
     * find url, content, title, description keywords
     * @param project - used for detect project id
     * @param keywords - find word(s)
     * @return Set of pages, where did find :keywords
     */
    public Set<Page> findUrlByKeywords(Project project, String[] keywords) {
        Set<Page> result = new HashSet<>();
        for (String word:
             keywords) {
            Set<Page> pages = pageRepository.findUrlByKeywords(project.getId(), word);
            if (pages != null){
                result.addAll(pages);
            }
        }
        return result;
    }

    public Set<Page> findContentByKeywords(Project project, String[] keywords) {
        Set<Page> result = new HashSet<>();
        for (String word:
             keywords) {
            Set<Page> pages = pageRepository.findContentByKeywords(project.getId(), word);
            if (pages != null){
                result.addAll(pages);
            }
        }
        return result;
    }

    public Set<Page> findTitleByKeywords(Project project, String[] keywords) {
        Set<Page> result = new HashSet<>();
        for (String word:
             keywords) {
            Set<Page> pages = pageRepository.findTitleByKeywords(project.getId(), word);
            if (pages != null){
                result.addAll(pages);
            }
        }
        return result;
    }

    public Set<Page> findDescriptionByKeywords(Project project, String[] keywords) {
        Set<Page> result = new HashSet<>();
        for (String word:
             keywords) {
            Set<Page> pages = pageRepository.findDescriptionByKeywords(project.getId(), word);
            if (pages != null){
                result.addAll(pages);
            }
        }
        return result;
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
