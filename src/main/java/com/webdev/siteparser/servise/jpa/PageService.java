package com.webdev.siteparser.servise.jpa;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    public Page save(Page page){
        pageRepository.save(page);
        return page;
    }

    public Page getById(long id){
        return pageRepository.findById(id).get();
    }

    public List<Page> getAll(){
        return pageRepository.findAll();
    }

    public void delete(Page p){
        pageRepository.delete(p);
    }

    public List<Page> getPageByUrl(String url){
        return pageRepository.findPageByUrl(url);
    }

    public Page getPageByUrlByProjectId(String url, long prId){
        return pageRepository.getPagesByProjectId(prId, url);
    }
}
