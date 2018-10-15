package laba1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DecryptedCaesarWithKey implements Decrypt {
  private final String encrFile;
  private final String decrFile;
  private Map<String, String> cipher;

  public DecryptedCaesarWithKey(String key, String encrFile, String decrFile) {
    this.encrFile = encrFile;
    this.decrFile = decrFile;
    KeyCipher c = new KeyCipher(key, decrFile, encrFile);
    this.cipher = changeCipher(c.getCipher());
  }

  private Map<String, String> changeCipher(Map<String, String> cipher) { //change encrypt cipher to decrypt
    Map<String, String> newCipher = new HashMap<>();
    for (String str : cipher.keySet()) {
      newCipher.put(cipher.get(str), str);
    }
    return newCipher;
  }

  public void decryption() throws IOException {
    RewriteFile rw = new RewriteFile();
    rw.decryption(encrFile, decrFile, cipher);
  }
}
