public class Graph {
	private SLL<Graph> openDoors = new SLL<Graph>();
	private SLL<ClosedDoor> closedDoors = new SLL<ClosedDoor>();
	private String roomName;
	private SLL<Item> items;


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
	public SLL<Item> seeItemsInRoom(){
		return this.items;
	}
	public takeItem(Item item){
		if(items.find(item){
			return;
		}
		throw new RuntimeException("this item does not exist in this room!");
	}
	public void addItem(Item item){
		this.items.addToTail(item);
	}
	public void removeOpenDoor(Graph room){
		this.openDoors.delete(room);
	}
        public void removeClosedDoor(Graph room){
                this.closedDoors.delete(room);
        }

	public void openDoorClosedDoor closedDoor, (SLL<Item> inventory){
		for(i:inventory){
			Graph openDoor = closedDoor.openDoor(key);
			if(openDoor != null){
				this.addOpenRoom(openDoor);
				this.closedDoors.delete(closedDoor);
			}
		}
		throw new RuntimeError ("door cant be opened!");
	}


	//TODO: finish
	public void closeDoor(Graph room, Item key){
		if(openDoors.find(room)){
			openDoors.delete(room);
			ClosedDoor closedDoor1 = new ClosedDoor(room, key);
			closedDoors.addToTail(closedDoor1);

			ClosedDoor closedDoor2 = new ClosedDoor(this, key);
			



	@Override
	public String toString(){
		return this.roomName;
	}
}
