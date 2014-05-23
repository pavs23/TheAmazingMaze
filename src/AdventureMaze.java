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
    
    private static final int EASY_BOUNDARY = 70;
    private static final int MEDIUM_BOUNDARY = 150;
    private static final int HARD_BOUNDARY = 220;
    
    /**
     * Constructor of the class
     * @param x the number of tiles in x-direction (it should be greater than 0).
     * @param y the number of tiles in y-direction (it should be greater than 0).
     */
    public AdventureMaze(int x, int y) {
        int boundary = -1;
        if (x == Game.EASY_X && y == Game.EASY_Y) {
            boundary = EASY_BOUNDARY;
        } else if (x == Game.MEDIUM_X && y == Game.MEDIUM_Y) {
            boundary = MEDIUM_BOUNDARY;
        } else if (x == Game.HARD_X && y == Game.HARD_Y) {
            boundary = HARD_BOUNDARY;
        }
        
        maze = new MazeGenerator(x, y);
        int[][] mazeArray = maze.generateMazeArray();
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        // The starting and opening point must be valid at odd indexes.
        // The starting X is at the leftmost of maze, finish X is at rightmost of maze.
        int randomYStart = (int)Math.floor((Math.random()*Math.floor(yDimension/2)) - 0.01);
        int yStart = randomYStart*2 + 1;
        mazeArray[0][yStart] = Game.ROAD;
        Coordinate startCoor = new Coordinate(0, yStart);
        start = startCoor;    
        
        // Find the finishing point that is the furthest from the starting point.
        boolean coordinateFound = false;
        int i = 0;
        
        // Set the y Finish to be equal at 1 first.
        // Set the length to be equal 0 at first.
        int yFinish = 1;
        int length = 0;
        Coordinate finishCoor = new Coordinate(xDimension-1, yFinish);
        int currLength;
        int currYFinish;
        Coordinate currFinishCoor;
           
        while(!coordinateFound && i < y) {
            currYFinish = i*2 + 1;
            
            // Set current finish position.
            currFinishCoor = new Coordinate(xDimension-1, currYFinish);
            finish = currFinishCoor;
            
            ArrayList<Coordinate> pathToEnd = getHint(startCoor);
            currLength = pathToEnd.size();
            
            // Update the length and coordinate if the currLength is greater.
            if (currLength > length) {
                yFinish = currYFinish;
                length = currLength;
                finishCoor = currFinishCoor;
                setMazeArray(mazeArray);
                System.out.println("yoohoo");
            }
            
            // If the length is now greater than boundary, exit the loop.
            if (length > boundary) {
                coordinateFound = true;
            }    
            i++;
        }
        
        finish = finishCoor;
        mazeArray[xDimension-1][yFinish] = Game.ROAD;  
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
