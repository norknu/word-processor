# Word Processor

Word processor that takes a text-file as input and creates a new json file with a word count of the words used in the file.

## Setup

Before you start

- Make sure you have Maven installed on your computer

  - https://maven.apache.org/install.html

- Download textfile
  - https://ocw.mit.edu/ans7870/6/6.006/s08/lecturenotes/files/t8.shakespeare.txt

## Run

### Word counter

Make sure you are inside the `wordcounter` folder.

- To build the .jar file run `mvn package`
- Then run `java -cp target/wordcounter-1.0-SNAPSHOT-jar-with-dependencies.jar com.wordcounter.Main`

First input is the file path of the file downloaded, including the file type. For example `t8.shakespear.txt`. Second input is the file path of the output file, do not include filetype. For example `word_count`. This file is created inside the wordcounter folder.

### Visualizer

Make sure you are inside the `visualizer` folder.

- Install dependencies buy running `pip install -r requirements.txt`
- Run code with `python main.py`
