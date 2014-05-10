import java.util.ArrayList;

/**
 * Generate a maze with a starting and end point.
 * @author floren
 *
 */
public class Maze implements GameMode {
    private int[][] mazeArray;
    private Coordinate start;
    private Coordinate finish;
    private MazeGenerator maze;  
    private final int ROAD = 1;
    
    /**
     * Constructor of the class
     * @param x the number of tiles in x-direction.
     * @param y the number of tiles in y-direction.
     */
    public Maze(int x, int y) {
        maze = new MazeGenerator(x, y);
        mazeArray = maze.generateMazeArray();
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        // The starting and opening point must be valid at odd indexes.
        // The starting X is at the leftmost of maze, finish X is at rightmost of maze.
        int randomYStart = (int)Math.floor((Math.random()*Math.floor(yDimension/2)) - 0.01);
        int randomYFinish = (int)Math.floor((Math.random()*Math.floor(yDimension/2)) - 0.01);
        int yStart = randomYStart*2 + 1;
        int yFinish = randomYFinish*2 + 1;
        Coordinate startCoor = new Coordinate(0, yStart);
        Coordinate finishCoor = new Coordinate(xDimension-1, yFinish);
        start = startCoor;
        finish = finishCoor;
        mazeArray[0][yStart] = ROAD;
        mazeArray[xDimension-1][yFinish] = ROAD;
    }
    
    /**
     * Get the position of starting point.
     * @return the coordinate of starting point.
     */
    public Coordinate getStartCoordinate() {
        return start;
    }
    
    /**
     * Get the position of finish point.
     * @return the coordinate of finish point.
     */
    public Coordinate getFinishCoordinate() {
        return finish;
    }
    
    
    /**
     * Get the array of maze.
     * 0 representing path and 1 representing walls.
     * @return the 2 dimensional array of the maze.
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
            if (mazeArray[nextX][nextY] == ROAD) {
                isNotWall = true;
            }
        }
        return isNotWall;
    }
    
    /**
     * Find the path from current position to the goal in an open ended maze.
     * @param curr the current position.
     * @return an ArrayList of Coordinate path, not including the current point, until the goal.
     */
    public ArrayList<Coordinate> getHint(Coordinate curr) {    
        Coordinate currPoint;
        int currX = curr.getX();
        int currY = curr.getY();
        int finishX = finish.getX();
        int finishY = finish.getY();
        Coordinate nearFinishPoint = new Coordinate(finishX-1, finishY);
        Coordinate finishPoint = new Coordinate(finishX, finishY);
        // Check if the current point is starting point (currX = 0).
        if (currX == 0) {
            // If it is at starting point, find path from the right of it.
            currPoint = new Coordinate(currX+1, currY);
        } else {
            currPoint = new Coordinate(currX, currY);
        }
        ArrayList<Coordinate> path = maze.findPath(currPoint, nearFinishPoint);
        path.add(finishPoint);
        // If at the start, add the first point (current currPoint) to the beginning of path.
        if (currX == 0) {
            path.add(0, currPoint);
        }
        return path;
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
