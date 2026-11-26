import leaderboard.Leaderboard;
import leaderboard.LeaderboardPlayer;
import objects.Item;
import objects.Room;
import Player.Player;
import Player.Inventory;
import DS.Queue;
import DS.SLL;
import DS.SLLNode;

public class Main {
  public static void main(String[] args) {
    // FileHandler config = new FileHandler("castle.config");
    // while (config.hasNext()) {
    //   System.out.println(config.next());
    // }
    
    Leaderboard leaderboard = new Leaderboard();
    SLL<LeaderboardPlayer> list = leaderboard.getLeaderboard();

    SLLNode<LeaderboardPlayer> node = list.getHead();

    while (node != null) {
      System.out.printf("%s, %d\n",node.info.getName(), node.info.getScore());
      node = node.next;
    }

    leaderboard.addPlayer("Ahmad", 20);
    leaderboard.addPlayer("Mansourdw", 2);
    leaderboard.saveFile();

    // Player p = new Player("Mansour", new Room(), new Queue<>(), 25);
    
    // Inventory inv = new Inventory();
    // Item a = new Item("TK", "Torch");
    // Item b = new Item("TK2", "Torch2");
    // Item c = new Item("TK3", "Torch3");
    //
    // inv.add(a);
    // inv.add(b);
    // inv.add(c);
    // System.out.println(inv);

  }
}
