import java.util.Collections;
import java.util.ArrayList;
 
/**
 * A class that generate a maze with depth first search recursive backtracking algorithm.
 * Generate a maze without starting & exit point.
 * @author floren
 *
 */
public class MazeGenerator {
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
		        tiles[i][j] = new Tile();
		    }
		}
		// Create the directions.
	    Direction north = new Direction("north", 0, -1);
	    Direction south = new Direction("south", 0, 1);
	    Direction west = new Direction("west", -1, 0);
	    Direction east = new Direction("east", 1, 0);
	    north.setOppositeDirection(south);
	    south.setOppositeDirection(north);
	    west.setOppositeDirection(east);
	    east.setOppositeDirection(west);
		directions.add(north);
		directions.add(south);
		directions.add(west);
		directions.add(east);
		createMaze(0, 0);
	}
	
	/**
	 * Generate the maze as an array. 0 = wall, 1 = path.
	 * @return
	 */
	public int[][] generateMazeArray() {
	    int xSize = 2*x + 1;
	    int ySize = 2*y + 1;
	    int[][] mazeArray = new int[xSize][ySize];
	    // Initialize the array to 0 (all walls).
	    for (int i = 0; i < xSize; i++) {
	        for (int j = 0; j < ySize; j++) {
	            mazeArray[i][j] = WALL;
	        }
	    }
	    for (int i = 0; i < x; i++) {
	        for (int j = 0; j < y; j++) {
	            int currX = 2*i + 1;
	            int currY = 2*j + 1;
	            // Set the tiles as a path.
	            mazeArray[currX][currY] = ROAD;
	            // If the tile South boundary true, then it is connected to the south tile.
	            if (tiles[i][j].getSouthValue() == true) {
	                mazeArray[currX][currY+1] = ROAD;
	            }
	            // If the tile East boundary true, then it is connected to the east tile.
                if (tiles[i][j].getEastValue() == true) {
                    mazeArray[currX+1][currY] = ROAD;
                }
	        }
	    }
	    return mazeArray;
	}
	
	/**
	 * Create a maze (collection of Tile objects).
	 * Tile has south, north, east, and west boundary. true in the boundary means there is no wall between 2 tiles.
	 * @param currX number of tiles in x dimension.
	 * @param currY number of tiles in y dimension.
	 */
	private void createMaze(int currX, int currY) {
	    // Randomized the direction that the tile will go.
		ArrayList<Direction> dirCopy = new ArrayList<Direction>(directions);
		Collections.shuffle(dirCopy);
		for (Direction dir : dirCopy) {
		    // Compute the next tile according to the Direction chosen.
			int nextX = currX + dir.getXDirection();
			int nextY = currY + dir.getYDirection();
			// Only execute if the next point is in the maze and has not been visited.
			if (isTileInMaze(nextX, x) && isTileInMaze(nextY,y)
			    && !tiles[nextX][nextY].isTileVisited()) {
			    // Remove the walls between the tiles.
				tiles[currX][currY].setBoundaryTrue(dir.getDirectionName());
				tiles[nextX][nextY].setBoundaryTrue(dir.getOpposite().getDirectionName());
				// Mark the tiles as visited.
				tiles[currX][currY].visited();
				tiles[nextX][nextY].visited();
				// Do the recursive call for the chosen neighbor.
				createMaze(nextX, nextY);
			}
		}
	}
 
	/**
	 * A helper method to verify whether the tile is in the maze or not.
	 * @param coordinate the x or y coordinate of the tile checked.
	 * @param boundary the x or y maximum of a tile.
	 * @return
	 */
	private boolean isTileInMaze(int coordinate, int boundary) {
	    boolean isInMaze = true;
	    if (coordinate < 0 || coordinate >= boundary) {
	        isInMaze = false;
	    }
		return isInMaze;
	}
	
	private final int ROAD = 1;
    private final int WALL = 0;
    
    private final int x;
    private final int y;
    private final Tile[][] tiles;
    private ArrayList<Direction> directions = new ArrayList<Direction>();
}