/**
 * A class to represent a tile in the maze background.
 * @author floren
 *
 */
public class Tile {
    // Represents the boundaries of the tile.
    // Initially it is false, but can be set to true if the wall between the boundaries is "destroyed".
    private boolean north = false;
    private boolean south = false;
    private boolean west = false;
    private boolean east = false;
    
    // Represents whether the tile is visited or not.
    private boolean isVisited = false;
    
    // Represents the position of the tile.
    private int x;
    private int y;
    
    /**
     * Create a Tile.
     * @param x the x coordinate of the tile.
     * @param y the y coordinate of the tile.
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get X value of the tile.
     * @return the x value.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get Y value of the tile.
     * @return the y value.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Get the value of the North boundary.
     * @return true if the North boundary is open, false otherwise.
     */
    public boolean getNorthValue() {
        return north;
    }
    
    /**
     * Get the value of the South boundary.
     * @return true if the South boundary is open, false otherwise.
     */
    public boolean getSouthValue() {
        return south;
    }
    
    /**
     * Get the value of the West boundary.
     * @return true if the West boundary is open, false otherwise.
     */
    public boolean getWestValue() {
        return west;
    }
    
    /**
     * Get the value of the East boundary.
     * @return true if the East boundary is open, false otherwise.
     */
    public boolean getEastValue() {
        return east;
    }
    
    /**
     * Get information whether the tile has been visited or not.
     * @return true if the tile has been visited, false otherwise.
     */
    public boolean isTileVisited() {
        return isVisited;
    }
    
    /**
     * Set the boolean value of boundary to true.
     * @param direction the Direction representing the direction that will be set to true.
     */
    public void setBoundaryTrue (Direction direction) {
        if (direction.equals(Game.NORTH)) {
            north = true;
        } else if (direction.equals(Game.SOUTH)) {
            south = true;
        } else if (direction.equals(Game.WEST)) {
            west = true;
        } else if (direction.equals(Game.EAST)) {
            east = true;
        }
    }
    
    /**
     * Mark the tile as visited.
     */
    public void visited() {
        isVisited = true;
    }
}
