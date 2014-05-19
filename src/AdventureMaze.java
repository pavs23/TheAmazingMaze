import java.util.ArrayList;

/**
 * Generate a maze with a starting and end point.
 * @author floren
 *
 */
public class AdventureMaze extends GameModeImp {
    private Coordinate start;
    private Coordinate finish;
    private MazeGenerator maze;  
    
    /**
     * Constructor of the class
     * @param x the number of tiles in x-direction.
     * @param y the number of tiles in y-direction.
     */
    public AdventureMaze(int x, int y) {
        maze = new MazeGenerator(x, y);
        int[][] mazeArray = maze.generateMazeArray();
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
        mazeArray[0][yStart] = Game.ROAD;
        mazeArray[xDimension-1][yFinish] = Game.ROAD;
        setMazeArray(mazeArray);
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
     * Checks whether a game has finished (player is at finish point).
     * @param playerCoordinate the coordinate of current player.
     * @return whether the game is finished or not.
     */
    public boolean gameFinished(Coordinate playerCoordinate) {
        boolean isFinished = false;
        if (playerCoordinate.equals(finish)){
            isFinished = true;
        }
        return isFinished;
    }
}
