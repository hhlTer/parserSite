package com.webdev.siteparser.servise.parse.stats;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


@Service
public class ParagraphCountService {
    private static final int MIN_CHAR_COUNT = 100;
    public int getCountOfParagraph(String html){
        Elements elements = Jsoup.parse(html).body().select("p");
        int count = 0;

        for (Element e:
             elements) {
            int len = e.html().length();
//            if (len > MIN_CHAR_COUNT) {
//                count++;
//            }
            count += len;
            System.out.println(e.text());
        }
        return count;
    }
}
