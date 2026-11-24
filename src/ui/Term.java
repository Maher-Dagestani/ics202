import java.util.ArrayList;
import java.util.Arrays;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Attributes;


public class Term {
  private ArrayList<Event> events;
  private Terminal terminal;
  private Attributes old;
  private ArrayList<Window> wins;
  private int winIdx;
  private boolean cursorStatus;
  private boolean editMode;
  private int x, y;
  private int eventIdx;
  private int descTabIdx;
  private int W, H;

  public int KEY_UP = 65;
  public int KEY_DOWN = 66;
  public int KEY_RIGHT = 67;
  public int KEY_LEFT = 68;
  public int KEY_SHIFT_RIGHT = 67;
  public int KEY_SHIFT_LEFT = 68;
  public int KEY_ENTER = 13;
  public int KEY_BACKSPACE = 127;
  public int KEY_ESC = 27;
  public int KEY_TAB = 9;
  public int KEY_SHIFT_TAB = 90;
  public int KEY_CTRL_Q = 17;
  public int KEY_A = 97;
  public int KEY_E = 101;
  
  public Term(ArrayList<Event> events) throws Exception {
    this.cursorStatus = false;
    System.out.print("\u001B[?1049h"); // enter alt screen
    System.out.print("\u001B[?25l");      // move back
    setCursor(false);
    System.out.flush();


    // Initializing terminal
    this.terminal = TerminalBuilder.builder()
      .system(true)
      .build();

    this.old = terminal.enterRawMode();

    // Passing the events list
    this.events = events;

    // Defining Windows
    this.W = this.terminal.getWidth();
    this.H = this.terminal.getHeight();
    this.wins = new ArrayList<>();
    this.wins.add(new Window(this.W/3, (this.H-9)/2, this.W/3, 9));
    this.wins.add(new Window(0, 0, this.W/2, this.H));
    this.wins.add(new Window(this.W/2, 0, this.W/2, this.H));
    this.wins.add(new Window(this.W/4, 4, this.W/2, this.H-8));
  
    // Setting the default cursor position
    this.x = 0;
    this.y = 0;
    this.winIdx = 0;
    this.eventIdx = 0;
    this.descTabIdx = 0;
    this.editMode = false;
  }

  public void setEditMode(boolean x) {
    this.editMode = x;
  }

  public void coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int[] getCoord() {
    return new int[] {this.x, this.y};
  }

  public void setWinIdx(int idx) {
    this.winIdx = idx;
  }

  public int getWinIdx() {
    return this.winIdx;
  }

  public ArrayList<Window> getWins() {
    return this.wins;
  }

  private int[] getX(ArrayList<Event> list, Window win) {
    int max = 0;
    for (Event e : list) {
      max = Math.max(e.getTitle().length(), max);
    }
    max = Math.max(max, win.getWidth()*2/3);
    int ix = (win.getWidth() - max)/2;
    int jx = ix+max;
    return new int[] {ix, jx};
  }

