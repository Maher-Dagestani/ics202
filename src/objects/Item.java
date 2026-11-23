package objects;

public class Item implements Comparable<Item> {
  private String id, name;

  public Item(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() { return this.name; }
  public String getId() { return this.id; }

  @Override
  public int compareTo(Item other) {
    return this.name.compareTo(other.getName());
  }
}
