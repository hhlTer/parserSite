package com.webdev.siteparser.servise.convert;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LanguageConvertNameService {
    private Map<String, String> languageMap;

    public LanguageConvertNameService(){
        languageMap = new HashMap<>();
        languageMap.put("uk", "Українська");
        languageMap.put("en", "Англійська");
        languageMap.put("ru", "Русіш");
    }

    public String convertName(String shortLang){
        if (languageMap.containsKey(shortLang)){
            return languageMap.get(shortLang);
        } else {
            return shortLang;
        }
    }
}
