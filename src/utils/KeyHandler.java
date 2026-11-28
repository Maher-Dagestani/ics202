package utils;

import java.io.IOException;
import org.jline.utils.NonBlockingReader;


public class KeyHandler {
  private NonBlockingReader reader;

  public KeyHandler(NonBlockingReader reader) {
    this.reader = reader;
  }

  public int getKey() throws IOException{
    int k = reader.read();
    if (k == -1) return -1;

    if (k == 13 || k == 10) return KEYS.ENTER.code();
    if (k == 127 || k == 8) return KEYS.BACKSPACE.code();
    if (k != KEYS.ESC.code()) return k;

    int next = reader.read(50);
    if (next == NonBlockingReader.READ_EXPIRED || next == -1) return KEYS.ESC.code();
    if (next != '[' && next != 'O') return -3;

    StringBuilder seq = new StringBuilder("[");
    int ch;
    while ((ch = reader.read(50)) != NonBlockingReader.READ_EXPIRED && ch != -1) {
      seq.append((char) ch);
      if (Character.isLetter(ch) || ch == '~') break;
    }

    KEYS key = KEYS.fromSequence(seq.toString());
    return key == null ? -3 : key.code();
  }

}
