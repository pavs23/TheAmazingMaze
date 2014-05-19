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
	//public JFrame difficultySelection;
	
	
	public DifficultySelection(final JFrame mainMenu, final int mode, final int players, final JPanel prev){
		
		Insets insets = mainMenu.getInsets();
		
		easyButton = new DifficultyButton("Easy", new Point(175, 80), insets, new Dimension(150, 70));
		mediumButton = new DifficultyButton("Medium", new Point(175, 180), insets, new Dimension(150, 70));
		hardButton = new DifficultyButton("Hard", new Point(175, 280), insets, new Dimension(150, 70));
		backButton = new BackButton(new Point(20, 380), prev, mainMenu, this);
		
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
				new MazeGame(players, mode, Game.EASY);
				
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");
				mainMenu.dispose();
				new MazeGame(players, mode, Game.MEDIUM);
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");
				mainMenu.dispose();
				new MazeGame(players, mode, Game.HARD);
			}
		});
		/*
		backButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go back to the main menu");
				MainPanel panel = new MainPanel(new Dimension(500, 500));
				MainScreen main = new MainScreen();
				mainMenu.dispose();
				main.run();
				
				
				//MainScreen main = new MainScreen();
				//main.run();
				//this.dispose();
			}
		});
		*/			
	}
	
	public void run(){
		this.setVisible(true);
		//difficultySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
