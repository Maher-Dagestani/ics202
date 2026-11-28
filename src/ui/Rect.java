package ui;

public class Rect {
  public int x, y, w, h;

  public Rect(int x, int y, int w, int h) {
    this.x = Math.max(x, 0);
    this.y = Math.max(y, 0);
    this.w = Math.max(w, 1);
    this.h = Math.max(h, 1);
  }
}
