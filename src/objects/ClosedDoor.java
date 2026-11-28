package objects;

import DS.Graph;
import DS.SLL;

public class ClosedDoor implements Comparable<ClosedDoor>{

private Graph room;
private Item key;


public ClosedDoor(Graph room, Item key){
	this.room = room;
	this.key = key;
}


public Graph getRoom(){
	return this.room;
}



public Graph openDoor(SLL<Item> inventory){
	for(int i = 0; i < inventory.size(); i++){
		Item item = inventory.get(i);
		if(item != null && item.isEqual(this.key)){
			return this.room;
		}
	}
	return null;
}


@Override
public int compareTo(ClosedDoor other){
	return this.room.toString().compareTo(other.room.toString());
}


@Override
public String toString(){
	return this.room.toString() + " needs key: " + key.toString();
}


}
