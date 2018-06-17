package com.webdev.siteparser.servise.parse.stats.languages;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class HandlerLanguageDetectorService {
    private static Map<String, String> mapLanguageSpecificChars;

    public HandlerLanguageDetectorService(){
        mapLanguageSpecificChars = new HashMap<>();
        mapLanguageSpecificChars.put("ua", "іїґє");
        mapLanguageSpecificChars.put("ru", "ёъыь");
        mapLanguageSpecificChars.put("en", "ijq");
    }

    public String detectLanguage(String html){
        String text = Jsoup.parse(html).body().text().toLowerCase();
        Map<String, Integer> mapCharCount = new HashMap<>();
        for (String lang:
             mapLanguageSpecificChars.keySet()) {
            char[] languageChars = mapLanguageSpecificChars.get(lang).toCharArray();
            for (char c:
                 languageChars) {
                int countOld = mapCharCount.getOrDefault(lang, 0);
                countOld += charCount(c, text);
                mapCharCount.put(lang, countOld);
            }
        }
        String lang = "ua";
        for (String s:
             mapCharCount.keySet()) {
            if (mapCharCount.get(s) > mapCharCount.get(lang)){
                lang = s;
            }
        }
        return lang;
    }

    private int charCount(char c, String line){
        int count = 0;
        for (char temp:
             line.toCharArray()) {
            if (c == temp){
                count++;
            }
        }
        return count;
    }
}
