package com.webdev.siteparser.servise.parse;

import org.springframework.stereotype.Service;

@Service
public class UrlService {
    public String normalizeUrl(String url){
        String[] urlParts = url.split("\\?");
        url = urlParts[0];

        urlParts = url.split("#");
        url = urlParts[0];

        if (url.endsWith("/")){
            url = url.substring(0, url.length() - 1);
        }


        return url;
    }
}
