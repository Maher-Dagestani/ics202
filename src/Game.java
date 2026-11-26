public class Game {
  public enum STATE {
    RUNNING, WON, LOSE
  }

  private STATE state;

  public Game() {
    this.state = STATE.RUNNING;
  }

  public void init() {

  }
}

