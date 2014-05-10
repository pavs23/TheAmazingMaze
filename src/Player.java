/**
 * A class to represent the player in the maze.
 * @author floren
 *
 */
public class Player {
    private Coordinate coordinate;
    
    /**
     * Constructor of the class.
     * @param initialCoordinate the initial coordinate of player.
     */
    public Player(Coordinate initialCoordinate) {
        coordinate = initialCoordinate;
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
    
}
