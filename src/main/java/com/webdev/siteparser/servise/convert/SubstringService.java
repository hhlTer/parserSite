package com.webdev.siteparser.servise.convert;

import org.springframework.stereotype.Service;

@Service
public class SubstringService {
    public String prepareToShow(String text){
        if (null == text){
            return "";
        }

        if (text.length() < 300){
            return text;
        }

        return text.substring(0, 300) + "....";
    }
}
