import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel to display the current leader board
 * @author Bronte Kalebic
 *
 */
public class LeaderBoard extends JPanel{
	
	//constants for iterating through array list of array lists
	private final int EASY_NORMAL_LOC = 0;
	private final int EASY_COIN_LOC = 1;
	private final int MEDIUM_NORMAL_LOC = 2;
	private final int MEDIUM_COIN_LOC = 3;
	private final int HARD_NORMAL_LOC = 4;
	private final int HARD_COIN_LOC = 5;
	
	//general GUI things
	private JLabel title;
	private BackButton back;

	//score displaying JLabels
	private JLabel easyNormalTitle;
	private JLabel easyNormalNames;
	private JLabel easyNormalScores;
	private JLabel easyCoinTitle;
	private JLabel easyCoinNames;
	private JLabel easyCoinScores;
	private JLabel mediumNormalTitle;
	private JLabel mediumNormalNames;
	private JLabel mediumNormalScores;
	private JLabel mediumCoinTitle;
	private JLabel mediumCoinNames;
	private JLabel mediumCoinScores;
	private JLabel hardNormalTitle;
	private JLabel hardNormalNames;
	private JLabel hardNormalScores;
	private JLabel hardCoinTitle;
	private JLabel hardCoinNames;
	private JLabel hardCoinScores;
	
