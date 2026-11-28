package Player;

import DS.SLL;
import DS.SLLNode;
import objects.Item;

public class Inventory {
  private SLL<Item> items;

  public Inventory() {
    this.items = new SLL<>();
  }

  public void add(Item item) {
    items.addToTail(item);
  }

  public boolean delete(Item item) {
    return items.delete(item);
  }

  public SLL<Item> getItems() { return items; }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    SLLNode<Item> node = items.getHead();
    while (node != null) {
      sb.append(node.info.getName() + ", " + node.info.getId() + "\n");
      node = node.next;
    }

    return sb.toString();
  }

}
