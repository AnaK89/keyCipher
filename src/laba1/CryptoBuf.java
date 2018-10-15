package laba1;

import java.util.Map;

class CryptoBuf {
  private Map<String, String> cipher;
  private char[] buf;

  CryptoBuf(char[] buf, Map<String, String> cipher) {
    this.buf = buf;
    this.cipher = cipher;
  }

  char[] cryptoBuf() {                       //return new encrypted/decrypted buf
    char[] eBuf = new char[buf.length];
    Character c;
    boolean upperCase = false;

    for (int i = 0; i < buf.length; i++) {
      if (Character.isUpperCase(buf[i])) {
        c = Character.toLowerCase(buf[i]);
        upperCase = true;
      } else c = buf[i];
      if (cipher.containsKey(Character.toString(c))) {
        if (upperCase) {
          c = cipher.get(Character.toString(c)).charAt(0);
          eBuf[i] = Character.toUpperCase(c);
          upperCase = false;
        } else eBuf[i] = cipher.get(Character.toString(c)).charAt(0);
      } else eBuf[i] = buf[i];
    }
    return eBuf;
  }
}
