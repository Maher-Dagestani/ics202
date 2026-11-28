import DS.Queue;
import Player.Mission;
import Player.Player;
import game.Data;
import game.Starter;
import objects.CurrentRoom;
import objects.Item;
import ui.UI;

public class Main {
	public static void main(String[] args) throws Exception {



		//take this from the user input at the start as the ducument says, so change it in the future
		String fileName = "castle.txt";


		Data data = null;
		try{
			//this starts and load the data from the .txt file
			Starter starter = new Starter();
			data = starter.start(fileName);
		}
		//integrate this with the ui [currently its a normal print] this should not crash our program
		catch(Exception e){
			System.out.println(e.getMessage());
		}

		//use this to move the player in the map, take items, and more.
		//IMPORTANT: please read the comment in CurrentRoom.java class
		CurrentRoom currentRoom = data.getCurrentroom();

		//this is a Queue that includes all the tasks the player has. i used your mession class
		//dequeue and u get the messions from first to last
		Queue<Mission> missions = data.getMissions();

		//this is the moves the player has
		int moves = data.getMoves();

    Player player = new Player("Mansour", currentRoom, missions, moves);
    player.collectItem(new Item("1", "Torch"));
    player.collectItem(new Item("2", "Key"));
    player.collectItem(new Item("3", "Torch 2"));
    player.collectItem(new Item("4", "Key 2"));
    UI ui = new UI(player);
    ui.loop();
	}
}
