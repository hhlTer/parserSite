package com.webdev.siteparser.listener;

import com.webdev.siteparser.domain.Page;
import com.webdev.siteparser.repository.PageRepository;
import com.webdev.siteparser.servise.cli.HtmlAnaliseCLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AppReadyListener {

    @Value("${launchMode}")
    private String launchMode;

    @Autowired
    private HtmlAnaliseCLI cli;

    @Autowired
    private PageRepository pageRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady(){

        String url = "https://habr.com";
        List<Page> pages = pageRepository.findPageByUrl(url);

        System.out.println(url);
        pages.stream().forEach(System.out::println);

        if (null != launchMode && launchMode.equals("cli")){
            cli.run();
        }
    }
}
