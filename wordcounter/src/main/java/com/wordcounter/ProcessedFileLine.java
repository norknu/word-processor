package com.wordcounter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ProcessedFileLine {

    private String filePath;
    private int lineNumber;
    private String lineText;
    private String md5Sum;
    private Map<String, Integer> wordCounts;

    ProcessedFileLine(FileLine fileLine){
        this.filePath = fileLine.getFilePath();
        this.lineNumber = fileLine.getLineNumber();
        this.lineText = fileLine.getText();
        this.md5Sum = getMd5();
        this.wordCounts = makeWordCounts();
    }

    private Map<String, Integer> makeWordCounts() {

        String newline = this.lineText.toLowerCase()
                //Replaces all ' with one letter after with space.
                //Ex: "Harold's" --> "Harold "
                .replaceAll("'[A-Za-z]\\s", " ")
                //Replaces all non letters and numbers with space
                .replaceAll("[^A-Za-z0-9]", " ")
                //Replaces all double or more spaces with one space
                .replaceAll("\\s+", " ")
                .trim();
        String[] words = newline.toLowerCase().split(" ");
        Map<String, Integer> map = new HashMap<String, Integer> ();
        for (String word:words) {
            if (word == null || word.isEmpty()) break;

            if (!map.containsKey(word)) {
                map.put(word, 0);
            }
            map.put(word, map.get(word) + 1);
        }
        return map;

    };

    private String getMd5()
    {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(this.lineText.getBytes());

            BigInteger num = new BigInteger(1, messageDigest);

            StringBuilder hashText = new StringBuilder(num.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    Map<String, Integer> getWordCounts(){
        return this.wordCounts;
    }

    @Override
    public String toString() {
        return "file: " + filePath + ", line: " + lineNumber + ", text: " + lineText + ", md5sum: " + md5Sum + ", word_count: " + wordCounts;
    }
}
