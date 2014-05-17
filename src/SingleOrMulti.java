import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SingleOrMulti extends JPanel{

	private SingleOrMulti currentScreen;
	
	public SingleOrMulti(int mode, final JFrame mainFrame){
		this.setSize(new Dimension(500, 500));
		currentScreen = this;
		
		JLabel heading = new JLabel("How many players?");
		heading.setSize(new Dimension(500, 150));;
		heading.setForeground(Color.BLACK);
		heading.setLocation(new Point(10,10));
		
		BackButton back = new BackButton(new Point(10, 480), this, mainFrame);
		
		NumberOfPlayersButton one = new NumberOfPlayersButton(mode, "One Player");
		NumberOfPlayersButton two = new NumberOfPlayersButton(mode, "Two Players");
		
		one.setLocation(new Point(175, 100));
		two.setLocation(new Point(175, 180));
		
		one.setSize(new Dimension(150, 70));
		two.setSize(new Dimension(150, 70));
		
		one.setBackground(Color.orange);
		two.setBackground(Color.MAGENTA);
		
		this.add(one);
		this.add(two);
		
		//Set the colour of the buttons in this class as well as other formatting
		
		one.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				//Open up difficulty selection mode which takes in number of game mode
				//		and number of players. That creates the game
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, MazeGame.ADVENTURE_MODE, MazeGame.SINGLE_PLAYER, currentScreen);
				currentScreen.setVisible(false);
				mainFrame.remove(currentScreen);
				mainFrame.add(dsScreen);
				dsScreen.setVisible(true);
				
			}
		});
		
		two.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//Open up difficulty selection mode which takes in number of game mode
				//		and number of players. That creates the game
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, MazeGame.ADVENTURE_MODE, MazeGame.MULTI_PLAYER, currentScreen);
				currentScreen.setVisible(false);
				mainFrame.remove(currentScreen);
				mainFrame.add(dsScreen);
				dsScreen.setVisible(true);
			}
		});
		
		this.setVisible(true);
		
	}
}
