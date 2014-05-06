/**
 * A class to represent an integer coordinate (x,y) of a maze.
 * @author floren
 *
 */
public class Coordinate {
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
    private int x;
    private int y;
}
