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
	private final int xPosition = 225;
	private final int yPosition = 100;
	
	public SingleOrMulti(final int mode, final JFrame mainFrame, final JPanel prev){
		this.setSize(new Dimension(600, 600));
		this.setLayout(null);
		
		JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
		currentScreen = this;
		
		heading = new JLabel("How many players?");
		heading.setSize(new Dimension(600, 150));;
		heading.setForeground(Color.BLACK);
		heading.setLocation(new Point(10,10));
		
		back = new BackButton(prev, mainFrame, this);
		
		one = new NumberOfPlayersButton(mode, "One Player");
		two = new NumberOfPlayersButton(mode, "Two Players");
		
		one.setLocation(new Point(xPosition, yPosition));
		two.setLocation(new Point(xPosition, yPosition + 160));
		
		one.setSize(new Dimension(150, 90));
		two.setSize(new Dimension(150, 90));
		
		one.setBackground(Color.orange);
		two.setBackground(Color.MAGENTA);
		
		panelLabel.add(one);
		panelLabel.add(two);
		panelLabel.add(back);
		
		this.add(panelLabel);
		
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
