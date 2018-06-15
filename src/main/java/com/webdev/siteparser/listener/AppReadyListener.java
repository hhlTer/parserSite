package com.webdev.siteparser.listener;

import com.webdev.siteparser.servise.parse.HtmlLoadService;
import com.webdev.siteparser.servise.parse.MetaTagService;
import com.webdev.siteparser.servise.parse.stats.ContentLengthService;
import com.webdev.siteparser.servise.parse.stats.HeaderCountService;
import com.webdev.siteparser.servise.parse.stats.LanguageDetectorService;
import com.webdev.siteparser.servise.parse.stats.ParagraphCountService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class AppReadyListener {

    @Autowired
    private HtmlLoadService htmlLoadService;

    @Autowired
    private MetaTagService metaTagService;

    @Autowired
    private ContentLengthService contentLengthService;

    @Autowired
    private ParagraphCountService paragraphCountService;

    @Autowired
    private HeaderCountService headerCountService;

    @Autowired
    private LanguageDetectorService languageDetectorService;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady(){

//        String url = "https://habr.com";
        String url = "https://ukr.net";
//        String html = htmlLoadService.loadPage(url);
//        System.out.println("App ready");
        //2
//        String result = metaTagService.parceTitle(htmlLoadService.getDocument(url));
//        System.out.println("tag: " + result);
//        result = metaTagService.parceDescription(htmlLoadService.getDocument(url));
//        System.out.println("description: " + result);

        Document document = htmlLoadService.getDocument(url);
        String html = document.html();

        int contentLength = contentLengthService.getContentLengthWithoutSpaces(html);
        System.out.println("Content Length: " + contentLength);


        int paragraphSize = paragraphCountService.getCountOfParagraph(html);
        System.out.println("paragraph size = " + paragraphSize);

        int paraCount = headerCountService.countOfTags(html);
        System.out.println("header tags: " + paraCount);

        String lang = languageDetectorService.detectLanguage(html);
        System.out.println("language = " + lang);
    }
}
