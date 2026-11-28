package ui;

import DS.SLL;
import DS.SLLNode;
import Player.Inventory;
import Player.Player;
import objects.Item;
import utils.KEYS;
import utils.Term;

public class InventoryPanel {
  private Player player;
  private SLL<Item> inventory;
  private Rect InventoryRect;
  private Window InventoryWin;
  private Color winColor, itemColor, focusedItemColor;
  private int w, h, idx;

  public InventoryPanel(Term terminal, Player player) {
    this.w = terminal.getWidth()/5;
    this.h = terminal.getHeight();
    this.idx = 0;
    this.player = player;
    this.InventoryRect = new Rect(0, 0, w, h);
    this.winColor = new Color("#d4d4d4", "#1e1e1e");
    this.itemColor = this.winColor;
    this.focusedItemColor = new Color("#1e1e1e", "#d4d4d4");
    this.itemColor.setBgBrightness(0.6f);
    this.itemColor.setFgBrightness(0.6f);
    this.InventoryWin = new Window(this.InventoryRect, winColor);
    this.inventory = player.getInventory().getItems();
  }
  public void draw() {
    this.InventoryWin.drawBg();
    this.InventoryWin.refresh();
    SLLNode<Item> node = this.inventory.getHead();

    int i = 0;
    while (node != null) {
      Rect rect = new Rect(2, 2+(i*4), this.w-4, 3);
      Window win = new Window(rect, i==this.idx ? focusedItemColor : itemColor);
      String item = node.info.getName();
      win.drawBg();
      win.print(item, (this.w - item.length())/2-2, 1);
      win.refresh();

      node = node.next;
      i++;
    }
  }

  public void update(int k) {
    KEYS key = KEYS.fromCode(k);
    if (key == null) return;   // plain character, nothing to do here
    switch (key) {
      case KEYS.UP:
        idx = --idx < 0 ? inventory.size()-1 : idx;
        break;
      case KEYS.DOWN:
        idx = ++idx >= inventory.size() ? 0 : idx;
        break;

      default:
        break;
    }
  }
}
