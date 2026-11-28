package ui;

import ui.Rect;

public class Window {
  private Rect rect;
  private Color color;
  private StringBuilder buf = new StringBuilder();

  public Window(Rect rect, Color color) {
    this.rect = rect;
    this.color = color;
  }

  // draw a box
  public void drawBox(Rect rect, Color c) {

    for (int i = 0; i < rect.h; i++) {
      buf.append("\u001B[")
        .append(this.rect.y+rect.y + i + 1).append(";")
        .append(this.rect.x+rect.x + 1).append("H")
        .append(c);
      buf.append(" ".repeat(this.rect.w));
    }
    buf.append("\u001B[0m");
  }

  public void drawBg() {
    drawBox(new Rect(0, 0, this.rect.w, this.rect.h), this.color);
  }

  public Rect getRect() {
    return this.rect;
  }


  // appends the String argument to the Stringbuilder

  public void print(String text, int x, int y) {
    this.print(text, x, y, this.color);
  }
  public void print(String text, int x, int y, Color c) {
    if (y == -1) y = (this.rect.h - 1) / 2;
    String[] list = text.split("\n");
    for (int i = 0; i < list.length; i++) {
      String line = list[i];
      String visible = line.replaceAll("\\033\\[[\\d;]*m", "");
      int lineX = (x == -1) ? (this.rect.w - visible.length()) / 2 : x;
      int available = this.rect.w - lineX;
      if (available <= 0) continue;
      int n = Math.min(line.length(), available);
      buf.append(c)
        .append("\u001B[")
        .append(this.rect.y + y + (i + 1)).append(";")
        .append(this.rect.x + lineX + 1).append("H")
        .append(line, 0, n);
    }
  }
  
  // refreshes the Terminal to print the new frame
  public void refresh() {
    System.out.print(buf);
    System.out.flush();
    buf.setLength(0);
  }
}
