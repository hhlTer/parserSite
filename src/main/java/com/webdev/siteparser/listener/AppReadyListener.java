package com.webdev.siteparser.listener;

import com.webdev.siteparser.servise.parse.HtmlLoadService;
import com.webdev.siteparser.servise.parse.MetaTagService;
import com.webdev.siteparser.servise.parse.stats.ContentLengthService;
import com.webdev.siteparser.servise.parse.stats.HeaderCountService;
import com.webdev.siteparser.servise.parse.stats.languages.HandlerLanguageDetectorService;
import com.webdev.siteparser.servise.parse.stats.ParagraphCountService;
import com.webdev.siteparser.servise.parse.stats.languages.LanguageDetectorService;
import com.webdev.siteparser.servise.parse.ui.HtmlAnaliseCLI;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class AppReadyListener {

    @Value("${launchMode}")
    private String launchMode;

    @Autowired
    private HtmlAnaliseCLI cli;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady(){

        if (null != launchMode && launchMode.equals("cli")){
            cli.run();
        }
    }
}
