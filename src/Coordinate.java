/**
 * A class to represent an integer coordinate (x,y) of a maze.
 * @author floren
 *
 */
public class Coordinate {
    private int x;
    private int y;
    
    /**
     * Constructor of the class.
     * @param x the x - integer coordinate.
     * @param y the y - integer coordinate.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the x value of the coordinate.
     * @return the x coordinate.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get the y value of the coordinate
     * @return the y coordinate.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Check the equality of 2 Coordinate objects.
     * @param otherObject the other object that will be compared to this Coordinate.
     * @return true if both objects have the same X and Y, false otherwise.
     */
    @Override
    public boolean equals(Object otherObject) {
        boolean equal = true;
        Coordinate other = (Coordinate) otherObject;
        if (this.x != other.getX()) {
            equal = false;
        } else if (this.y != other.getY()) {
            equal = false;
        }
        return equal;
    }
}
