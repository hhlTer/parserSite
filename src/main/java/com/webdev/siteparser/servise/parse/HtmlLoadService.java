package com.webdev.siteparser.servise.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HtmlLoadService {
    public String loadPage(String url){
        Document document = getDocument(url);
        return document.html();
    }

    public Document getDocument(String url){
        Document document = null;
        try {
            System.out.println("from HtmlLoadService, detDocument(url), url: " + url);
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

}
