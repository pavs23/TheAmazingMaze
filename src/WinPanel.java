import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Create a winning panel for the game.
 * @author floren
 *
 */
public class WinPanel extends FinishingPanel {
    private static final long serialVersionUID = 1L;
    private JLabel winLabel;
    
    /**
     * Constructor of the class for SinglePlayer game.
     * @param frame the frame that contains the panel.
     * @param playerName the name of the winning player.
     * @param score the score of the winning player in SinglePlayer mode.
     * @param mode the mode of the game (coin/adventure).
     * @param difficulty the difficulty of the game.
     */
	public WinPanel(JFrame frame, String playerName, int score, int mode, int difficulty) {
		super(frame);
		
        winLabel = new JLabel();
        winLabel.setSize(new Dimension(600, 430));
        winLabel.setLocation(new Point(0, 0));
        winLabel.setLayout(null);
        winLabel.setBackground(Color.WHITE);
        
        File winFile = new File("images/win.jpg");
        Image winImage = null;
        try {
            winImage = ImageIO.read(winFile);
        } catch (IOException e) {}
        
        
        JLabel messageIcon = new JLabel();
        messageIcon.setText(playerName + " Wins!");
        messageIcon.setHorizontalAlignment(JLabel.CENTER);
        messageIcon.setFont(new Font("Arial", Font.BOLD, 40));
        messageIcon.setSize(new Dimension(600, 70));
        messageIcon.setLocation(new Point(0, 10));
        
        JLabel happyIcon = new JLabel();
        happyIcon.setIcon(new ImageIcon(winImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        happyIcon.setSize(new Dimension(250, 250));
        happyIcon.setLocation(new Point(60, 140));
        
        JLabel scoreLabel = new JLabel();
        scoreLabel.setBackground(Color.WHITE);
        scoreLabel.setText("Score : " + score);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setSize(new Dimension(150, 30));
        scoreLabel.setLocation(new Point(225, 80));
        
        JLabel leaderBoard = new JLabel();
        leaderBoard.setBackground(Color.BLACK);
        leaderBoard.setOpaque(true);
        leaderBoard.setSize(new Dimension(250, 180));
        leaderBoard.setLocation(new Point(320, 160));
        
        JLabel leaderBoardLabel = new JLabel();
        leaderBoardLabel.setText("Leaderboard");
        leaderBoardLabel.setBackground(Color.BLACK);
        leaderBoardLabel.setForeground(Color.YELLOW);
        leaderBoardLabel.setOpaque(true);
        leaderBoardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        leaderBoardLabel.setSize(new Dimension(250, 30));
        leaderBoardLabel.setLocation(new Point(30, 20));
                    
        JLabel leaderBoardNames = new JLabel();
        leaderBoardNames.setBackground(Color.BLACK);
        leaderBoardNames.setForeground(Color.WHITE);
        leaderBoardNames.setOpaque(true);
        leaderBoardNames.setText(getLeaderBoardNames(mode, difficulty));
        leaderBoardNames.setFont(new Font("Arial", Font.PLAIN, 16));
        leaderBoardNames.setSize(new Dimension(120, 140));
        leaderBoardNames.setLocation(new Point(30, 60));
        leaderBoardNames.setVerticalAlignment(JLabel.TOP);
        
        JLabel leaderBoardScores = new JLabel();
        leaderBoardScores.setBackground(Color.BLACK);
        leaderBoardScores.setForeground(Color.WHITE);
        leaderBoardScores.setOpaque(true);
        leaderBoardScores.setText(getLeaderBoardScores(mode, difficulty));
        leaderBoardScores.setFont(new Font("Arial", Font.PLAIN, 16));
        leaderBoardScores.setSize(new Dimension(100, 140));
        leaderBoardScores.setLocation(new Point(180, 60));
        leaderBoardScores.setVerticalAlignment(JLabel.TOP);
        
        
        leaderBoard.add(leaderBoardLabel);
        leaderBoard.add(leaderBoardNames);
        leaderBoard.add(leaderBoardScores);
        
        winLabel.add(messageIcon);
        winLabel.add(happyIcon);
        winLabel.add(scoreLabel);
        winLabel.add(leaderBoard);
  
        setMessage(winLabel);
	}
	
	/**
	 * Return the names of leaderboard winners.
	 * @param mode the mode of the game.
	 * @param difficulty the difficulty of the game.
	 * @return String representing the names of the players in leaderboard.
	 */
	private String getLeaderBoardNames(int mode, int difficulty) {
	    ScoreManager leaderBoard = new ScoreManager();
	    ArrayList<LeaderBoardEntry> scores = leaderBoard.getScores(mode, difficulty);
	    int i = 0;
        int numEntries = scores.size();
        LeaderBoardEntry LBE;
        String returnVal = "<html>";
        while (i < numEntries){
            LBE = scores.get(i);
            returnVal += LBE.getScoreName() + "<br>";
            i++;
        }
        returnVal += "</html>";   
        return returnVal;
	}
	
	/**
     * Return the scores of leaderboard winners.
     * @param mode the mode of the game.
     * @param difficulty the difficulty of the game.
     * @return String representing the scores of the players in leaderboard.
     */
	private String getLeaderBoardScores(int mode, int difficulty) {
        ScoreManager leaderBoard = new ScoreManager();
        ArrayList<LeaderBoardEntry> scores = leaderBoard.getScores(mode, difficulty);
        int i = 0;
        int numEntries = scores.size();
        LeaderBoardEntry LBE;
        String returnVal = "<html>";
        while (i < numEntries){
            LBE = scores.get(i);
            returnVal += LBE.getScoreNum() + "<br>";
            i++;
        }
        returnVal += "</html>";   
        return returnVal;
    }
	
	/**
     * Constructor of the class for MultiPlayer game.
     * @param frame the frame that contains the panel.
     * @param playerName the name of the winning player.
     */
    public WinPanel(JFrame frame, String playerName) {
        super(frame);
        JLabel winLabel = new JLabel();
        winLabel.setSize(new Dimension(600, 430));
        winLabel.setLocation(new Point(0, 0));
        winLabel.setLayout(null);
        winLabel.setBackground(Color.WHITE);
        
        File winFile = new File("images/win.jpg");
        Image winImage = null;
        try {
            winImage = ImageIO.read(winFile);
        } catch (IOException e) {}
        
        JLabel messageIcon = new JLabel();
        messageIcon.setText(playerName + " Wins!");
        messageIcon.setHorizontalAlignment(JLabel.CENTER);
        messageIcon.setFont(new Font("Arial", Font.BOLD, 40));
        messageIcon.setSize(new Dimension(600, 70));
        messageIcon.setLocation(new Point(0, 10));
        
        JLabel happyIcon = new JLabel();
        happyIcon.setIcon(new ImageIcon(winImage.getScaledInstance(150, 250, Image.SCALE_SMOOTH)));
        happyIcon.setSize(new Dimension(150, 250));
        happyIcon.setLocation(new Point(225, 80));
              
        winLabel.add(messageIcon);
        winLabel.add(happyIcon);
        
        setMessage(winLabel);
    }

}
