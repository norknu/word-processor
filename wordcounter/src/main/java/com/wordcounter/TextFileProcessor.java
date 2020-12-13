package com.wordcounter;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.simple.JSONObject;

class TextFileProcessor {

    ArrayList<FileLine> readFromTextFile(String filePath) {

        ArrayList<FileLine> lineItems = new ArrayList<FileLine>();

        try {
            //Read from file given a file path
            BufferedReader in = new BufferedReader(
                    new FileReader(filePath));

            String inputLine;
            int lineNumber = 1;

            while ((inputLine = in.readLine()) != null){
                FileLine newLineObject = new FileLine(filePath, lineNumber, inputLine);
                lineItems.add(newLineObject);
                ++lineNumber;
            }
            in.close();

        } catch (IOException ex) {
            System.out.print("Not able to read from file");
        }
        return lineItems;

    }

    Stream<ProcessedFileLine> processFileLines(ArrayList<FileLine> fileLines) {
        return fileLines.stream().parallel().map(ProcessedFileLine::new);
    }

    void writeToJsonFile(WordCountReducer wordCountReducer, String givenFileName) {

        String fileName = givenFileName + ".json";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("word_counts", wordCountReducer.getFileWordCount());
        jsonObject.put("file", wordCountReducer.getFilePath());

        try {
            FileWriter file = new FileWriter(fileName);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            System.out.println("Not able to create file");
            e.printStackTrace();
        }
        System.out.println("JSON file created with filename: " + fileName);
    }
}
