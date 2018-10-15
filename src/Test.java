import laba1.*;

import java.io.IOException;

public class Test {
  public static void main (String[] args) throws IOException {
    String key = "чайник";

    // encrypt chapter with key
    Encrypt c = new KeyCipher(key, "originalText.txt", "encryptedText.txt");
    c.encryption();

    // decrypt chapter with key
    Decrypt withKey = new DecryptedCaesarWithKey(key, "encryptedText.txt", "decryptedWithKey.txt");
    withKey.decryption();

    // decrypt chapter without key
    DecryptedCaesarWithoutKey withoutKey = new DecryptedCaesarWithoutKey( "Book.txt",
            "encryptedText.txt","decryptedWithoutKey.txt");
    withoutKey.decryption();

    // decrypt chapter with bigrams
    Decrypt bigrams = new DecryptedWithBigram("Book.txt", "encryptedText.txt",
            "decryptedWithBigrams.txt", withoutKey.getNewCipher());
    bigrams.decryption();

  }
}
