/**
 * An abstract class for game modes.
 * @author floren
 *
 */
public abstract class GameModeImp implements GameMode {
    private int[][] mazeArray;
    
    /**
     * Get the array of 0s and 1s in the maze.
     * @return the array of 0s and 1s denoting walls and roads.
     */
    public int[][] getMazeArray() {
        return mazeArray;
    }
    
    /**
     * Get whether the tile in the direction from current position is a path (not a wall).
     * @param currCoordinate coordinate of current position.
     * @param direction the direction of tile from current position that would like to be examined.
     * @return true if it is a path(not a wall), false otherwise).
     */
    public boolean isPath(Coordinate currCoordinate, Direction direction) {
        boolean isNotWall = false;
        int nextX = currCoordinate.getX();
        int nextY = currCoordinate.getY();
        nextX += direction.getXDirection();
        nextY += direction.getYDirection();
        if (isPointInMaze(nextX, nextY)) {

            if (mazeArray[nextX][nextY] == Game.ROAD) {
                isNotWall = true;
            }
        }
        return isNotWall;
    }
    
    /**
     * Set the mazeArray of the class.
     * @param array the array representing the maze.
     */
    protected void setMazeArray(int[][] array) {
        mazeArray = array;
    }
    
    /**
     * Check whether a point is in the maze array.
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @return true if it is in the maze array, false otherwise.
     */
    private boolean isPointInMaze(int x, int y) {
        boolean isInMaze = true;
        if (x < 0 || x >= mazeArray.length) {
            isInMaze = false;
        } else if (y < 0 || y >= mazeArray[0].length) {
            isInMaze = false;
        }
        return isInMaze;
    }
}
