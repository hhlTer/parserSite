package com.webdev.siteparser.servise.parse.stats;

import com.webdev.siteparser.domain.Project;
import com.webdev.siteparser.servise.parse.UrlService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExtractLinksService {
    @Autowired
    private UrlService urlService;

    public List<String> linkList (Project project, Document document){
        List<String> result = new ArrayList<>();
        Elements elements = document.select("a");
        for (Element element:
             elements) {
            String link = urlService.normalizeUrl(element.absUrl("href"));
            if (link.startsWith(project.getDomain())){
                result.add(link);
            }
        }
        return result;
    }
}
