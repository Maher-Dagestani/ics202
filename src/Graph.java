public class Graph {
	private SLL<Graph> openDoors = new SLL<Graph>();
	private SLL<ClosedDoor> closedDoors = new SLL<ClosedDoor>();
	private String roomName;


	public Graph(String roomName){
		this.roomName = roomName;
	}
	public void addOpenRoom(Graph room){
		openDoors.addToTail(room);
	}
	public void addClosedRoom(ClosedDoor room){
		closedDoors.addToTail(room);
	}
	public SLL<Graph> getOpenDoors(){
		return this.openDoors;
	}
        public SLL<ClosedDoor> getClosedDoors(){
                return this.closedDoors;
        }



	Public void openDoor(Item key, ClosedDoor closedDoor){
		Graph openDoor = closedDoor.openDoor(key);
		if(openDoor != null){
			this.addOpenRoom(openDoor);
			this.closedDoors.delete(closedDoor);
		}
		else{
			    throw new RuntimeError("room cannot be opened!!");
		}


	@Override
	Public String toString(){
		return this.roomName;
	}
}
