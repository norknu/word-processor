package com.wordcounter;

class FileLine {

    private String filePath;
    private int lineNumber;
    private String text;

    FileLine(String filePath, int lineNumber, String text) {
        this.filePath = filePath;
        this.lineNumber = lineNumber;
        this.text = text;
    }

    String getFilePath() {
        return this.filePath;
    }

    int getLineNumber() {
        return this.lineNumber;
    }

    String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return "file: " + filePath + ", line: " + lineNumber + ", text: " + text;
    }
}
