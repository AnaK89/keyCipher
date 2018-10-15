package laba1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

class RewriteFile { //rewrite file with cipher
  private final static int BUF_LENGTH = 300;

  RewriteFile() {
  }

  void decryption(String encrFile, String decrFile, Map<String, String> cipher) throws IOException {
    FileReader encryptedText = new FileReader(encrFile);
    FileWriter newText = new FileWriter(decrFile);
    char[] buf = new char[BUF_LENGTH];

    while (encryptedText.ready()) {
      encryptedText.read(buf);
      CryptoBuf cb = new CryptoBuf(buf, cipher);
      newText.write(cb.cryptoBuf());
    }

    encryptedText.close();
    newText.flush();
    newText.close();
  }
}
