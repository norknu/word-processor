package com.wordcounter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCountReducer {

    private String filePath;
    private Map<String, Integer> fileWordCount;

    WordCountReducer(Stream<ProcessedFileLine> lineItems, String filePath) {
        this.filePath = filePath;
        this.fileWordCount = reduceItems(lineItems);
    }

    private static Map<String, Integer> reduceItems(Stream<ProcessedFileLine> items) {
        Stream<Map.Entry<String, Integer>> entryStream = items
                .map(ProcessedFileLine::getWordCounts)
                .map(Map::entrySet)
                .flatMap(Set::stream);

        return entryStream.collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue)));

    }

    String getFilePath(){
        return this.filePath;
    }

    Map<String, Integer> getFileWordCount() {
        return this.fileWordCount;
    }

    @Override
    public String toString() {
        return "file: " + filePath + ", word_counts: " + fileWordCount;
    }
}