	/**
	 * Sets up all of the leader boards and displays them.
	 * @param leaders is an array list of leader boards
	 * @param prev is the previous window
	 * @param mainFrame is the main window
	 */
	public LeaderBoard(ArrayList<ArrayList<LeaderBoardEntry>> leaders, final JPanel prev, final JFrame mainFrame){
		
		//set current panel
		this.setSize(new Dimension(600, 600));
		this.setLayout(null);
		
		//set back button
		back = new BackButton(prev, mainFrame, this);
		back.setVisible(true);
		
		//set title
		title = new JLabel("Our Amazing Champions!");
		title.setSize(new Dimension(250, 40));
		title.setLocation(new Point(160, 20));
		title.setVisible(true);
		
		//set leader board
		//easy normal
		easyNormalTitle = new JLabel("Easy Adventure Mode");
		easyNormalTitle.setSize(new Dimension(150, 40));
		easyNormalTitle.setLocation(new Point(20, 55));
		easyNormalTitle.setVisible(true);
		easyNormalNames = new JLabel(displayLeaderBoardNames(leaders.get(EASY_NORMAL_LOC)));
		easyNormalNames.setSize(new Dimension(100, 100));
		easyNormalNames.setLocation(new Point(20, 70));
		easyNormalNames.setVisible(true);		
		easyNormalScores = new JLabel(displayLeaderBoardScores(leaders.get(EASY_NORMAL_LOC)));
		easyNormalScores.setSize(new Dimension(100, 100));
		easyNormalScores.setLocation(new Point(120, 70));
		easyNormalScores.setVisible(true);
		//easy coin
		easyCoinTitle = new JLabel("Easy Coin Mode");
		easyCoinTitle.setSize(new Dimension(150, 40));
		easyCoinTitle.setLocation(new Point(270, 55));
		easyCoinTitle.setVisible(true);
		easyCoinNames = new JLabel(displayLeaderBoardNames(leaders.get(EASY_COIN_LOC)));
		easyCoinNames.setSize(new Dimension(100, 100));
		easyCoinNames.setLocation(new Point(270, 70));
		easyCoinNames.setVisible(true);		
		easyCoinScores = new JLabel(displayLeaderBoardScores(leaders.get(EASY_COIN_LOC)));
		easyCoinScores.setSize(new Dimension(100, 100));
		easyCoinScores.setLocation(new Point(370, 70));
		easyCoinScores.setVisible(true);
		//medium normal
		mediumNormalTitle = new JLabel("Medium Adventure Mode");
		mediumNormalTitle.setSize(new Dimension(150, 40));
		mediumNormalTitle.setLocation(new Point(20, 155));
		mediumNormalTitle.setVisible(true);
		mediumNormalNames = new JLabel(displayLeaderBoardNames(leaders.get(MEDIUM_NORMAL_LOC)));
		mediumNormalNames.setSize(new Dimension(100, 100));
		mediumNormalNames.setLocation(new Point(20, 170));
		mediumNormalNames.setVisible(true);		
		mediumNormalScores = new JLabel(displayLeaderBoardScores(leaders.get(MEDIUM_NORMAL_LOC)));
		mediumNormalScores.setSize(new Dimension(100, 100));
		mediumNormalScores.setLocation(new Point(120, 170));
		mediumNormalScores.setVisible(true);
		//medium coin
		mediumCoinTitle = new JLabel("Medium Coin Mode");
		mediumCoinTitle.setSize(new Dimension(150, 40));
		mediumCoinTitle.setLocation(new Point(270, 155));
		mediumCoinTitle.setVisible(true);
		mediumCoinNames = new JLabel(displayLeaderBoardNames(leaders.get(MEDIUM_COIN_LOC)));
		mediumCoinNames.setSize(new Dimension(100, 100));
		mediumCoinNames.setLocation(new Point(270, 170));
		mediumCoinNames.setVisible(true);		
		mediumCoinScores = new JLabel(displayLeaderBoardScores(leaders.get(MEDIUM_COIN_LOC)));
		mediumCoinScores.setSize(new Dimension(100, 100));
		mediumCoinScores.setLocation(new Point(370, 170));
		mediumCoinScores.setVisible(true);
		//hard normal
		hardNormalTitle = new JLabel("Hard Adventure Mode");
		hardNormalTitle.setSize(new Dimension(150, 40));
		hardNormalTitle.setLocation(new Point(20, 255));
		hardNormalTitle.setVisible(true);
		hardNormalNames = new JLabel(displayLeaderBoardNames(leaders.get(HARD_NORMAL_LOC)));
		hardNormalNames.setSize(new Dimension(100, 100));
		hardNormalNames.setLocation(new Point(20, 270));
		hardNormalNames.setVisible(true);		
		hardNormalScores = new JLabel(displayLeaderBoardScores(leaders.get(HARD_NORMAL_LOC)));
		hardNormalScores.setSize(new Dimension(100, 100));
		hardNormalScores.setLocation(new Point(120, 270));
		hardNormalScores.setVisible(true);
		//hard coin
		hardCoinTitle = new JLabel("Hard Coin Mode");
		hardCoinTitle.setSize(new Dimension(150, 40));
		hardCoinTitle.setLocation(new Point(270, 255));
		hardCoinTitle.setVisible(true);
		hardCoinNames = new JLabel(displayLeaderBoardNames(leaders.get(HARD_COIN_LOC)));
		hardCoinNames.setSize(new Dimension(100, 100));
		hardCoinNames.setLocation(new Point(270, 270));
		hardCoinNames.setVisible(true);		
		hardCoinScores = new JLabel(displayLeaderBoardScores(leaders.get(HARD_COIN_LOC)));
		hardCoinScores.setSize(new Dimension(100, 100));
		hardCoinScores.setLocation(new Point(370, 270));
		hardCoinScores.setVisible(true);
		
		//generate frame
		this.add(back);
		this.add(title);
		this.add(easyNormalTitle);
		this.add(easyNormalNames);
		this.add(easyNormalScores);
		this.add(easyCoinTitle);
		this.add(easyCoinNames);
		this.add(easyCoinScores);
		this.add(mediumNormalTitle);
		this.add(mediumNormalNames);
		this.add(mediumNormalScores);
		this.add(mediumCoinTitle);
		this.add(mediumCoinNames);
		this.add(mediumCoinScores);
		this.add(hardNormalTitle);
		this.add(hardNormalNames);
		this.add(hardNormalScores);
		this.add(hardCoinTitle);
		this.add(hardCoinNames);
		this.add(hardCoinScores);
		
	}
	
	/**
	 * This method finds all of the names in the entries in the leader
	 * board and converts them into a string to be displayed
	 * @param entries is an array list of entries to be displayed
	 * @return a string of all of the names
	 */
	private String displayLeaderBoardNames(ArrayList<LeaderBoardEntry> entries){
		int i = 0;
		int numEntries = entries.size();
		LeaderBoardEntry LBE;
		String returnVal = "<html>";
		while (i < numEntries){
			LBE = entries.get(i);
			returnVal += LBE.getScoreName() + "<br>";
			i++;
		}
		returnVal += "</html>";
		
		return returnVal;
	}
	
	/**
	 * This method finds all of the scores in the entries in the leader
	 * board and converts them into a string to be displayed
	 * @param entries is an array list of entries to be displayed
	 * @return a string of all of the scores
	 */
	private String displayLeaderBoardScores(ArrayList<LeaderBoardEntry> entries){
		int i = 0;
		int numEntries = entries.size();
		LeaderBoardEntry LBE;
		String returnVal = "<html>";
		while (i < numEntries){
			LBE = entries.get(i);
			returnVal += LBE.getScoreNum() + "<br>";
			i++;
		}
		returnVal += "</html>";
		
		return returnVal;
	}
}
