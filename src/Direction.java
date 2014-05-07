/**
 * A class to represent a direction.
 * @author floren
 *
 */
public class Direction {
    /**
     * Constructor of the class.
     * @param directionName the name of direction.
     * @param dx x component of the direction.
     * @param dy y component of the direction.
     */
    public Direction(String directionName, int dx, int dy) {
        this.directionName = directionName;
        this.dx = dx;
        this.dy = dy;
    }
    
    /**
     * Get the name of the direction.
     * @return the String that represents the name of the direction.
     */
    public String getDirectionName() {
        return directionName;
    }
    
    /**
     * Get the delta x-component of the direction.
     * @return the delta x-component of direction.
     */
    public int getXDirection() {
        return dx;
    }
    
    /**
     * Get the delta y-component of the direction.
     * @return the delta y-component of direction.
     */
    public int getYDirection() {
        return dy;
    }
    
    /**
     * Get the opposite direction of the current direction.
     * @return the Direction object that is the opposite of the direction.
     */
    public Direction getOpposite() {
        return opposite;
    }
    
    /**
     * Set the opposite direction.
     * @param opposite the Direction object representing opposite direction.
     */
    public void setOppositeDirection (Direction opposite) {
        this.opposite = opposite;
    }
    
    // Represents the name of the direction.
    private String directionName;
    // Represents in which x-y direction the direction is.
    private int dx;
    private int dy;
    // Represents the opposite direction of the current direction.
    private Direction opposite = null;
}
