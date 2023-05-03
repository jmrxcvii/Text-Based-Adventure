/**
 * class that creates a player object to be used for storeing player infomation
 * @author John Rosso
 * @version 1.0
 */
public class Player {
    // data members that hold player info 
    private int number;
    private Room currentRoom;
    private boolean toolsCollected = false;
    private Part lastMachinePartCollcted = new Part(0);
    /**
     * constructor that sets the players number for debugging and sets the players
     * current room
     * @param playerNumber debugging use
     * @param room room object of current room player is in
     */
    public Player(int playerNumber, Room room) {
        number = playerNumber;
        currentRoom = room; 
    }
    /**
     * getter method for player number
     * @return int
     */
    public int getPlayerNumber() {
        return number;
    }
    /**
     * getter method for current room object
     * @return Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    /**
     * getter method for last part collected object
     * @return Part
     */
    public Part getLastMachinePartColleted() {
        return lastMachinePartCollcted;
    }
    /**
     * getter method for toolsCollected variable 
     * @return boolean
     */
    public boolean hasTools() {
        return toolsCollected;
    }
    /**
     * method that trys to move the player in desierd direction
     * @param direction value of direction player wats to move
     * @return String
     */
    public String move(int direction) {
        // if door exist in attemped direct the players moves through door
        if (currentRoom.getDoor(direction) != null) {
            currentRoom = currentRoom.getDoor(direction);
            return currentRoom.printMessage();
        }
        // other wise user is told no door exists
        else {
            return "No door in this direction";
        }
    }
    /**
     * method that checks if the user is able to collect a part based on if room
     * has a part,  user doesn't already have the part, and part is next in order.
     * @return String
     */
    public String collectPart() {
        // chcking if room has a part
        if (!currentRoom.hasPart()) {
            return "Room does not have machine parts";
        }
        // uses methods inside RoomWith MachinePart to determin if part order is right
        else if (currentRoom.collectPart(this) == null) {
            if (currentRoom.getMachinePart().getPartNumber() <= lastMachinePartCollcted.getPartNumber()) {
                return "Part already collected";
            }
            else {
                return "Parts must be collcted in order";
            }
        }
        // checking if player already has part
        else if (currentRoom.getMachinePart().getPartNumber() <= lastMachinePartCollcted.getPartNumber()) {
            return "Part already collected";
        }
        // if all other checks fail then gives user current room part
        else {
            lastMachinePartCollcted = currentRoom.collectPart(this);
            return "You have successfully collected part " + lastMachinePartCollcted.getPartNumber();
        }
    }
    /**
     * method that allows user to collect tools if the current room has tools
     * and the user doesn't already have tools
     * @return String
     */
    public String collectTools() {
        // checking if room has tools
        if (!currentRoom.hasTools()) {
            return "Room does not have tools";
        }
        // checking if player already has tools
        else if (toolsCollected == true) {
            return "Tools already collected";
        }
        // if all other checks fail then tools are collected
        else {
            toolsCollected = true;
            return "You have successfully collected tools";
        }
    }
    /**
     * method that allows use to build machine and win game if all parts are 
     * collected, tools are collected, and player is in the workshop room
     * @return String
     */
    public String build() {
        // check if player is in the workshop room
        if (!currentRoom.isWorkshop()) {
            return "You are not in the workshop";
        }
        // check if the player has the tools
        else if (toolsCollected == false) {
            return "You don't have the tools";
        }
        // check if the player collected all the parts
        else if (!lastMachinePartCollcted.isLastPart()) {
            return "You don't have all the parts";
        }
        // if all win conditions are met controlls are disabled and the player wins!
        else {
            return "You have built the portal, you may now escape!";
        }
    }
}