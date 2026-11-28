package game;

import DS.Queue;
import Player.Mission;
import objects.CurrentRoom;

public class Data{
	CurrentRoom currentroom;
	Queue<Mission> missions;
	int moves;




	public Data(CurrentRoom currentroom, Queue<Mission> missions, int moves) {
        	this.currentroom = currentroom;
        	this.missions = missions;
       		this.moves = moves;
  	 }

    	public CurrentRoom getCurrentroom() {
        	return currentroom;
    	}

    	public Queue<Mission> getMissions() {
        	return missions;
    	}

    	public int getMoves() {
        	return moves;
    	}



}
