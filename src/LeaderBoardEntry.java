/**
 * A class used to store a leader board entries name and score
 * @author Bronte Kalebic
 *
 */
public class LeaderBoardEntry {

	private String name;
	private int score;
	
	/**
	 * A constructor for the class
	 * @param newName is the name of the user that made the high score
	 * @param newScore is the high score number
	 */
	public LeaderBoardEntry(String newName, int newScore){
		name = newName;
		score = newScore;
	}
	
	/**
	 * Gets the name of the high score
	 * @return a string of the name
	 */
	public String getScoreName(){
		return name;
	}
	
	/**
	 * Gets the score of the high score
	 * @return an int of the score
	 */
	public int getScoreNum(){
		return score;
	}
	
}