  public void drawDescription(Event e, String userId, boolean dateErr) {
    Color winColor = new Color("#ffffff", "#2b2b2b");
    Color selected = this.editMode ? new Color("#ffffff", "#171717") : winColor;
    Color full = new Color("#C44545", "#2b2b2b");
    Color available = new Color("#A2CB8B", "#2b2b2b");
    Color bar = new Color("#F7F8F0", "#2b2b2b", 0.3f, 1.0f);
    Window win = this.wins.get(2);
    ArrayList<String> tabs = new ArrayList<>(Arrays.asList("Event", "Venue", "Department"));
    if (this.winIdx != 2) {
      winColor.setBgBrightness(0.3f);
      winColor.setFgBrightness(0.3f);
      full.setBgBrightness(0.3f);
      full.setFgBrightness(0.3f);
      available.setBgBrightness(0.3f);
      available.setFgBrightness(0.3f);
      bar.setBgBrightness(0.3f);
      bar.setFgBrightness(0.3f);
      selected.setBgBrightness(0.3f);
      selected.setFgBrightness(0.3f);
    }

    win.drawBg(winColor);

    int counter = (win.getWidth()-32)/2;
    for (int i=0; i<tabs.size(); i++) {
      if (i==this.descTabIdx && this.winIdx == 2) bar.setFgBrightness(1.0f);
      else if (i==this.descTabIdx && this.winIdx != 2) bar.setFgBrightness(0.3f);
      String tab = tabs.get(i);
      win.print(String.format("\033[1m  %s  \n\n%s\033[0m", tab, "▔".repeat(tab.length()+4)), counter, 1, bar);
      counter += tab.length()+4;
      bar.setFgBrightness(0.3f);
    }

    int stX, stY;
    stY = (win.getHeight()-10)/2;
    stX = (win.getWidth()-26)/2;


    if (e != null) {
      Date stDate = e.getStDate();
      Date endDate = e.getEndDate();
      Department dep = e.getDepartment();
      Venue ven = e.getVenue();
      int w = win.getWidth();


      if (dateErr) win.print("Invalid Date", -1, 5, full); 
      if (String.valueOf(dep.getSponsorId()).equals(userId)) win.print("Editable", -1, 6, winColor);
      if (e.getAttendance().contains(Integer.parseInt(userId.length() == 0 ? "1" : userId))) win.print("Registered", -1, 7, winColor);
  
      // Event Drawing
      if (this.descTabIdx == 0) {
        win.print("\033[1m●  Event:\033[0m", stX, stY, winColor);
        win.print("Title : ", stX+3, stY+3, winColor);
        win.print(e.getTitle(), stX+11, stY+3, this.y == 0 ? selected : winColor);
        win.print("Category : ", stX+3, stY+6, winColor);
        win.print(e.getCategory(), stX+14, stY+6, this.y == 1 ? selected : winColor);

        win.print("Date :             ->", stX+3, stY+9, winColor);
        win.print(stDate.getDate(), stX+11, stY+9, (this.y >= 2 && this.y <= 4) ? selected : winColor);
        win.print(stDate.getTime(), stX+11, stY+10, (this.y >= 5 && this.y <= 6) ? selected : winColor);
        win.print(endDate.getDate(), stX+25, stY+9, (this.y >= 7 && this.y <= 9) ? selected : winColor);
        win.print(endDate.getTime(), stX+25, stY+10, (this.y >= 10 && this.y <= 11) ? selected : winColor);
        
        win.print("Attendance : ", stX+3, stY+13, winColor);
        win.print(String.format("%d/%d", e.getAttendance().size(), ven.getCapacity()), stX+16, stY+13, e.isFull() ? full : available);
      }

      // Venue Drawing
      if (this.descTabIdx == 1) {
        win.print("\033[1m●  Venue:\033[0m", stX, stY, winColor);
        win.print("Title : ", stX+3, stY+3, winColor);
        win.print(ven.getTitle(), stX+11, stY+3, this.y == 0 ? selected : winColor);
        win.print("Building : ", stX+3, stY+6, winColor);
        win.print(ven.getBldgNum()+"", stX+15, stY+6, this.y == 1 ? selected : winColor);
        win.print("Room : ", stX+22, stY+6, winColor);
        win.print(ven.getRoomNum()+"", stX+29, stY+6, this.y == 2 ? selected : winColor);
        win.print("Capacity : ", stX+3, stY+9, winColor);
        win.print(ven.getCapacity()+"", stX+14, stY+9, this.y == 3 ? selected : winColor);

     }
      // Department Drawing
      if (this.descTabIdx == 2) {
        win.print("\033[1m●  Department:\033[0m", stX, stY, winColor);
        win.print("Department: ", stX+3, stY+3, winColor);
        win.print(dep.getTitle(), stX+15, stY+3, this.y == 0 ? selected : winColor);
        win.print("Sponsor : ", stX+3, stY+6, winColor);
        win.print(dep.getSponsor(), stX+15, stY+6, this.y == 1 ? selected : winColor);
        win.print("SponsorId : ", stX+3, stY+9, winColor);
        win.print(dep.getSponsorId()+"", stX+15, stY+9, this.y == 2 ? selected : winColor);
      }
    } else {
      win.print("No information", -1, -1, winColor);
    }
    win.refresh();
    if (editMode && winIdx == 2 && e!=null) {
      setCursor(true);
      int x = win.getX()+stX;
      int y = win.getY()+stY+4+(this.y*3);
      Venue v = e.getVenue();
      Department d = e.getDepartment();
      if (this.descTabIdx == 0) {
        if (this.y == 0) x += 12+e.getTitle().length();
        else if (this.y == 1) x += 15+e.getCategory().length();
        else if (this.y == 2) x += 16;
        else if (this.y == 3) {
          x += 19;
          y -= 3;  
        }
        else if (this.y == 4) {
          x += 22;
          y -= 6;
        } 
        else if (this.y == 5) {
          x += 14;
          y -= 8;
        } else if (this.y == 6) {
          x += 17;
          y -= 11;
        } else if (this.y == 7) {
          x += 30;
          y -= 15;
        } else if (this.y == 8) {
          x += 33;
          y -= 18;
        } else if (this.y == 9)  {
          x += 36;
          y -= 21;
        } else if (this.y == 10) {
          x += 28;
          y -= 23;
        } else if (this.y == 11) {
          x += 31;
          y -= 26;
        }
      } else if (descTabIdx == 1) {
        if (this.y == 0) {
          x += 12+v.getTitle().length();
        } else if (this.y == 1) {
          x += 16+String.valueOf(v.getBldgNum()).length();
        } else if (this.y == 2) {
          x += 30+String.valueOf(v.getRoomNum()).length();
          y -= 3;
        } else if (this.y == 3) {
          x += 15+String.valueOf(v.getCapacity()).length();
          y -= 3;
        }
      } else if (descTabIdx == 2) {
        if (this.y == 0) {
          x += 16+d.getTitle().length();
        } else if (this.y == 1) {
          x += 16+d.getSponsor().length();
        } else if (this.y == 2) {
          x += 16+String.valueOf(d.getSponsorId()).length();
        }
      }
      setCursorPosition(x, y);
    }
  }

