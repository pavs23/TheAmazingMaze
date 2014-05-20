import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author pavan
 *
 */

public class SingleOrMulti extends JPanel{

	private SingleOrMulti currentScreen;
	private BackButton back;
	private NumberOfPlayersButton one;
	private NumberOfPlayersButton two;
	private JLabel heading;
	
	public SingleOrMulti(final int mode, final JFrame mainFrame, final JPanel prev){
		this.setSize(new Dimension(500, 500));
		this.setLayout(null);
		
		currentScreen = this;
		
		heading = new JLabel("How many players?");
		heading.setSize(new Dimension(500, 150));;
		heading.setForeground(Color.BLACK);
		heading.setLocation(new Point(10,10));
		
		back = new BackButton(new Point(10, 380), prev, mainFrame, this);
		back.setLocation(new Point(20, 380));
		
		one = new NumberOfPlayersButton(mode, "One Player");
		two = new NumberOfPlayersButton(mode, "Two Players");
		
		one.setLocation(new Point(175, 100));
		two.setLocation(new Point(175, 180));
		
		one.setSize(new Dimension(150, 70));
		two.setSize(new Dimension(150, 70));
		
		one.setBackground(Color.orange);
		two.setBackground(Color.MAGENTA);
		
		this.add(one);
		this.add(two);
		this.add(back);
		
		//Set the colour of the buttons in this class as well as other formatting
		
		one.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				//Open up difficulty selection mode which takes in number of game mode
				//		and number of players. That creates the game
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, mode, Game.SINGLE_PLAYER, currentScreen);
				//NameEntrySinglePlayer enterName = new NameEntrySinglePlayer(mainFrame, mode, currentScreen);
				currentScreen.setVisible(false);
				mainFrame.remove(currentScreen);
				//mainFrame.add(enterName);
				mainFrame.add(dsScreen);
				dsScreen.setVisible(true);
				
			}
		});
		
		two.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//Open up difficulty selection mode which takes in number of game mode
				//		and number of players. That creates the game
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, mode, Game.MULTI_PLAYER, currentScreen);
				//NameEntryDoublePlayer enterName = new NameEntryDoublePlayer(mainFrame, mode, currentScreen);
				currentScreen.setVisible(false);
				mainFrame.remove(currentScreen);
				//mainFrame.add(enterName);
				mainFrame.add(dsScreen);
				dsScreen.setVisible(true);
				
				//Add another panel here to take the user to enter the both the usernames
			}
		});
		
		this.setVisible(true);
		
	}
}
