public class Graph {
	private SLL<Graph> openDoors = new SLL<Graph>();
	private SLL<ClosedDoor> closedDoors = new SLL<ClosedDoor>();
	private String roomName;
	private SLL<Item> items = new SLL<Item>();


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
	public void takeItem(Item item){
		if(items.find(item)){
			items.delete(item);
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
		ClosedDoor i;
                for(int iterator = 0; iterator < closedDoors.size(); iterator++){
			i = closedDoors.get(iterator);
			if(i.getRoom() == room){
				closedDoors.delete(i);
				return;
			}
		}
		throw new RuntimeException("this room can't be removed");
        }

	public void openDoor(ClosedDoor closedDoor, SLL<Item> inventory){
		Graph openDoor = closedDoor.openDoor(inventory);
		if(openDoor != null){
			this.addOpenRoom(openDoor);
			this.closedDoors.delete(closedDoor);
			openDoor.addOpenRoom(this);
			openDoor.removeClosedDoor(this);
			return;
		}
		throw new RuntimeException("door can't be opened!");
	}



	public void closeDoor(Graph room, Item key){
		if(openDoors.find(room)){
			openDoors.delete(room);
			ClosedDoor closedDoor1 = new ClosedDoor(room, key);
			closedDoors.addToTail(closedDoor1);

			ClosedDoor closedDoor2 = new ClosedDoor(this, key);
			room.addClosedRoom(closedDoor2);
			room.removeOpenDoor(this);
		}
		else{
			throw new RuntimeException("door can't be closed!");
		}
	}


	@Override
	public String toString(){
		return this.roomName;
	}
}
