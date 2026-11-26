package ui;

public class Window {
  private int x, y;
  private int width, height;
  private Color color;
  private StringBuilder buf = new StringBuilder();

  public Window(int x, int y, int width, int height, Color color) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  // draw a box
  public void drawBox(int x, int y, int w, int h, Color c) {
    for (int i = 0; i < h; i++) {
      buf.append("\u001B[")
        .append(this.y+y + i + 1).append(";")
        .append(this.x+x + 1).append("H")
        .append(c);
      buf.append(" ".repeat(w));
    }
    buf.append("\u001B[0m");
  }

  public void drawBg() {
    drawBox(0, 0, this.width, this.height, this.color);
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }


  // appends the String argument to the Stringbuilder

  public void print(String text, int x, int y) {
    this.print(text, x, y, this.color);
  }
  public void print(String text, int x, int y, Color c) {
    if (y == -1) y = (this.height - 1) / 2;
    String[] list = text.split("\n");
    for (int i = 0; i < list.length; i++) {
      String line = list[i];
      String visible = line.replaceAll("\\033\\[[\\d;]*m", "");
      int lineX = (x == -1) ? (this.width - visible.length()) / 2 : x;
      int available = this.width - lineX;
      if (available <= 0) continue;
      int n = Math.min(line.length(), available);
      buf.append(c)
        .append("\u001B[")
        .append(this.y + y + (i + 1)).append(";")
        .append(this.x + lineX + 1).append("H")
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
