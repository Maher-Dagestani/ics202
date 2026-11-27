package Player;

public class Mission {
  public enum TYPE {
    COLLECT, REACH
  }

  private TYPE type;
  private String id;

  public Mission(TYPE type, String id) {
    this.type = type;
    this.id = id;
  }

  public String getId() { return this.id; }

  public boolean isEqual(Mission other) { return other != null && this.type == other.type && this.id.equals(other.getId()); }

}