  public void drawEvents() {
    // Initializing colors
    Color c = new Color("#1e1e1e", "#F7F8F0", 0.5f, 0.5f);
    Color winColor = new Color("#ffffff", "#2b2b2b");
    Window win = this.wins.get(1);
    int[] x = getX(this.events, win); 
    int ix = x[0];
    int jx = x[1];
    int maxElements = (this.H - 8)/4;
    int page = this.eventIdx/maxElements+1;
    int pages = this.events.size()/maxElements+1;
    if (this.winIdx != 1) {
      winColor.setFgBrightness(0.3f);
      winColor.setBgBrightness(0.3f);
    }
    win.drawBg(winColor);

    if (!this.events.isEmpty()) {
      for (int i=(page-1)*maxElements; i<Math.min(page*maxElements, this.events.size()); i++) {
        Event e = this.events.get(i);
        int y = (win.getHeight() - maxElements*4)/2+(i*4)-(maxElements*4*(page-1));
      
        if (i == this.eventIdx && this.winIdx == 1) {
          c.setBgBrightness(1.0f);
          c.setFgBrightness(1.0f);
        } else if (i == this.eventIdx && this.winIdx != 1) {
          c.setBgBrightness(0.55f);
          c.setFgBrightness(0.55f);
        } else if (i != this.eventIdx && this.winIdx == 1) {
          c.setBgBrightness(0.5f);
          c.setFgBrightness(0.5f);
        } else if (i != this.eventIdx && this.winIdx != 1){
          c.setBgBrightness(0.3f);
          c.setFgBrightness(0.3f);
        }

        win.drawBox(ix, y, jx-ix, 3, c);
        win.print(e.getTitle(), -1, y+1, c);
        win.print(String.format("%d/%d", page, pages), -1, this.H-3, winColor);
        
      }
    } else {
      win.print("No Current events!", -1, win.getHeight()/2 -1, winColor);
      win.print("\u001B[5mPress A to Create an Event\u001B[0m", -1, win.getHeight()/2 , winColor);
    }
    win.refresh();
  }

