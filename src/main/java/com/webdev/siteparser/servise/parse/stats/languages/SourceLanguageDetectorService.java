package com.webdev.siteparser.servise.parse.stats.languages;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceLanguageDetectorService implements LanguageDetectorService{

    public static final String API_KEY = "940b6cfa82658ae533780ba2c0a3d0f6";

    public SourceLanguageDetectorService(){
        DetectLanguage.apiKey = API_KEY;
    }

    public String detectLanguage(String sourceText) {
        List<Result> results = null;
        try {
            results = DetectLanguage.detect(sourceText);
            Result result = results.get(0);
            System.out.println("Language: " + result.language);
            System.out.println("Is reliable: " + result.isReliable);
            System.out.println("Confidence: " + result.confidence);
            return result.language;
        } catch (APIError apiError) {
            apiError.printStackTrace();
        }
        return null;
    }
}
