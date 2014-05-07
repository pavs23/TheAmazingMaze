/**
 * Generate a maze with a starting and end point.
 * @author floren
 *
 */
public class Maze {
    /**
     * Constructor of the class
     * @param x the number of tiles in x-direction.
     * @param y the number of tiles in y-direction.
     */
    public Maze(int x, int y) {
        MazeGenerator maze = new MazeGenerator(x, y);
        mazeArray = maze.generateMazeArray();
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        // The starting and opening point must be valid at odd indexes.
        // The starting X is at the leftmost of maze, finish X is at rightmost of maze.
        int randomYStart = (int)Math.floor((Math.random()*Math.floor(yDimension/2)) - 0.01);
        int randomYFinish = (int)Math.floor((Math.random()*Math.floor(yDimension/2)) - 0.01);
        int yStart = randomYStart*2 + 1;
        int yFinish = randomYFinish*2 + 1;
        startX = 0;
        finishX = xDimension-1;
        startY = yStart;
        finishY = yFinish;
        mazeArray[startX][startY] = ROAD;
        mazeArray[finishX][finishY] = ROAD;
    }
    
    /**
     * Get the x position of starting point.
     * @return the x coordinate of starting point.
     */
    public int getStartX() {
        return startX;
    }
    
    /**
     * Get the y position of starting point.
     * @return the y coordinate of starting point.
     */
    public int getStartY() {
        return startY;
    }
    
    /**
     * Get the x position of finish point.
     * @return the x coordinate of finish point.
     */
    public int getFinishX() {
        return finishX;
    }
    
    /**
     * Get the y position of finish point.
     * @return the y coordinate of finish point.
     */
    public int getFinishY() {
        return finishY;
    }
    
    /**
     * Get the array of maze.
     * 0 representing path and 1 representing walls.
     * @return the 2 dimensional array of the maze.
     */
    public int[][] getMaze() {
        return mazeArray;
    }
    
    /**
     * Get whether the tile in the direction from current position is a path (not a wall).
     * @param currX x coordinate of current position.
     * @param currY y coordinate of current position.
     * @param direction the direction of tile from current position that would like to be examined.
     * @return true if it is a path(not a wall), false otherwise).
     */
    public boolean isPath(int currX, int currY, Direction direction) {
        boolean isNotWall = false;
        int nextX = currX;
        int nextY = currY;
        nextX += direction.getXDirection();
        nextY += direction.getYDirection();
        if (isPointInMaze(nextX, nextY)) {
            if (mazeArray[nextX][nextY] == ROAD) {
                isNotWall = true;
            }
        }
        return isNotWall;
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
    
    private int[][] mazeArray;
    private int startX;
    private int startY;
    private int finishX;
    private int finishY;
    
    private final int ROAD = 1;
}
