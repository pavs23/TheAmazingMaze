/**
 * A class to represent the player in the maze.
 * @author floren
 *
 */
public class Player {
    private Coordinate coordinate;
    private String name;
    
    /**
     * Constructor of the class.
     * @param initialCoordinate the initial coordinate of player.
     * @param name the name of the player.
     */
    public Player(Coordinate initialCoordinate, String name) {
        coordinate = initialCoordinate;
        this.name = name;
    }
    
    /**
     * Get the coordinate of the player.
     * @return the coordinate position.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
    
    
    /**
     * Set the current position of the player.
     * @param currCoordinate the current position of player.
     */
    public void setCoordinate(Coordinate currCoordinate) {
        coordinate = currCoordinate;
    }
    
    /**
     * Get the player's name.
     * @return the player's name.
     */
    public String getName() {
        return name;
    }
}
