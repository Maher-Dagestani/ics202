public class Player {
  private Room currentRoom;
  private int moves;
  private String name;
  private Inventory inventory;
  private Queue missions;

  public Player(String name, Room startRoom, int moves) {
    this.name = name;
    this.currentRoom = startRoom;
    this.moves = moves;
    this.inventory = new Inventory();
  }
  
  public Room getCurrentRoom() { return currentRoom; }
  public Inventory getInventory() { return inventory; }
  public Queue getMissions() { return missions; }
}
