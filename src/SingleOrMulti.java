import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author pavan & Jo
 *
 */

public class SingleOrMulti extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel titleLabel;
	
	private SingleOrMulti currentScreen;
	private BackButton back;
	private StyledButton onePlayer;
	private StyledButton twoPlayer;
	
	public SingleOrMulti(final int mode, final JFrame mainFrame, final JPanel prev){
		
		final int lengthNumOfPlayerLabel = 150;
		final int widthNumOfPlayerLabel = 70;
		final int xPositionButton = 225;
		final int yPositionButton = 150;
		final int vGap = 30;
		
		
		this.setSize(new Dimension(600, 600));
		this.setLayout(null);
        
		currentScreen = this;
		
		//input image
		File titleFile = new File("numOfPlayer.png");
		
		Image titleImage = null;
		
		try{
			titleImage = ImageIO.read(titleFile);
		
		}catch (IOException e) {}
		
		titleLabel = new JLabel();
        titleLabel.setSize(new Dimension(400, 70));
        titleLabel.setLocation(new Point(100, 20));
        Image scaledTitleButton = titleImage.getScaledInstance(titleLabel.getWidth(), titleLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon titleIcon = new ImageIcon(scaledTitleButton);
        titleLabel.setIcon(titleIcon);
		
        
        onePlayer = new StyledButton("One Player");
		twoPlayer = new StyledButton("Two Player");
		onePlayer.setSize(lengthNumOfPlayerLabel, widthNumOfPlayerLabel);
		twoPlayer.setSize(lengthNumOfPlayerLabel, widthNumOfPlayerLabel);
		onePlayer.setLocation(xPositionButton, yPositionButton);
		twoPlayer.setLocation(xPositionButton, yPositionButton + widthNumOfPlayerLabel + vGap);
		
		back = new BackButton(prev, mainFrame, this);
		
		JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
		panelLabel.add(titleLabel);
		panelLabel.add(onePlayer);
		panelLabel.add(twoPlayer);
		panelLabel.add(back);
		
		this.add(panelLabel);
		
		//Set the color of the buttons in this class as well as other formatting
		
		onePlayer.addActionListener(new ActionListener() {
			
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
		
		twoPlayer.addActionListener(new ActionListener() {
			
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
