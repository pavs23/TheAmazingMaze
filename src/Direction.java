/**
 * A class to represent a direction.
 * @author floren
 *
 */
public class Direction {
    // Represents in which x-y direction the direction is.
    private int dx;
    private int dy;
    
    /**
     * Constructor of the class.
     * @param dx x component of the direction.
     * @param dy y component of the direction.
     */
    public Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
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
     * The opposite direction is the negative of both x & y component.
     * @return the Direction object that is the opposite of the direction.
     */
    public Direction getOpposite() {
        Direction opposite = new Direction(-getXDirection(), -getYDirection());
        return opposite;
    }
    
    /**
     * Two Direction objects are equal if the x and y direction of both objects are the same.
     * @param otherObject the other object to be compared with this Direction.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals (Object otherObject) {
        boolean isEqual = false;
        Direction other = (Direction) otherObject;
        if (other.getXDirection() == dx && other.getYDirection() == dy) {
            isEqual = true;
        }
        return isEqual;
    }
}
