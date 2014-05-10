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
     * @param direction the string representing the direction that will be set to true (north/south/west/east).
     */
    public void setBoundaryTrue (String direction) {
        if (direction.equalsIgnoreCase("north")) {
            north = true;
        } else if (direction.equalsIgnoreCase("south")) {
            south = true;
        } else if (direction.equalsIgnoreCase("west")) {
            west = true;
        } else if (direction.equalsIgnoreCase("east")) {
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
