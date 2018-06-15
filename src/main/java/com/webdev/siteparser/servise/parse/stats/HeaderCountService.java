package com.webdev.siteparser.servise.parse.stats;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeaderCountService {
    public static final String[] HEADER_TAGS = {
            "h1", "h2", "h3", "h4", "h5", "h6"
    };

    public int countOfTags(String html){
        Document document = Jsoup.parse(html);
        int count = 0;
        for (String header:
                HEADER_TAGS){
            count += document.select(header).size();
        }
        return count;
    }
}
