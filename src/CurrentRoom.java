public class CurrentRoom{


Graph currentRoom;



public CurrentRoom(Graph room){
	this.currentRoom = room;
}




public SLL<Graph> seeOpenRooms(){
	return this.currentRoom.getOpenDoors();
}

public SLL<ClosedDoors> seeClosedRooms(){
        return this.currentRoom.getClosedDoors();
}

public move(Graph room){
	if(currentRoom.getOpenDoors().find(room)){
		currentRoom = room;
	}
	else{
		throw new RuntimeError("room does not exist!");
	}
}
public move(ClosedDoor room; 



}
