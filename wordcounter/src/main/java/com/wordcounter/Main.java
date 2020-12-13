package com.wordcounter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){

        Scanner sc= new Scanner(System.in);
        System.out.print("Enter full file path and name: ");
        String filePath = sc.nextLine();
        System.out.print("Enter output file name (without file type specification): ");
        String outputFileName = sc.nextLine();
        long startTime = System.currentTimeMillis();

        TextFileProcessor textFileProcessor = new TextFileProcessor();

        // Task A
        ArrayList<FileLine> items = textFileProcessor.readFromTextFile(filePath);


        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Running on machine with " + cores + " cores");

        // Task B
        Stream<ProcessedFileLine> processedItems = textFileProcessor.processFileLines(items);

        // Task C
        WordCountReducer wordCountFromFile = new WordCountReducer(processedItems, filePath);

        textFileProcessor.writeToJsonFile(wordCountFromFile, outputFileName);
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Time elapsed (milliseconds): " + duration);

    }
}
