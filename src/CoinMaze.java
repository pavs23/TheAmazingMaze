import java.util.ArrayList;
import java.util.Random;

/**
 * A class that generates and monitors the state of a coin mode maze
 * @author Bronte Kalebic
 *
 */
public class CoinMaze extends GameModeImp implements Cloneable{

	private ArrayList<Coordinate> coinLocations;
	private MazeGenerator maze;
	
	private final int TOTAL_COIN_NUM = 10;
	private final Coordinate startCoordinate = new Coordinate(1,1);
	
	
	/**
	 * A constructor that generates a coin maze
	 * @param sizeX is size of grid horizontally
	 * @param sizeY is size of grid vertically
	 */
	public CoinMaze(int sizeX, int sizeY){
		coinLocations = new ArrayList<Coordinate>();
		maze = new MazeGenerator(sizeX,sizeY);
		setMazeArray(maze.generateMazeArray());
		
		coinCoordinateGenerator();
	}
	
	/**
	 * Checks whether a game has finished (if all coins have been found)
	 * @param playerCoordinate the coordinate of current player.
	 * @return whether the game is finished or not
	 */
	public boolean gameFinished(Coordinate playerCoordinate){
		if (coinLocations.isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Remove a coin.
	 * @param coinCoordinate the coordinate of the coin that wants to be removed.
	 */
	public void removeCoin(Coordinate coinCoordinate) {
	    coinLocations.remove(coinCoordinate);
	}
	
	/**
	 * Iterates through location of coins to see if the player has
	 * found a coin (if the location of the player is the same as the
	 * location of a coin)
	 * @param playerCoordinate is the coordinate of the player
	 * @return if a coin has been found
	 */
	public boolean coinFound(Coordinate playerCoordinate){
		int i = 0;
		int numCoins = coinLocations.size();
		Coordinate currentCoordinate; //iterator
		
		while (i < numCoins){
			currentCoordinate = coinLocations.get(i);
			if (currentCoordinate.equals(playerCoordinate)){
				return true;
			}
			i++;
		}
		
		return false;
	}
	
	/**
	 * Generates a hint path to a random coin in the maze
	 * @param playerCoordinate is the current position of the player
	 * @return an array list of the coordinates of the path to a coin closest to the player, excluding the coin position.
	 */
	public ArrayList<Coordinate> getHint(Coordinate playerCoordinate){
	    ArrayList<Coordinate> path = null;
	    for (Coordinate location : coinLocations) {
	        ArrayList<Coordinate> currPath = maze.findPath(playerCoordinate, location);
	        if (path == null) {
	            path = currPath;
	        } else if (path.size() > currPath.size()) {
	            path = currPath;
	        }
	    }
	    path.remove(path.size()-1);
		return path;
	}
	
	/**
	 * Gets the coordinates of all the coins remaining
	 * @return an array list of coordinates
	 */
	public ArrayList<Coordinate> getCoinCoordinates(){
		return coinLocations;
	}
	
	/**
	 * gets the number of coins left to be found
	 * @return number of coins
	 */
	public int getNumCoins(){
		return coinLocations.size();
	}
	
	public Coordinate getStartCoordinate() {
	    return startCoordinate;
	}
	
	/**
	 * Generates random coordinates and stores coins in them
	 */
	private void coinCoordinateGenerator(){
		Random randomGenerator = new Random();
		int x;
		int y;
		int[][] mazeArray = getMazeArray();
		int horizLength = mazeArray.length;
		int vertLength = mazeArray[0].length;
		Coordinate testCoordinate;
		
		int i = 0;
		while (i< TOTAL_COIN_NUM){
			x = randomGenerator.nextInt(horizLength);
			y = randomGenerator.nextInt(vertLength);
			testCoordinate = new Coordinate(x,y);
			//only make a coin if the coordinate is on a path, and if
			//there is not a coin already at that location, and if the
			//coin is not at the start position
			if (mazeArray[x][y] == ROAD && !coinLocations.contains(testCoordinate) && !testCoordinate.equals(startCoordinate)){
				coinLocations.add(testCoordinate);
				i++;
			}
		}
	}
	
	/**
	 * Clone the object
	 */
	public Object clone(){  
	    try{  
	        return super.clone();  
	    } catch (Exception e) { 
	        return null; 
	    }
	}
}
