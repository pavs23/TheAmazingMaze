import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author pavan
 *
 */

public class DifficultySelection extends JPanel {

	private DifficultyButton easyButton;
	private DifficultyButton mediumButton;
	private DifficultyButton hardButton;
	private BackButton backButton;
	private String playerOne;
	private String playerTwo;
	//public JFrame difficultySelection;
	
	
	public DifficultySelection(final JFrame mainMenu, final int mode, final int players, final JPanel prev, String playerNameIn){
		
		Insets insets = mainMenu.getInsets();
		
		easyButton = new DifficultyButton("Easy", new Point(175, 80), insets, new Dimension(150, 70));
		mediumButton = new DifficultyButton("Medium", new Point(175, 180), insets, new Dimension(150, 70));
		hardButton = new DifficultyButton("Hard", new Point(175, 280), insets, new Dimension(150, 70));
		backButton = new BackButton(new Point(20, 380), prev, mainMenu, this);
		playerOne = playerNameIn;
		
		this.add(easyButton);
		this.add(mediumButton);
		this.add(hardButton);
		this.add(backButton);
		this.setSize(new Dimension(500, 500));
		this.setLayout(null);
		
		easyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create an easy game");
				mainMenu.dispose();
				new MazeGame(Game.PLAYER_0, playerOne, mode, Game.EASY);
				
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");
				mainMenu.dispose();
				new MazeGame(players, playerOne, mode, Game.MEDIUM);
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");
				mainMenu.dispose();
				new MazeGame(players, playerOne, mode, Game.HARD);
			}
		});
				
	}
	
public DifficultySelection(final JFrame mainMenu, final int mode, final int players, final JPanel prev, String playerOneIn, String playerTwoIn){
		
		Insets insets = mainMenu.getInsets();
		
		easyButton = new DifficultyButton("Easy", new Point(175, 80), insets, new Dimension(150, 70));
		mediumButton = new DifficultyButton("Medium", new Point(175, 180), insets, new Dimension(150, 70));
		hardButton = new DifficultyButton("Hard", new Point(175, 280), insets, new Dimension(150, 70));
		backButton = new BackButton(new Point(20, 380), prev, mainMenu, this);
		playerOne = playerOneIn;
		playerTwo = playerTwoIn;
		
		this.add(easyButton);
		this.add(mediumButton);
		this.add(hardButton);
		this.add(backButton);
		this.setSize(new Dimension(500, 500));
		this.setLayout(null);
		
		easyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create an easy game");
				mainMenu.dispose();
				//new MazeGame(players, playerOne, mode, Game.EASY);
				new MazeGame(Game.PLAYER_0, playerOne, Game.PLAYER_2, playerTwo, mode, Game.EASY);
				
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");
				mainMenu.dispose();
				//new MazeGame(players, playerOne, mode, Game.MEDIUM);
				new MazeGame(Game.PLAYER_0, playerOne, Game.PLAYER_2, playerTwo, mode, Game.MEDIUM);
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");
				mainMenu.dispose();
				//new MazeGame(players, playerOne, mode, Game.HARD);
				new MazeGame(Game.PLAYER_0, playerOne, Game.PLAYER_2, playerTwo, mode, Game.HARD);
			}
		});
				
	}
	
	public void run(){
		this.setVisible(true);
		//difficultySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
