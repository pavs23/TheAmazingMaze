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
	private JPanel current;
	
	//public JFrame difficultySelection;
	
	
	public DifficultySelection(final JFrame mainFrame, final int mode, final int players, final JPanel prev){
		
		Insets insets = mainFrame.getInsets();
		
		easyButton = new DifficultyButton("Easy", new Point(175, 80), insets, new Dimension(150, 70));
		mediumButton = new DifficultyButton("Medium", new Point(175, 180), insets, new Dimension(150, 70));
		hardButton = new DifficultyButton("Hard", new Point(175, 280), insets, new Dimension(150, 70));
		backButton = new BackButton(new Point(20, 380), prev, mainFrame, this);
		current = this;
		
		this.add(easyButton);
		this.add(mediumButton);
		this.add(hardButton);
		this.add(backButton);
		this.setSize(new Dimension(500, 500));
		this.setLayout(null);
		
		easyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create an easy game");
				
				if(players == Game.SINGLE_PLAYER){
					NameEntrySinglePlayer nameEntry = new NameEntrySinglePlayer(mainFrame, mode, current, Game.EASY);
					current.setVisible(false);
					mainFrame.add(nameEntry);
					nameEntry.setVisible(true);
				} else if(players == Game.MULTI_PLAYER){
					NameEntryDoublePlayer nameEntry = new NameEntryDoublePlayer(mainFrame, mode, current, Game.EASY);
					current.setVisible(false);
					mainFrame.add(nameEntry);
					nameEntry.setVisible(true);
				}
				
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");

				//mainFrame.dispose();
				//new MazeGame(players, playerOne, mode, Game.MEDIUM);
				
				if(players == Game.SINGLE_PLAYER){
					NameEntrySinglePlayer nameEntry = new NameEntrySinglePlayer(mainFrame, mode, current, Game.MEDIUM);
					current.setVisible(false);
					mainFrame.add(nameEntry);
					nameEntry.setVisible(true);
				} else if(players == Game.MULTI_PLAYER){
					NameEntryDoublePlayer nameEntry = new NameEntryDoublePlayer(mainFrame, mode, current, Game.MEDIUM);
					current.setVisible(false);
					mainFrame.add(nameEntry);
					nameEntry.setVisible(true);
				}
				
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");

				//mainFrame.dispose();
				if(players == Game.SINGLE_PLAYER){
					NameEntrySinglePlayer nameEntry = new NameEntrySinglePlayer(mainFrame, mode, current, Game.HARD);
					current.setVisible(false);
					mainFrame.add(nameEntry);
					nameEntry.setVisible(true);
				} else if(players == Game.MULTI_PLAYER){
					NameEntryDoublePlayer nameEntry = new NameEntryDoublePlayer(mainFrame, mode, current, Game.HARD);
					current.setVisible(false);
					mainFrame.add(nameEntry);
					nameEntry.setVisible(true);
				}

			}
		});
				
	}
	
	
	public void run(){
		this.setVisible(true);
		//difficultySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
