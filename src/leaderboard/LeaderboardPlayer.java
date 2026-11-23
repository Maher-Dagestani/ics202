package leaderboard;

public class LeaderboardPlayer implements Comparable<LeaderboardPlayer> {
  private String name;
  private int score;

  public LeaderboardPlayer(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() { return this.name; }
  public int getScore() { return this.score; }

  public void setScore(int score) { this.score = score; }

  public boolean equals(LeaderboardPlayer player) {
    return this.name.equals(player.getName());
  }

  @Override 
  public int compareTo(LeaderboardPlayer player) {
    return Integer.compare(player.getScore(), this.score);
  }
}
