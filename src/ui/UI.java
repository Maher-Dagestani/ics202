package ui;

import utils.KeyHandler;
import utils.Term;
import utils.KEYS;

public class UI {
  private Term terminal;
  private KeyHandler kh;

  public UI() throws Exception {
    this.terminal = new Term();
    this.kh = new KeyHandler(terminal.getReader());
  }
  public void loop() throws Exception {
    Color winColor = new Color("#ffffff", "#1e1e1e");
    Window win = new Window(0, 0, terminal.getWidth(), terminal.getHeight(), winColor);
    StringBuilder text = new StringBuilder();
    int k;
    while ((k = kh.getKey()) != 'q') {
      KEYS key = KEYS.fromCode(k);

      win.drawBg();
      win.print(text.toString(), 0, 0);
      win.refresh();
      if (key == null) {
        text.append((char) k);               // plain character
      } else {
        switch (key) {
          case BACKSPACE:
            if (text.length() > 0) text.deleteCharAt(text.length() - 1);
            break;
          case ENTER:
            text.append('\n');
            break;
          case TAB:
            text.append('\t');
            break;
          default:
            break;                       // arrows and others: ignored for now
        }
      }

    }

  }
}
