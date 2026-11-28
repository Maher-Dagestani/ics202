package utils;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;


public class Term {
  Terminal terminal;
  NonBlockingReader reader;

  public Term() throws Exception{
    this.terminal = TerminalBuilder.builder().system(true).build();
    terminal.enterRawMode();
    this.reader = terminal.reader();
  }

  public NonBlockingReader getReader() { return this.reader; }
  public int getWidth() { return this.terminal.getWidth(); }
  public int getHeight() { return this.terminal.getHeight(); }
  public void enterAlt() { 
    this.terminal.puts(InfoCmp.Capability.enter_ca_mode);
    this.terminal.flush();
  }

  public void exitAlt() { 
    this.terminal.puts(InfoCmp.Capability.exit_ca_mode);
    this.terminal.flush();
  }

  public void setCursor(int n) {
    terminal.writer().print("\u001b[" + n + " q");
    terminal.flush();
  }

  public void setCursorPosition(int x, int y) {
    terminal.puts(InfoCmp.Capability.cursor_address, y, x);
    terminal.flush();
  }

  public void setCursorVisiblity(boolean x) {
    if (x)
      terminal.puts(InfoCmp.Capability.cursor_visible);
    else
      terminal.puts(InfoCmp.Capability.cursor_invisible);
    terminal.flush();
  }

  public void close() {
    exitAlt();
    setCursorVisiblity(true);
  }

  //
  // public static void main(String[] args) throws Exception {
  //   try (Terminal terminal = TerminalBuilder.builder().system(true).build()) {
  //     terminal.enterRawMode();
  //     NonBlockingReader reader = terminal.reader();
  //
  //     terminal.writer().println("Hello JLine! Press Arrow keys or any letter ('q' to quit):");
  //     terminal.flush();
  //
  //     while (true) {
  //       int c = reader.read(); // Blocks until a key is pressed
  //       if (c == -1 || c == 'q' || c == 'Q') {
  //         terminal.writer().println("Goodbye!");
  //         terminal.flush();
  //         break;
  //       }
  //
  //       // Arrow keys send an ESC (27) followed by '[' (91) and A/B/C/D
  //       if (c == 27) {
  //         int next1 = reader.read(50); // Wait up to 50ms for escape sequence
  //         if (next1 == '[') {
  //           int next2 = reader.read(50);
  //           String arrow = switch (next2) {
  //             case 'A' -> "UP";
  //             case 'B' -> "DOWN";
  //             case 'C' -> "RIGHT";
  //             case 'D' -> "LEFT";
  //               default -> "UNKNOWN_ESC";
  //           };
  //           terminal.writer().println("Arrow key: " + arrow);
  //         } else {
  //           terminal.writer().println("ESC key");
  //         }
  //       } else if (c == 13 || c == 10) {
  //         terminal.writer().println("ENTER key");
  //       } else if (c == 59 ) {
  //         int next1 = reader.read(50);
  //         if (next1 == '2') {
  //           int next2 = reader.read(50);
  //           String arrowCtrl = switch (next2) {
  //             case 'A' -> "UP";
  //             case 'B' -> "DOWN";
  //             case 'C' -> "RIGHT";
  //             case 'D' -> "LEFT";
  //               default -> "UNKNOWN_ESC";
  //           };
  //           terminal.writer().println("Ctrl Arrow key: " + arrowCtrl);
  //         }
  //       } else {
  //         terminal.writer().println("Pressed: '" + (char) c + "' (code: " + c + ")");
  //       }
  //       terminal.flush();
  //     }
  //   }
  // }
}

