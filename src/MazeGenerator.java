import java.util.Collections;
import java.util.ArrayList;
 
/**
 * A class that generate a maze with depth first search algorithm.
 * Generate a maze without starting & exit point.
 * @author floren
 *
 */
public class MazeGenerator {
    private int x;
    private int y;
    private Tile[][] tiles;

    private int[][] maze = null;
    private boolean[][] visited;
    private ArrayList<Coordinate> path;
    private ArrayList<Direction> directions;
    
    /**
     * Constructor of the class.
     * @param x the number of road in x direction.
     * @param y the number of road in y direction.
     */
	public MazeGenerator(int x, int y) {
		this.x = x;
		this.y = y;
		tiles = new Tile[this.x][this.y];
		for (int i = 0; i < x; i++) {
		    for (int j = 0; j < y; j++) {
		        tiles[i][j] = new Tile(i, j);
		    }
		}
		// Initialize directions Array.
		directions = new ArrayList<Direction>();
		directions.add(Game.NORTH);
		directions.add(Game.SOUTH);
		directions.add(Game.WEST);
		directions.add(Game.EAST);
		createMaze(x/2, y/2);
	}
	
	/**
	 * Generate the maze as an array.
	 * @return the array of the maze.
	 */
	public int[][] generateMazeArray() {
	    if (maze == null) {
    	    int xSize = 2*x + 1;
    	    int ySize = 2*y + 1;
    	    int[][] mazeArray = new int[xSize][ySize];
    	    // Initialize the array to 0 (all walls).
    	    for (int i = 0; i < xSize; i++) {
    	        for (int j = 0; j < ySize; j++) {

    	            mazeArray[i][j] = Game.WALL;
    	        }
    	    }
    	    for (int i = 0; i < x; i++) {
    	        for (int j = 0; j < y; j++) {
    	            int currX = 2*i + 1;
    	            int currY = 2*j + 1;
    	            // Set the tiles as a path.

    	            mazeArray[currX][currY] = Game.ROAD;
    	            // If the tile South boundary true, then it is connected to the south tile.
    	            if (tiles[i][j].getSouthValue() == true) {
    	                mazeArray[currX][currY+1] = Game.ROAD;
    	            }
    	            // If the tile East boundary true, then it is connected to the east tile.
                    if (tiles[i][j].getEastValue() == true) {
                        mazeArray[currX+1][currY] = Game.ROAD;
                    }
    	        }
    	    }
    	    // Save the maze into the object.
    	    maze = mazeArray;
	    }
	    return maze;
	}
	
