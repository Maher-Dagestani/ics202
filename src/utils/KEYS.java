package utils;

public enum KEYS {
  TAB(9, null),
  ENTER(13, null),
  BACKSPACE(127, null),
  ESC(27, null),

  UP(0x10000, "[A"),
  DOWN(0x10001, "[B"),
  RIGHT(0x10002, "[C"),
  LEFT(0x10003, "[D"),

  SUP(0x10004, "[1;2A"),
  SDOWN(0x10005, "[1;2B"),
  SRIGHT(0x10006, "[1;2C"),
  SLEFT(0x10007, "[1;2D"),

  CUP(0x10008, "[1;5A"),
  CDOWN(0x10009, "[1;5B"),
  CRIGHT(0x1000A, "[1;5C"),
  CLEFT(0x1000B, "[1;5D");

  private final int code;
  private final String seq;

  KEYS(int code, String seq) {
    this.code = code;
    this.seq = seq;
  }

  public int code() {
    return code;
  }

  public String seq() {
    return seq;
  }

  public static KEYS fromSequence(String seq) {
    for (KEYS k : values()) {
      if (k.seq != null && k.seq.equals(seq)) return k;
    }
    return null;
  }

  public static KEYS fromCode(int code) {
    for (KEYS k : values()) {
      if (k.code == code) return k;
    }
    return null;
  }
}
