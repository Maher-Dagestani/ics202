package Player;

import DS.Queue;
import objects.CurrentRoom;
import objects.Item;

public class Player {
  private CurrentRoom currentRoom;
  private int moves;
  private String name;
  private Inventory inventory;
  private Queue<Mission> missions;

  public Player(String name, CurrentRoom currentRoom, Queue missions, int moves) {
    this.name = name;
    this.currentRoom = currentRoom;
    this.missions = new Queue<>();
    this.moves = moves;
    this.inventory = new Inventory();
  }
  
  public CurrentRoom getCurrentRoom() { return currentRoom; }
  public Inventory getInventory() { return inventory; }
  public Queue getMissions() { return missions; }
  public void collectItem(Item item) { inventory.add(item); }

  // public void move(Room room) {
  //
  // }

}
