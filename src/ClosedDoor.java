public class ClosedDoor{

private Graph room;
private Item key;


public ClosedDoor(Graph room, Item key){
	this.room = room;
	this.key = key;
}





public Graph openDoor(SLL<Item> inventory){
	for(Item i : inventory){
		if(i.isEaual(this.key)){
			return this.room;
		}
	}
	throw new RuntimeError("You do not have the key to open this door");
}


@Override
Public String toString(){
	return this.room.toString() + " needs key: " + key.toString();
}


}
