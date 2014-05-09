import java.util.ArrayList;
import java.util.Random;

/**
 * A class that generates and monitors the state of a coin mode maze
 * @author Bronte Kalebic
 *
 */
public class CoinMaze implements GameMode{

	private ArrayList<Coordinate> coinLocations;
	private MazeGenerator maze;
	private int[][] mazeArray;
	
	private final int TOTAL_COIN_NUM = 10;
	//private final int WALL = 0;
	private final int ROAD = 1;
	
	/**
	 * A constructor that generates a coin maze
	 * @param sizeX is size of grid horizontally
	 * @param sizeY is size of grid vertically
	 */
	public CoinMaze(int sizeX, int sizeY){
		coinLocations = new ArrayList<Coordinate>();
		maze = new MazeGenerator(sizeX,sizeY);
		mazeArray = maze.generateMazeArray();
		
		coinCoordinateGenerator();
	}
	
	/**
	 * Checks whether a game has finished (if all coins have been found)
	 * @return whether the game is finished or not
	 */
	public boolean gameFinished(){
		if (coinLocations.isEmpty()){
			return true;
		} else {
			return false;
		}
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
	 * @return an array list of the coordinates of the path to a coin
	 */
	public ArrayList<Coordinate> getHint(Coordinate playerCoordinate){
		return maze.findPath(playerCoordinate, coinLocations.get(0));
	}
	
	/**
	 * Gets the coordinates of all the coins remaining
	 * @return an array list of coordinates
	 */
	public ArrayList<Coordinate> getCoinCoordinates(){
		return coinLocations;
	}
	
	/**
	 * gets the mazeArray
	 * @return 2d array of 1 and 0s denoting walls or roads
	 */
	public int[][] getMazeArray(){
		return mazeArray;
	}
	
	/**
	 * gets the number of coins left to be found
	 * @return number of coins
	 */
	public int getNumCoins(){
		return coinLocations.size();
	}
	
	/**
	 * Generates random coordinates and stores coins in them
	 */
	private void coinCoordinateGenerator(){
		Random randomGenerator = new Random();
		int x;
		int y;
		int horizLength = mazeArray.length;
		int vertLength = mazeArray[0].length;
		Coordinate testCoordinate;
		Coordinate startCoordinate = new Coordinate(1,1);
		
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
	
	
}
