import Player.Mission;

public class Starter{


	SLL<Graph> rooms = new SLL<Graph>();
	Queue<Mission> missions = new Queue<Mission>();
	Integer moves = null;
	CurrentRoom  currentRoom = null;
	int line = 0;
	Graph exit = null;


public Data start(String fileName){
try{
	FileReader f = new FileReader(fileName);
	while (f.hasNext()) {
		line ++;
		//this one gets the first word (ROOM, CONNECT, ITEM, MISSION, START, MOVES, EXIT)
		String token = f.next();


		if(token.equals("ROOM")){
			String room = f.next();
			if(tokenChecker(room)){
				throw new RuntimeException("error on line " + line + "no room is provided");
			}
			rooms.addToTail(new Graph(room));
		}

		else if(token.equals("CONNECT")){
			Item lock = null;
			Graph room1 = findRoom(f.next());
			Graph room2 = findRoom(f.next());
			if(tokenChecker(f.peek()) == false){
				lock = new Item(f.next());
			}

			//check both rooms acually exist
			if(room1 == null){
				throw new RuntimeException("in line " + line + "you entered the room " + room1.toString() + "which does not exist");
			}

			if(room1 == null){
                                throw new RuntimeException("in line " + line + "you entered the room " + room1.toString() + "which does not exist");
                        }


			//in case path is not locked
			if(lock == null){
				room1.addOpenRoom(room2);
				room2.addOpenRoom(room1);
			}
			//in case it is locked
			else{
				room1.addClosedRoom(new ClosedDoor(room2, lock));
                                room2.addClosedRoom(new ClosedDoor(room1, lock));
			}
		}

		else if(token.equals("ITEM")){
			//this creates an new item using id and name
			Item item = new Item(f.next(), f.next());

			Graph room = findRoom(f.next());
			if(room == null){
				throw new RuntimeException("in line " + line + " the room entered does not exist [" + room + "]");
			}
			room.addItem(item);
		}
		else if(token.equals("MISSION")){
			String typeToken = f.next();
			if(typeToken != "COLLECT" && typeToken != "REACH"){
				throw new RuntimeException("error on line " + line + ": mission must be COLLECT or REACH only, you entered: " + typeToken);
			}

			String id = f.next();
			if(tokenChecker(id)){
			throw new RuntimeException("error on line " + line + ": no mission id is provided");
			}


			Mission.TYPE type = Mission.TYPE.valueOf(typeToken);

			missions.enqueue(new Mission(type, id));

			}



		else if(token.equals("START")){
			Graph room = findRoom(f.next());
			if (room == null){
				throw new RuntimeException("error on line "+ line + "room: " + room + "does not exist bro!");
			}
			currentRoom = new CurrentRoom(room);
		}



                else if(token.equals("MOVES")){

			String movesToken = f.next();
			try{
				moves = Integer.parseInt(movesToken);
			}
			catch(NumberFormatException e){
				throw new RuntimeException("error on line " + line + ": MOVES must be a number, you entered: " + movesToken);
			}
			if(moves <= 0){
				throw new RuntimeException("error on line " + line + ": MOVES must be greater than 0");
			}
               	}

                else if(token.equals("EXIT")){
			Graph room = findRoom(f.next());
                        if (room == null){
                                throw new RuntimeException("error on line "+ line + "room: " + room + "does not exist bro!");
                        }
			exit = room; //used later to check that exit exists
                         Mission.TYPE type = Mission.TYPE.valueOf("REACH");
                         Mission exitMission = new Mission(type, room.toString());
			if(exitMission.isEqual(missions.peek()) == false){
				missions.enqueue(exitMission);
			}
                }
		else{
			throw new RuntimeException("in line " + line + ", " + token + " is not known command");
		}
	}

	//check first
	if(exit == null){
		throw new RuntimeException("you did not add an EXIT, you must add an exit at the end like [EXIT, any-room]");
	}
	if(currentRoom == null){
		throw new RuntimeException("you did not add an START, you must add an START room, for example START, any-room");
	}
	if(moves == null){
		throw new RuntimeException("you did not add an MOVES, you must add a specific number of MOVES, for example MOVES, 25");
	}

	//now send the data
	Data data = new Data(currentRoom, missions, moves);
	return data;
}
//to be safe
catch (Exception e) {
    System.err.println("Something went wrong: " + e.getMessage());
    e.printStackTrace();
    return null;
}

}



	//check if the token is valaid, return true if token is the first in line (ROOM, CONNECT, etc)
	private boolean tokenChecker(String token){

		//a short if statment
		if (token.equals("ROOM") || token.equals("CONNECT") || token.equals("ITEM")
    || token.equals("MISSION") || token.equals("START") || token.equals("MOVES")
    || token.equals("EXIT")) {
			return true;
		}
		return false;
	}


	private Graph findRoom(String roomString){
		Graph room = null;
		for(int i = 0; i < rooms.size(); i++){
			if(rooms.get(i).toString().equals(roomString)){
				room = rooms.get(i);
			}
		}
		return room;
	}

}
