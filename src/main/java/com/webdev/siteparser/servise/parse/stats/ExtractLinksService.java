package com.webdev.siteparser.servise.parse.stats;

import com.webdev.siteparser.domain.Project;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExtractLinksService {
    public List<String> linkList (Project project, Document document){
        List<String> result = new ArrayList<>();
        Elements elements = document.select("a");
        for (Element element:
             elements) {
            String link = element.absUrl("href");
            if (link.startsWith(project.getDomain())){
                result.add(link);
            }
        }
        return result;
    }
}
