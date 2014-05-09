import java.util.ArrayList;

/**
 * An interface for game modes.
 * @author floren
 *
 */
public interface GameMode {
    /**
     * Get the array of 0s and 1s in the maze.
     * @return the array of 0s and 1s denoting walls and roads.
     */
    public int[][] getMazeArray();
    
    /**
     * Get hint for the maze.
     * @param currentPosition the current position of the player.
     * @return an ArrayList of Coordinate from currentPosition to a goal, not including current coordinate.
     */
    public ArrayList<Coordinate> getHint(Coordinate currentPosition);
}
