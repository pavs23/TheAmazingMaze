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
	private JLabel onePlayerLabel;
	private JLabel twoPlayerLabel;
	
	private SingleOrMulti currentScreen;
	private BackButton back;
	private NumberOfPlayersButton onePlayer;
	private NumberOfPlayersButton twoPlayer;
	//private JLabel heading;
	
	private static final int lengthNumOfPlayerLabel = 150;
	private static final int widthNumOfPlayerLabel = 70;
	private final int xPositionButton = 225;
	private final int yPositionButton = 150;
	private static final int vGap = 30;
	
	public SingleOrMulti(final int mode, final JFrame mainFrame, final JPanel prev){
		this.setSize(new Dimension(600, 600));
		this.setLayout(null);
		
		
		JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
		currentScreen = this;
		
		//input image
		File titleFile = new File("numOfPlayer.png");
		File onePlayerFile = new File("onePlayer.png");
		File twoPlayerFile = new File("twoPlayer.png");
		
		Image titleImage = null;
		Image onePlayerImage = null;
		Image twoPlayerImage = null;
		
		try{
			titleImage = ImageIO.read(titleFile);
			onePlayerImage = ImageIO.read(onePlayerFile);
			twoPlayerImage = ImageIO.read(twoPlayerFile);
		}catch (IOException e) {}
		
		titleLabel = new JLabel();
        titleLabel.setSize(new Dimension(400, 70));
        titleLabel.setLocation(new Point(100, 20));
        Image scaledTitleButton = titleImage.getScaledInstance(titleLabel.getWidth(), titleLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon titleIcon = new ImageIcon(scaledTitleButton);
        titleLabel.setIcon(titleIcon);
		
        onePlayerLabel = new JLabel();
        onePlayerLabel.setSize(new Dimension(lengthNumOfPlayerLabel, widthNumOfPlayerLabel));
        onePlayerLabel.setLocation(new Point(100, 20));
        Image scaledOnePButton = onePlayerImage.getScaledInstance(onePlayerLabel.getWidth(), onePlayerLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon onePIcon = new ImageIcon(scaledOnePButton);
        onePlayerLabel.setIcon(onePIcon);
		
        twoPlayerLabel = new JLabel();
        twoPlayerLabel.setSize(new Dimension(lengthNumOfPlayerLabel, widthNumOfPlayerLabel));
        twoPlayerLabel.setLocation(new Point(100, 20));
        Image scaledTwoPButton = twoPlayerImage.getScaledInstance(twoPlayerLabel.getWidth(), twoPlayerLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon twoPIcon = new ImageIcon(scaledTwoPButton);
        twoPlayerLabel.setIcon(twoPIcon);
		
		onePlayer = new NumberOfPlayersButton(onePlayerLabel, new Point(xPositionButton, yPositionButton));
		twoPlayer = new NumberOfPlayersButton(twoPlayerLabel, new Point(xPositionButton, yPositionButton + widthNumOfPlayerLabel + vGap));
		
		back = new BackButton(prev, mainFrame, this);
		
		panelLabel.add(titleLabel);
		panelLabel.add(onePlayer);
		panelLabel.add(twoPlayer);
		panelLabel.add(back);
		
		this.add(panelLabel);
		
		//Set the colour of the buttons in this class as well as other formatting
		
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
