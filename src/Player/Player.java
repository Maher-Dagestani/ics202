package Player;

import objects.Room;
import DS.Queue;

public class Player {
  private Room currentRoom;
  private int moves;
  private String name;
  private Inventory inventory;
  private Queue<Mission> missions;

  public Player(String name, Room startRoom, Queue missions, int moves) {
    this.name = name;
    this.currentRoom = startRoom;
    this.missions = new Queue<>();
    this.moves = moves;
    this.inventory = new Inventory();
  }
  
  public Room getCurrentRoom() { return currentRoom; }
  public Inventory getInventory() { return inventory; }
  public Queue getMissions() { return missions; }

  public void move(Room room) {

  }

}
