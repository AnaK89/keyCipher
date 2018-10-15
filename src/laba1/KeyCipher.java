package laba1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class KeyCipher implements Encrypt {
  private final String encryptedFile;
  private final String originalFile;
  static private Character[] alphabet = new Character[32];
  private final String key;
  private Map<String, String> cipher = new HashMap();

  public KeyCipher(String key, String originalFile, String encryptedFile) {
    this.originalFile = originalFile;
    this.encryptedFile = encryptedFile;
    this.key = key;
    addAlphabet();
    addCipher();
  }

  private void addAlphabet() {                    // create russians alphabet (without ё)
    for (int i = 0; i < 32; i++) {
      alphabet[i] = (char) ('а' + i);
    }
  }

  private void addCipher() {                      // create cipher with using key
    char[] key = this.key.toCharArray();

    for (int i = 0; i < alphabet.length; i++) {
      if (i < key.length) {
        cipher.put(alphabet[i].toString(), Character.toString(key[i]));
      } else {
        for (Character j : alphabet) {
          if (!this.key.contains(j.toString())) {
            cipher.put(alphabet[i].toString(), j.toString());
            i++;
          }
        }
      }
    }
  }

  public void encryption() throws IOException {
    RewriteFile rw = new RewriteFile();
    rw.decryption(originalFile, encryptedFile, cipher);
  }

  Map getCipher() {
    return this.cipher;
  }

}
