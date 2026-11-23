package leaderboard;

import DS.SLL;
import DS.SLLNode;
import utils.FileHandler;

public class Leaderboard {
  private SLL<LeaderboardPlayer> leaderboardList;
  FileHandler leaderboard;

  public Leaderboard() {
    this.leaderboard = new FileHandler("leaderboard.txt");
    this.leaderboardList = new SLL<>();
    
    if (leaderboard.isFileEmpty()) return;

    while (leaderboard.hasNext()) {
      try {
        String name = leaderboard.next();
        int score = Integer.parseInt(leaderboard.next());

        leaderboardList.addToTail(new LeaderboardPlayer(name, score));  
      } catch(Exception e) {
        System.out.println("Error occured in the leaderboard file.");
      }
    }
    leaderboardList.sort();
  }
  
  public SLL getLeaderboard() { return leaderboardList; }

  public LeaderboardPlayer getPlayer(String name) {
    SLLNode<LeaderboardPlayer> node = this.leaderboardList.getHead();
    while (node != null) {
      if (node.info.getName().equals(name)) return node.info;
      node = node.next;
    }
    return null;
  }

  public void addPlayer(String name, int score) {
    LeaderboardPlayer player = getPlayer(name);
    System.out.println(player);
    if (player != null)
      player.setScore(score);
    else 
      leaderboardList.addToTail(new LeaderboardPlayer(name, score));
    leaderboardList.sort();
  }

  public void saveFile() {
    this.leaderboard.write("", false);
    SLLNode<LeaderboardPlayer> node = getLeaderboard().getHead();
    while (node != null) {
      String text = node.info.getName() + "," + node.info.getScore()+"\n";
      this.leaderboard.write(text, true);
      node = node.next;
    }
  }
}