	/**
     * Find the path from a coordinate to the other.
     * This method has to be invoked after the method generateMazeArray().
     * @param from the Coordinate from.
     * @param to the Coordinate to.
     * @return an ArrayList of Coordinate of the path, in order, including the to but not from.
     * @return a null object if not valid (coordinate invalid or the method generateMazeArray has not been invoked.
     * @return empty array list if the path is not found or from = to.
     */
    public ArrayList<Coordinate> findPath(Coordinate from, Coordinate to) {
        int startX = from.getX();
        int startY = from.getY();
        int finishX = to.getX();
        int finishY = to.getY();
        if (maze != null && isCoordinateInMaze(startX, startY) && isCoordinateInMaze(finishX, finishY)) {  
            int width = maze.length;
            int height = maze[0].length;
            visited = new boolean[maze.length][maze[0].length];
            // Initialize visited to false.
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    visited[i][j] = false;
                }
            }
            path = new ArrayList<Coordinate>();
            boolean pathFound = recursivePath(startX, startY, finishX, finishY);
            if (pathFound) {
                // Remove the from.
                path.remove(0);
            }
            return path;
        } else {
            return null;
        }
    }
    
	/**
	 * Create a maze (collection of Tile objects).
	 * Tile has south, north, east, and west boundary. true in the boundary means there is no wall between 2 tiles.
	 * @param currX number of tiles in x dimension.
	 * @param currY number of tiles in y dimension.
	 */
	private void createMaze(int currX, int currY) {
	      tiles[currX][currY].visited();
	      ArrayList<Tile> tilesList = new ArrayList<Tile>();
	      tilesList.add(tiles[currX][currY]);

	      while (!tilesList.isEmpty()) {
	          Tile currTile;
	          int currSize = tilesList.size();
	          double random = Math.floor(Math.random() * currSize);
	          int randomIndex = (int)random;
	          // To help reduce long roads with short branches.
	          if (Math.random() * 10 < 2.5) {
	              currTile = tilesList.remove(randomIndex);
	          } else {
	              currTile = tilesList.remove(currSize - 1);
	          }
	          
	          int currTileX = currTile.getX();
	          int currTileY = currTile.getY();
	          
	          Tile successor = null;
	          Direction currDir = null;
	          
	          // Randomized the direction that the tile will go.
	          ArrayList<Direction> dirCopy = new ArrayList<Direction>(directions);
	          Collections.shuffle(dirCopy);
	          int i = 0;
	          boolean successorFound = false;
	         
	          while (!successorFound && i < dirCopy.size()) {
	              Direction dir = dirCopy.get(i);
	              int nextTileX = currTileX + dir.getXDirection();
                  int nextTileY = currTileY + dir.getYDirection();
                  if (isTileInMaze(nextTileX, nextTileY) && !tiles[nextTileX][nextTileY].isTileVisited()) {
                      successor = tiles[nextTileX][nextTileY];
                      currDir = dir;
                      successorFound = true;
                  }
	              i++;
	          }

	          if (successor != null) {
	              currTile.setBoundaryTrue(currDir);
	              successor.visited();
	              successor.setBoundaryTrue(currDir.getOpposite());
	              tilesList.add(currTile);
	              tilesList.add(successor);
	          }
	      }
	}
	
	/**
	 * A helper method to verify whether the tile is in the maze or not.
	 * @param x the x position of the tile.
	 * @param y the y position of the tile.
	 * @return true if the tile in maze, false otherwise.
	 */
	private boolean isTileInMaze(int x, int y) {
        boolean isInMaze = true;
        if (x < 0 || x >= this.x || y < 0 || y >= this.y) {
            isInMaze = false;
        }
        return isInMaze;
    }
	
	
	/**
	 * Helper function to know whether a coordinate is in the maze array.
	 * @param x the x coordinate.
	 * @param y the y coordinate.
	 * @return true if the coordinate in maze, false otherwise.
	 */
	private boolean isCoordinateInMaze(int x, int y) {
	    int width = maze.length;
        int height = maze[0].length;
        boolean inMaze = true;
        if (x < 0 || x >= width) {
            inMaze = false;
        } else if (y < 0 || y >= height) {
            inMaze = false;
        }
        return inMaze;
	}
	
	/**
	 * Recursive function to modify the path to the goal.
	 * @param currX current x-coordinate.
	 * @param currY current y-coordinate.
	 * @param goalX goal x-coordinate.
	 * @param goalY goal y-coordinate.
	 * @return true if the path found, false otherwise.
	 */
	private boolean recursivePath(int currX, int currY, int goalX, int goalY) {
	    if (currX == goalX && currY == goalY) {
	        // The goal is reached. Add it to the path and return true.
	        // Always add to the path in the beginning as it is recursive function.
	        path.add(0, new Coordinate(currX, currY));
	        return true;
	    }
	    // Wall = 0.

        if (visited[currX][currY] || maze[currX][currY] == Game.WALL) {
            // The coordinate has been visited, or it is a wall.
            return false;
        }
        // Mark the point as visited.
        visited[currX][currY] = true;
        // Check on each side of the maze.
        if (currX != 0 && recursivePath(currX-1, currY, goalX, goalY)) {
            // There is a left side of the current position.
            // The recursive function returns true, it means that current position is a part of the path.
            // Add the current coordinate to path.
            path.add(0, new Coordinate(currX, currY));
            return true;
        }
        if (currX != maze.length - 1 && recursivePath(currX+1, currY, goalX, goalY)) {
            // There is a right side of the current position.
            // The recursive function returns true, it means that current position is a part of the path.
            // Add the current coordinate to path.
            path.add(0, new Coordinate(currX, currY));
            return true;
        }
        
        if (currY != 0 && recursivePath(currX, currY-1, goalX, goalY)) {
            // There is a top side of the current position.
            // The recursive function returns true, it means that current position is a part of the path.
            // Add the current coordinate to path.
            path.add(0, new Coordinate(currX, currY));
            return true;
        }
        if (currY != maze[0].length - 1 && recursivePath(currX, currY+1, goalX, goalY)) {
            // There is a bottom side of the current position.
            // The recursive function returns true, it means that current position is a part of the path.
            // Add the current coordinate to path.
            path.add(0, new Coordinate(currX, currY));
            return true;
        }
        return false;
	}
}