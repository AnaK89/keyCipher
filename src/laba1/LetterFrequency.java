package laba1;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

class LetterFrequency {   // frequency of letters from .txt
  private final FileReader text;
  private Map<String, Double> letterIsFound = new LinkedHashMap<>();
  private double quantityOfLetters = 0.0;

  LetterFrequency(String nameFile) throws IOException {
    text = new FileReader(nameFile);
    addKeysToAlphabet();
    counting();
  }

  private void addKeysToAlphabet() {
    for (int i = 0; i < 32; i++) {
      letterIsFound.put(Character.toString((char) ('а' + i)), 0.0);
    }
  }

  private void counting() throws IOException {
    int c;

    while ((c = text.read()) != -1) {
      if (Character.isUpperCase(c))
        c = Character.toLowerCase(c);
      if (letterIsFound.containsKey(Character.toString((char) c))) {
        quantityOfLetters++;
        double l = letterIsFound.get(Character.toString((char) c));
        letterIsFound.put(Character.toString((char) c), l + 1);
      }
    }
    for (String str : letterIsFound.keySet()) {
      letterIsFound.put(str, (letterIsFound.get(str) / quantityOfLetters));
    }

    SortedHashMapForValue sort = new SortedHashMapForValue(letterIsFound);
    letterIsFound = sort.sorting();

  }

  Map<String, Double> getLetterIsFound() {
    return letterIsFound;
  }
}
