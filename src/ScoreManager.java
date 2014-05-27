import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to get and set a leader board
 * @author Bronte Kalebic
 *
 */
public class ScoreManager {	
	// A bunch of constants for scoring.
	private static final int NUM_SCORES = 5;
	private static final int REDUNDANT_SCORE = NUM_SCORES;
	private static final int NUM_ELEMENTS = 2;
	
	/**
	 * A method that generates an array list of scores from a text file
	 * that stores all of the high scores. It reads from a different
	 * text file depending on the game mode and difficulty
	 * @param modeFlag is the integer indicating the game mode
	 * @param difficultyFlag is the integer indicating the game difficulty
	 * @return An array list of scores
	 */
	public ArrayList<LeaderBoardEntry> getScores(int modeFlag, int difficultyFlag) {

		ArrayList<LeaderBoardEntry> scoreArray = new ArrayList<LeaderBoardEntry>();
		String score; //stores whole line of input
		
		//splits input into name and score number
		String splitString[] = new String[NUM_ELEMENTS]; 
		String tempName; //temporary name to create lbe with
		int tempScore; //temporary score number to create lbe with
		LeaderBoardEntry tempLBE; //temporary lbe to add to array list

		//inner loop variables
		int i;
		int size;
		
		//find relevant text file
		FileInputStream scoreFile = getFileInputStream(modeFlag, difficultyFlag);
		
		//scan all the high score records and store them in an array list
		Scanner scanner = new Scanner(scoreFile);
		while(scanner.hasNextLine()) {
			score = scanner.nextLine();
			splitString = score.split(" ");
			
			//loop to find end of name if there's spaces
			i = 0;
			size = splitString.length;
			tempName = "";
			while (i < size-1) {
				tempName += splitString[i] + " ";
				i++;
			}
			tempScore = Integer.parseInt(splitString[size-1]);
			
			tempLBE = new LeaderBoardEntry(tempName, tempScore);
			scoreArray.add(tempLBE);
		}
		scanner.close();

		return scoreArray;

	}
	
	/**
	 * A method that stores a new high score into the high score board.
	 * It first determines if the new score is a high score or not, and
	 * if it is then its location in the score board is found and 
	 * another function is called to store it.
	 * @param modeFlag is an integer indicating the game mode
	 * @param difficultyFlag is an integer indicating the game difficulty
	 * @param newName is the name of the new high score
	 * @param newScore is the score number of the new high score
	 */
	public void setNewScore(int modeFlag, int difficultyFlag, String newName, int newScore) {
		ArrayList<LeaderBoardEntry> scoreArray = getScores(modeFlag, difficultyFlag);
		boolean savedNewScore;
		
		if (scoreArray.isEmpty()) {
			LeaderBoardEntry newLBE = new LeaderBoardEntry(newName, newScore);
			scoreArray.add(newLBE);
			savedNewScore = true;
		} else {
			savedNewScore = false;
			int i = 0;
			while (i < NUM_SCORES && i < scoreArray.size() + 1 && !savedNewScore) {
				if (i == scoreArray.size()) {
					LeaderBoardEntry newLBE = new LeaderBoardEntry(newName, newScore);
					scoreArray.add(i, newLBE);
					savedNewScore = true;
				} else if (newScore >= scoreArray.get(i).getScoreNum()) {
					LeaderBoardEntry newLBE = new LeaderBoardEntry(newName, newScore);
					scoreArray.add(i, newLBE);
					savedNewScore = true;
					if (scoreArray.size() > NUM_SCORES){
						scoreArray.remove(REDUNDANT_SCORE);
					}
				}
				i++;
			}		
		}
			
		if (savedNewScore) {
			storeLeaderBoard(scoreArray, modeFlag, difficultyFlag);
		}
			
	}
	
	/**
	 * A method that stores an array of leader board entries to a text file
	 * @param scoreArray is the array of leader board entries to be stored
	 * @param modeFlag is the integer indicating the game mode
	 * @param difficultyFlag is the integer indicating the game difficulty
	 */
	private void storeLeaderBoard(ArrayList<LeaderBoardEntry> scoreArray, int modeFlag, int difficultyFlag) {
		BufferedWriter writer = null;
		try{
			File file = getFile(modeFlag, difficultyFlag);
			if (file.exists()) {  
			    file.delete();
			}
			// Create a new file with the same name.
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
			
			//write scores to file
			int i = 0;
			String outputString;
			while (i < scoreArray.size()) {
				outputString = scoreArray.get(i).getScoreName() + " " + scoreArray.get(i).getScoreNum();
				writer.write(outputString + "\n");
				i++;
			}
			file.setReadOnly();
		} catch (IOException e) {
		} finally {
			try {
				writer.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * A method that determines and returns what file needs to be read for input
	 * @param modeFlag is an integer that indicates the game mode
	 * @param difficultyFlag is an integer that indicates the game difficulty
	 * @return a file input stream to be used to read input
	 */
	private FileInputStream getFileInputStream(int modeFlag, int difficultyFlag) {
		FileInputStream stream = null;
		try{
		    File file = getFile(modeFlag, difficultyFlag);
		    if (!file.exists()) {
		        file.createNewFile();    
		    } 
		    stream = new FileInputStream(file);
		    return stream;
		} catch (Exception e) {
		    return null;
		}
	}

	/**
	 * A method that determines and returns what file needs to written
	 * to for output
	 * @param modeFlag is an integer that indicates the game mode
	 * @param difficultyFlag is an integer that indicates the game difficulty
	 * @return a file to write output to
	 */
	private File getFile(int modeFlag, int difficultyFlag) {
		File file = null;
		try{
			if (difficultyFlag == Game.EASY) {
				if (modeFlag == Game.ADVENTURE_MODE) {
					file = new File("easyNormalScore.txt");
				} else if (modeFlag == Game.COIN_MODE) {
					file = new File("easyCoinScore.txt");
				}
			} else if (difficultyFlag == Game.MEDIUM) {
				if (modeFlag == Game.ADVENTURE_MODE){
					file = new File("mediumNormalScore.txt");
				} else if (modeFlag == Game.COIN_MODE) {
					file = new File("mediumCoinScore.txt");
				}
			} else if (difficultyFlag == Game.HARD) {
				if (modeFlag == Game.ADVENTURE_MODE) {
					file = new File("hardNormalScore.txt");
				} else if (modeFlag == Game.COIN_MODE) {
					file = new File("hardCoinScore.txt");
				}
			}
			return file;
		} catch (Exception e) {
			return null;
		}
	}
}
