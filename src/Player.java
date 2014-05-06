/**
 * A class to represent the player in the maze.
 * @author floren
 *
 */
public class Player {
    /**
     * Constructor of the class.
     * @param initialX initial x coordinate of player.
     * @param initialY initial y coordinate of player.
     */
    public Player(int initialX, int initialY) {
        x = initialX;
        y = initialY;
    }
    
    /**
     * Get the X position of the player.
     * @return the x position.
     */
    public int getXPosition() {
        return x;
    }
    
    /**
     * Get the Y position of the player.
     * @return the y position.
     */
    public int getYPosition() {
        return y;
    }
    
    /**
     * Set the current X position of the player.
     * @param currX the current X position of player.
     */
    public void setXPosition(int currX) {
        x = currX;
    }
    
    /**
     * Set the current Y position of the player.
     * @param currY the current Y position of player.
     */
    public void setYPosition(int currY) {
        y = currY;
    }
    
    private int x;
    private int y;
}
