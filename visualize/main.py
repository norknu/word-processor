import json
import matplotlib.pyplot as plt
import numpy as np
import logging
import nltk
from nltk.corpus import stopwords

nltk.download("stopwords")

logging.basicConfig(format="%(levelname)s: %(message)s", level=logging.INFO)


def read_json_file(file_path: str):
    """
    Loads a json file based on a provided file path
    """

    if not file_path.endswith(".json"):
        raise ValueError(f"Invalid file extension {file_path.split('.')[-1]}")

    with open(file_path) as file:
        return json.load(file)


def plot_histogram(data: dict, number_of_words: int = 10):
    logging.info("Unpacking words and count values")
    labels, values = zip(*data.items())

    logging.info("Sorting the data")
    indSort = np.argsort(values)[::-1]

    labels = np.array(labels)[indSort]
    values = np.array(values)[indSort]

    labels = labels[:number_of_words]
    values = values[:number_of_words]

    logging.info("Creating bar chart")
    plt.bar(labels, values, color="purple")

    logging.info("Generating plot")
    plt.show()


def plot_table(data: dict, number_of_words: int = 10):
    _, ax = plt.subplots()

    # Hide axes
    ax.xaxis.set_visible(False)
    ax.yaxis.set_visible(False)

    items = list(data.items())

    items = sorted(items, key=lambda x: x[1], reverse=True)

    items = items[:number_of_words]

    columns = ("Word", "Count")
    ax.table(cellText=items, colLabels=columns, loc="center")
    plt.show()


def filter_words(data: dict, minimum_length: int = 0):
    english_stop_words = stopwords.words("english")
    return {
        key: value
        for key, value in data.items()
        if key not in english_stop_words and len(key) > minimum_length
    }

print("Write filepath and name (include filetype): ")
file_path = str(input())
minimum_length = 8

logging.info(f"Reading and loading JSON file: {file_path}")

data = read_json_file(file_path)
word_counts = data.get("word_counts")

plot_table(word_counts, 10)

cleaned_word_counts = filter_words(word_counts, 0)

plot_histogram(cleaned_word_counts, 10)
