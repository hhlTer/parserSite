package com.webdev.siteparser.servise.parse;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Runner {

//    @PostConstruct
    public void run(){
        System.out.println("Launched");
    }
}
