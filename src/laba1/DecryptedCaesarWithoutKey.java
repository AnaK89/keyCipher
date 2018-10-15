package laba1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DecryptedCaesarWithoutKey implements Decrypt {
  private final String fileBook;
  private final String fileEncrText;
  private final String fileDecrText;
  private Map<String, String> newCipher = new HashMap<>();


  public DecryptedCaesarWithoutKey(String fileBook, String fileEncrText, String fileDecrText) {
    this.fileBook = fileBook;
    this.fileEncrText = fileEncrText;
    this.fileDecrText = fileDecrText;
  }

  public void decryption() throws IOException {
    LetterFrequency book = new LetterFrequency(fileBook);               //count frequency of letters from Book
    Map<String, Double> frequencyOriginalBook = book.getLetterIsFound();

    LetterFrequency encryptedChapter =
            new LetterFrequency(fileEncrText);                    //count frequency of letters from encrypted chapter
    Map<String, Double> frequencyEncryptedChapter = encryptedChapter.getLetterIsFound();

    Iterator<String> iteratorChapter = frequencyEncryptedChapter.keySet().iterator();
    Iterator<String> iteratorBook = frequencyOriginalBook.keySet().iterator();

    for (int i = 0; i < 32; i++) {
      if (iteratorBook.hasNext() && iteratorChapter.hasNext()) {
        newCipher.put(iteratorChapter.next(), iteratorBook.next());
      }
    }

    RewriteFile rw = new RewriteFile();
    rw.decryption(fileEncrText, fileDecrText, newCipher);
  }

  public Map<String, String> getNewCipher() { // for use at bigrams
    return this.newCipher;
  }

}
