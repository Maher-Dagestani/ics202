package ui;

import utils.KeyHandler;
import utils.Term;
import utils.KEYS;
import Player.Player;

public class UI {
  private Term terminal;
  private KeyHandler kh;
  private Player player;

  public UI(Player player) throws Exception {
    this.player = player;
    this.terminal = new Term();
    this.terminal.enterAlt();
    this.kh = new KeyHandler(terminal.getReader());
    this.terminal.setCursorVisiblity(false);
  }
  public void loop() throws Exception {
    Color winColor = new Color("#1e1e1e", "#2d333d");
    Rect rect = new Rect(0, 0, terminal.getWidth(), terminal.getHeight());
    Window win = new Window(rect , winColor);
    InventoryPanel sp = new InventoryPanel(this.terminal, this.player);
    int k = -1;
    while (k != 'q') {
      win.drawBg();
      win.refresh();
      sp.draw();

      k = kh.getKey();
      sp.update(k);
    }
    this.terminal.close();
  }
}
