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
    
    /**
     * Get the starting coordinate of the player.
     * @return the Coordinate of the starting point.
     */
    public Coordinate getStartCoordinate();
    
    /**
     * Get whether the tile in the direction from current position is a path (not a wall).
     * @param currCoordinate coordinate of current position.
     * @param direction the direction of tile from current position that would like to be examined.
     * @return true if it is a path(not a wall), false otherwise).
     */
    public boolean isPath(Coordinate currCoordinate, Direction direction);
    
    /**
     * Checks whether a game has finished
     * @param playerCoordinate the coordinate of current player.
     * @return whether the game is finished or not
     */
    public boolean gameFinished(Coordinate playerCoordinate);
}
