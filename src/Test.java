public class Test {
	public static void main(String[] args) {

		//add more tests here:
		Graph a = new Graph("A");
		Graph b = new Graph("B");
		a.addOpenRoom(b);
		b.addOpenRoom(a);
		CurrentRoom room = new CurrentRoom(a);
		room.move(b);
		if(room.seeOpenRooms().find(a) == false){
			throw new AssertionError("move error");
		}
		Item key = new Item("k1", "key");
		room.addItemToRoom(key);
		if(room.seeItemsInRoom().find(key) == false){
			throw new AssertionError("addItemToRoom error");
		}
		room.takeItem(key);
		if(room.seeItemsInRoom().find(key)){
			throw new AssertionError("takeItem error");
		}

		Graph c = new Graph("C");
		Graph d = new Graph("D");
		c.addOpenRoom(d);
		d.addOpenRoom(c);
		CurrentRoom cdRoom = new CurrentRoom(c);
		Item doorKey = new Item("dk1", "door key");

		cdRoom.lockDoor(d, doorKey);
		if(c.getOpenDoors().find(d) || d.getOpenDoors().find(c)){
			throw new AssertionError("lock error: door still open");
		}
		if(c.getClosedDoors().size() != 1 || d.getClosedDoors().size() != 1){
			throw new AssertionError("lock error: door not closed on both rooms");
		}

		SLL<Item> inventory = new SLL<Item>();
		inventory.addToTail(doorKey);
		cdRoom.openDoor(c.getClosedDoors().get(0), inventory);
		if(!c.getOpenDoors().find(d) || !d.getOpenDoors().find(c)){
			throw new AssertionError("unlock error: door not open on both rooms");
		}
		if(!c.getClosedDoors().isEmpty() || !d.getClosedDoors().isEmpty()){
			throw new AssertionError("unlock error: door still closed on both rooms");
		}
		cdRoom.move(d);
		System.out.println("All tests passed");
	}
}