  public void drawLogin(String id, boolean showErr) {
    id = String.join(" ", id.split(""));
    Color winColor = new Color("#ffffff", "#2b2b2b");
    Color field = new Color("#ffffff", "#3d3d3d");
    Color err = new Color("#CE2626", "#2b2b2b");
    Window win = this.wins.get(0);
    int x = (win.getWidth()-17)/2;
    win.drawBg(winColor);
    win.print("Enter KFUPM ID", 2, 1, winColor);
    winColor.setFgBrightness(0.3f);
    win.print("▔".repeat(win.getWidth()-4), 2, 2, winColor);
    win.drawBox((win.getWidth()-23)/2, 4, 23, 3, field);
    if (showErr) win.print("Please Enter vaild ID", -1, 3, err);
    win.print(id, x, 5, field);
    win.refresh();
  }

  public void drawDialogBox(String title, String leftBtn, String rightBtn, Color c1, Color c2) {
    Color winColor = new Color("#ffffff", "#2e2e2e");
    int width = leftBtn.length()+rightBtn.length()+25;
    int height = 9;
    Window win = new Window((this.W-width)/2, (this.H-height)/2, width, height);
    win.drawBox(0, 0, width, height, winColor);

    win.print(title, 2, 1, winColor);
    win.print("▔".repeat(win.getWidth()-4), 2, 3, winColor);

    if (this.x == 0) {
      c1.setBgBrightness(1.0f);
      c1.setFgBrightness(1.0f);
      c2.setBgBrightness(0.3f);
      c2.setFgBrightness(0.3f);
    } else if (this.x == 1) {
      c1.setBgBrightness(0.3f);
      c1.setFgBrightness(0.3f);
      c2.setBgBrightness(1.0f);
      c2.setFgBrightness(1.0f);
    }

    win.print(String.format("  %s  ", leftBtn), (width/2 - rightBtn.length())/2, 5, c1);
    win.print(String.format("  %s  ", rightBtn), (width/2 - rightBtn.length())/2+(width/2), 5, c2);

    win.refresh();
  }

  public void eventsScroll(int dir) {
    if (this.eventIdx+dir == -1) this.eventIdx = this.events.size()-1;
    else if (this.eventIdx+dir == this.events.size()) this.eventIdx = 0;
    else this.eventIdx = this.eventIdx+dir;
  }

  public void setCursor(boolean x) {
    System.out.print("\u001B[5 q");
    System.out.print(x ? "\u001B[?25h" : "\u001B[?25l");
  }

  public void setCursorPosition(int x, int y) {
    System.out.printf("\033[%d;%dH", y, x);
    System.out.flush();
  }

  public void descScroll(int dir) {
    if (this.descTabIdx+dir == -1)
      this.descTabIdx = 2;
    else if (this.descTabIdx+dir == 3)
      this.descTabIdx = 0;
    else
      this.descTabIdx += dir;
    this.y = 0;
  }

  public int getKey() throws Exception {
    int key = terminal.reader().read();
    if (key == 27) {
        terminal.reader().read(); // consume 91
        return terminal.reader().read(); // returns 65,66,67,68
    }
    return key;
}

  public int getDescTabIdx() {
    return this.descTabIdx;
  }

  public int getEventIdx() {
    return this.eventIdx;
  }
  
  public void setEventIdx(int x) {
    this.eventIdx = x;
  }

  public int[] getTermSize() {
    return new int[]{this.terminal.getWidth(), this.terminal.getHeight()};
  }

  public int[] refreshTermSize() {
    this.W = this.terminal.getWidth();
    this.H = this.terminal.getHeight();
    this.wins.set(0, new Window(this.W/3, (this.H-9)/2, this.W/3, 9));
    this.wins.set(1, new Window(0, 0, this.W/2, this.H));
    this.wins.set(2, new Window(W/2, 0, this.W/2, this.H));
    this.wins.set(3, new Window(this.W/4, 4, this.W/2, this.H-8));

    return new int[] {this.W, this.H};
  }

  public void close() throws Exception {
    System.out.print("\u001B[?1049l");
    System.out.print("\u001B[?25h");      // move back
    setCursor(true);
    System.out.flush();
    this.terminal.setAttributes(old);
    this.terminal.close();

  }
}
