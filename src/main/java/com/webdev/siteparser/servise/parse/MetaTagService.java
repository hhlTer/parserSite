package com.webdev.siteparser.servise.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class MetaTagService {
    public String parceTitle(Document document){
        return document.title();
    }

    public String parceDescription(Document document){
        Elements meta = document.select("meta");
        for (Element e:
             meta) {
            if (e.hasAttr("name") && e.attr("name").equals("description")){
                return e.attr("content");
            }
        }
        return null;
    }
}
