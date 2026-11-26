public class CurrentRoom{
Graph currentRoom;



/*
please read this:
to use my part of code, all u need is to use only this class (hopefully)
this class tracks the player current room.

so functions you do have are:

seeOpenRooms() that return an SLL of class Graph
seeClosedRooms() that return an SLL of class ClosedDoor

	for each class of the above you can use toString to see the room name

-------------------------------------------------------------------

to move the player to an open room, just use:
	move(Graph room);
		return type: void


to open a room, use openDoor(ClosedDoor room, Item key);
	this automaticly move the room to become open, and now user can go there using normal move();
		this should return void, and may raise RuntimeException if room or key dont exist
-------------------------------------------------------------------
to see the items in the current room just use:
	SeeItemsInRoom()
		return type: void

to take an item use:
	TakeItem(Item item);
		this may raise an RunTImeException
		return type void (if no exception raised, it ran sucsessfully)

-------------------------------------------------------------------
for undo:

to undo the move just move the player again in the oposite direction, soo in the queue u need to save
the room which the player came from
	A ---> B | just put A in queue, and if player undo we move the player to A


to undo item pick use AddItemToRoom(Item item), so in the queue u need to keep the items the user picked up and
put them in the queue (example):
	room A
	Item key
	room B
will result in:
	move(A);
	addItemToRoom(key);
	move(B)
		NOTE: do not forgot u need to remove the item from user inventory too

to undo unlocking doors: use lockDoor(room, key), so in the queue keep track of doors that are getting unlocked

(example)
		A ---> C --/unlock/--> B (item key2)
	item key
	Room C
	unlock room B using key1
	room A

will result in:
	addItemToRoom(key1)
	move(C)
	lockDoor(B, key1)
	move(A)
*/



public CurrentRoom(Graph room){
	this.currentRoom = room;
}




public SLL<Graph> seeOpenRooms(){
	return this.currentRoom.getOpenDoors();
}


public SLL<ClosedDoor> seeClosedRooms(){
        return this.currentRoom.getClosedDoors();
}

public void move(Graph room){
	if(currentRoom.getOpenDoors().find(room)){
		currentRoom = room;
	}
	else{
		throw new RuntimeException("room does not exist!");
	}
}


/*
public void openDoor(ClosedDoor room, SLL<Item> inventory){
	if(currentRoom.getClosedDoors().find(room)){
		currentRoom.openDoor(room, inventory);
		return;
	}
	throw new RuntimeException("ROOM DOES NOT EXIST");

}
*/
public SLL<Item> seeItemsInRoom(){
	return currentRoom.seeItemsInRoom();
	}

	public void addItemToRoom(Item item){
		currentRoom.addItem(item);
	}

	public void takeItem(Item item){
		currentRoom.takeItem(item);
	}

	public void lockDoor(Graph room, Item key){
		currentRoom.closeDoor(room, key);
	}


}
