package com.webdev.siteparser.servise.cli;

import com.webdev.siteparser.servise.convert.LanguageConvertNameService;
import com.webdev.siteparser.servise.parse.HtmlLoadService;
import com.webdev.siteparser.servise.parse.MetaTagService;
import com.webdev.siteparser.servise.parse.stats.ContentLengthService;
import com.webdev.siteparser.servise.parse.stats.HeaderCountService;
import com.webdev.siteparser.servise.parse.stats.ParagraphCountService;
import com.webdev.siteparser.servise.parse.stats.languages.HandlerLanguageDetectorService;
import com.webdev.siteparser.servise.parse.stats.languages.LanguageDetectorService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class HtmlAnaliseCLI {

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
    private HandlerLanguageDetectorService handlerLanguageDetectorService;

    @Qualifier("sourceLanguageDetectorService")
    @Autowired
    private LanguageDetectorService languageDetectorService;

    @Autowired
    private LanguageConvertNameService languageConvertNameService;

    Map<String, CLICommandHandler> cliCommandHandlerMap;

    public HtmlAnaliseCLI(ApplicationContext context){
        cliCommandHandlerMap = new HashMap<>();
        cliCommandHandlerMap.put("exit", context.getBean(ExitCLICommandHandler.class));
        cliCommandHandlerMap.put("help", context.getBean(HelpCLICommandHandler.class));
    }
    public void run(){
        System.out.println("Starting CLI......");
        System.out.println("enter URL amd press enter");

        Scanner scanner = new Scanner(System.in);
        String command;
        while (true){
            command = scanner.nextLine();
            if (command.startsWith("http://") || command.startsWith("https://")){
                printPageStarts(command);
            } else {
                handleCommand(command);
            }
        }
    }

    private void handleCommand(String command){
        if (cliCommandHandlerMap.containsKey(command)){
            cliCommandHandlerMap.get(command).handleCommand(command, this);
        } else {
            System.out.println("Unknow command: <" + command + ">");
        }
    }

    private void printPageStarts(String url){

//        String url = "https://habr.com";
//        String url = "https://ukr.net";
//        String html = htmlLoadService.loadPage(url);

        System.out.println("App ready");
        //2
        String result = metaTagService.parceTitle(htmlLoadService.getDocument(url));
        System.out.println("tag: " + result);
        result = metaTagService.parceDescription(htmlLoadService.getDocument(url));
        System.out.println("description: " + result);

        Document document = htmlLoadService.getDocument(url);
        String html = document.html();

        int contentLength = contentLengthService.getContentLengthWithoutSpaces(html);
        System.out.println("Content Length: " + contentLength);


        int paragraphSize = paragraphCountService.getCountOfParagraph(html);
        System.out.println("paragraph size = " + paragraphSize);

        int paraCount = headerCountService.countOfTags(html);
        System.out.println("header tags: " + paraCount);

        String lang = handlerLanguageDetectorService.detectLanguage(html);
        System.out.println("language handler = " + lang);

        String langSource = languageDetectorService.detectLanguage(document.text());
        System.out.println("language from service = " + languageConvertNameService.convertName(langSource));
    }
}
