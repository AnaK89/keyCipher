package laba1;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DecryptedWithBigram implements Decrypt {
  private final String fileBook;
  private final String fileEncr;
  private final String fileDecr;
  private final static Integer QUANTITY_BIGRAMS = 10;
  private Map<String, String> decrypt = new HashMap<>();
  private Map<String, String> cipher;


  public DecryptedWithBigram(String fileBook, String fileEncr, String fileDecr, Map<String, String> cipher) {
    this.fileBook = fileBook;
    this.fileEncr = fileEncr;
    this.fileDecr = fileDecr;
    this.cipher = cipher;
  }

  public void decryption() throws IOException {
    createDecryptedCipher();
    fullDecryptedCipher(cipher);

    RewriteFile rw = new RewriteFile();
    rw.decryption(fileEncr, fileDecr, decrypt);
  }

  private void createDecryptedCipher() throws IOException { // create cipher with using bigrams

    Map<String, Double> bigrams = new LinkedHashMap<>();
    List<String> bidramsBook = new LinkedList<>();
    List<String> bigramsEncrText = new LinkedList<>();


    Bigrams f = new Bigrams(fileEncr);                   // find bigrams in encrypted chapter
    bigrams = f.findBigrams();
    Iterator<String> it = bigrams.keySet().iterator();

    for (int i = 0; i < QUANTITY_BIGRAMS; i++) {        // add to top-10 list
      if (it.hasNext()) {
        bigramsEncrText.add(it.next());
      }
    }

    Bigrams b = new Bigrams(fileBook);                  // find bigrams in book
    bigrams = b.findBigrams();
    it = bigrams.keySet().iterator();

    for (int i = 0; i < QUANTITY_BIGRAMS; i++) {        // add to top-10 list
      if (it.hasNext()) {
        bidramsBook.add(it.next());
      }
    }

    it = bidramsBook.iterator();                       // create changing alphabet
    for (String s1 : bigramsEncrText) {
      if (it.hasNext()) {
        String s2 = it.next();
        if (!(decrypt.containsKey(Character.toString(s1.charAt(0))) ||
                decrypt.containsValue(Character.toString(s2.charAt(0)))))
          decrypt.put(Character.toString(s1.charAt(0)), Character.toString(s2.charAt(0)));
        if (!(decrypt.containsKey(Character.toString(s1.charAt(1))) ||
                decrypt.containsValue(Character.toString(s2.charAt(1)))))
          decrypt.put(Character.toString(s1.charAt(1)), Character.toString(s2.charAt(1)));
      }
    }

  }

  private void fullDecryptedCipher(Map<String, String> decryptedCipher) { // create full alphabet with cipher from frequency analysis
    Map<String, String> dec = new HashMap<>();

    while (true) {
      for (String str : decrypt.keySet()) {
        if (!decrypt.containsKey(decrypt.get(str))) {
          dec.put(decrypt.get(str), decryptedCipher.get(str));
        }
      }
      if (dec.isEmpty())
        break;
      decrypt.putAll(dec);
      dec.clear();
    }

    if (decrypt.size() != 32) {
      for (String s : decryptedCipher.keySet()) {
        if (!decrypt.containsKey(s)) {
          decrypt.put(s, decryptedCipher.get(s));
        }
      }
    }
  }

  private static class Bigrams {
    private final String nameFile;
    private Map<String, Double> bigrams = new LinkedHashMap<>();
    private FileReader originalText;
    static private Set<Character> alphabet = new HashSet<>();

    private Bigrams(String nameFile) {
      this.nameFile = nameFile;
      addAlphabet();
    }

    private void addAlphabet() {
      for (int i = 0; i < 32; i++) {
        alphabet.add((char) ('а' + i));
      }
    }

    Map<String, Double> findBigrams() throws IOException {
      int first, second;
      StringBuilder buf = new StringBuilder();

      {
        try {
          this.originalText = new FileReader(nameFile);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      second = originalText.read();
      while (true) {
        if ((first = second) != -1) {
          if (Character.isUpperCase((char) first)) {
            first = Character.toLowerCase(first);
          }
          if (alphabet.contains((char) first)) {
            if ((second = originalText.read()) != -1) {
              if (Character.isUpperCase((char) second)) {
                second = Character.toLowerCase(second);
              }
              if (alphabet.contains((char) second)) {
                buf.append((char) first);
                buf.append((char) second);
                if (!bigrams.containsKey(buf.toString())) {
                  bigrams.put(buf.toString(), 1.0);
                } else {
                  Double d = bigrams.get(buf.toString());
                  bigrams.put(buf.toString(), d + 1.0);
                }
                buf.delete(0, 2);
              } else second = originalText.read();
            } else originalText.close();
          } else second = originalText.read();
        } else {
          originalText.close();
          break;
        }
      }

      SortedHashMapForValue sort = new SortedHashMapForValue(bigrams);
      bigrams = sort.sorting();

      return bigrams;
    }
  }
}
