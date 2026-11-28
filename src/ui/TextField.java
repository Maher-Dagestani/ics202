package ui;

import ui.Window;
import ui.Rect;
import ui.Color;

public class TextField {
  private Window win;
  private boolean focused;
  private int cursorIdx;
  private Rect rect;
  private Color color;

  private String text;
  private String placeholder;


  public TextField(String placeholder, Rect rect, Color color) {
    this.placeholder = placeholder;
    this.text = "";
    this.rect = rect;
    this.color = color;
    this.win = new Window(this.rect, this.color);
  }

  public void draw() {
    if (text.isEmpty() || !this.focused) color.setFgBrightness(0.6f);
    else color.setFgBrightness(1.0f);
    win.drawBox(this.rect, color);
    win.print(text.isEmpty() ? placeholder : text, this.rect.x+1, this.rect.y+1, this.color);
    win.refresh();
  }

  public void setFocus(boolean x) { this.focused = x; }
  public void toggleFocus() { this.focused = !this.focused; }
  public String getText() { return text; }
  
}
