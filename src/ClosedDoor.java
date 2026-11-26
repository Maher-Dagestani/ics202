public class ClosedDoor{

private Graph room;
private Item key;


public ClosedDoor(Graph room, Item key){
	this.room = room;
	this.key = key;
}


public Graph getRoom(){
	return this.room;
}



/*
public Graph openDoor(SLL<Item> inventory){
	for(Item i : inventory){
		if(i.isEqual(this.key)){
			return this.room;
		}
	}
	return null;
}
*/


@Override
public String toString(){
	return this.room.toString() + " needs key: " + key.toString();
}


}
