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

	}
}
