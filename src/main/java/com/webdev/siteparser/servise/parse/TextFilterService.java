package com.webdev.siteparser.servise.parse;

import org.springframework.stereotype.Service;

@Service
public class TextFilterService {
    public String cleanText(String text){
        StringBuilder result = new StringBuilder();

        for (char c:
             text.toCharArray()) {
            if (isFilter(c)){
                result.append(c);
            }
        }
        return result.toString();
    }

    private boolean isFilter(char c){
        if (Character.isAlphabetic(c)
                || Character.isDigit(c)
                || Character.isLetterOrDigit(c)
                || Character.isSpaceChar(c)){
            return true;
        } else {
            return false;
        }
    }
}
