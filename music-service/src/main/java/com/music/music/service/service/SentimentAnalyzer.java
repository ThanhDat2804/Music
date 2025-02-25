package com.music.music.service.service;

import com.music.music.service.model.Sentiment;
import dev.langchain4j.service.UserMessage;

public class SentimentAnalyzer {
    @UserMessage("Analyze sentiment of {{it}}")
    Sentiment analyzeSentimentOf(String text);
}
