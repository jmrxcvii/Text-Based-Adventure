/**
 * This class holds the whole game and its components (the two players and 10 rooms). 
 * This class should be instantiated in the main application class 
 * @author John Rosso
 * @version 1.0
 */
public class Game {
	private Player[] players;
	private Room[] rooms;
	private Player currentPlayer;
	/**
	 * constructor that sets the player and rooms array while adding room objects
	 * to the rooms array. also uses setUpDoors method to create door map
	 * @throws Exception
	 */
	public Game() throws Exception {
		// setting up arrays
		players = new Player[2];
		rooms = new Room[10];
		// adding room objects to rooms array
		rooms[0] = new Room(1);
		rooms[1] = new RoomWithMachinePart(2, new Part(3));
		rooms[2] = new RoomWithMachinePart(3, new Part(1));
		rooms[3] = new Room(4);
		rooms[4] = new RoomWithMachinePart(5, new Part(2));
		rooms[5] = new RoomWithMachinePart(6, new Part(4));
		rooms[6] = new Room(7);
		rooms[7] = new RoomWithTools(8);
		rooms[8] = new Room(9);
		rooms[9] = new Workshop(10);
		// setting door map 
		this.setUpDoors();
	}
	   /**
	    * Assuming that Rooms has been initialized in the constructor 
	    * to hold 10 objects of type Room or a subclass of Room, this method 
	    * sets up the doors between the rooms, as described in the map
	    * @return void
	    * @throws Exception if inconsistencies found by setDoor
	    */
	private void setUpDoors() throws Exception {
		rooms[0].setDoor(Direction.up, rooms[3]);
		rooms[0].setDoor(Direction.left, rooms[8]);
		rooms[1].setDoor(Direction.up, rooms[4]);
		rooms[1].setDoor(Direction.down, rooms[7]);
		rooms[1].setDoor(Direction.right, rooms[9]);
		rooms[2].setDoor(Direction.down, rooms[8]);
		rooms[3].setDoor(Direction.down, rooms[0]);
		rooms[3].setDoor(Direction.right, rooms[4]);
		rooms[4].setDoor(Direction.down, rooms[1]);
		rooms[4].setDoor(Direction.right, rooms[5]);
		rooms[4].setDoor(Direction.left, rooms[3]);
		rooms[5].setDoor(Direction.down, rooms[9]);
		rooms[5].setDoor(Direction.left, rooms[4]);
		rooms[6].setDoor(Direction.up, rooms[8]);
		rooms[6].setDoor(Direction.right, rooms[7]);
		rooms[7].setDoor(Direction.up, rooms[1]);
		rooms[7].setDoor(Direction.left, rooms[6]);
		rooms[8].setDoor(Direction.up, rooms[2]);
		rooms[8].setDoor(Direction.down, rooms[6]);
		rooms[8].setDoor(Direction.right, rooms[0]);
		rooms[9].setDoor(Direction.up, rooms[5]);
		rooms[9].setDoor(Direction.left, rooms[1]);
	}	
	/**
	 * method that starts game by createing player objects and store them in 
	 * players array also setting currentPlayer to first player
	 * @return void
	 */
	public void InitGame() {
		// creating player and starting them in room 1
		players[0] = new Player(0, rooms[0]);
		players[1] = new Player(1, rooms[0]);
		currentPlayer = players[0];
	}
	/**
	 * getter method for currentPlayer object
	 * @return Player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * method that switches the current playing player
	 * @return void
	 */
	public void switchPlayer() {
		// if currently player 1 then switch to player two and vice versa
		if (currentPlayer == players[0]) {
			currentPlayer = players[1];
		}
		else if (currentPlayer == players[1]) {
			currentPlayer = players[0];
		}
	}
}