import java.awt.Color;
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

public class SelectGameMode extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
	private GameModeButton coinMode;
	private GameModeButton adventureMode;
	private SelectGameMode currentScreen;
	private BackButton back;
	
	private static final int lengthNumOfPlayerLabel = 150;
	private static final int widthNumOfPlayerLabel = 70;
	private final int xPositionButton = 225;
	private final int yPositionButton = 150;
	private static final int vGap = 30; 

	public SelectGameMode(Dimension d, final JFrame mainFrame, final JPanel prev) {
		currentScreen = this;
		this.setSize(d);
		this.setLayout(null);
		
		File titleFile = new File("modeSelection.png");
		
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
		
		coinMode = new GameModeButton("Coin", Color.orange);
		
		adventureMode = new GameModeButton("Adventure", Color.orange);
		coinMode.setSize(lengthNumOfPlayerLabel, widthNumOfPlayerLabel);
		adventureMode.setSize(lengthNumOfPlayerLabel, widthNumOfPlayerLabel);

		JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
        panelLabel.add(titleLabel);
        
		//Insets insets = this.getInsets();
		adventureMode.setLocation(xPositionButton, yPositionButton);
		coinMode.setLocation(xPositionButton, yPositionButton + widthNumOfPlayerLabel + vGap);

		back = new BackButton(prev, mainFrame, this);

		// Still need to hook up this panel to the frame.
		// also need to put the frame into the chain of the frames.

		coinMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You have selected Coin Mode");
				SingleOrMulti players = new SingleOrMulti(
						Game.COIN_MODE, mainFrame, currentScreen);
				// See if remove works better rather than keeping these panels
				// running

				currentScreen.setVisible(false);
				prev.setVisible(false);
				mainFrame.add(players);
			}
		});

		adventureMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You have selected Adventure Mode");
				SingleOrMulti players = new SingleOrMulti(
						Game.ADVENTURE_MODE, mainFrame, currentScreen);
				mainFrame.add(players);
				
				currentScreen.setVisible(false); // Passed this variable into actionListener as a private
												
				prev.setVisible(false);
				players.setVisible(true);
				// mainFrame.add(dsScreen);
			}
		});
		
		panelLabel.add(back);
		panelLabel.add(adventureMode);
		panelLabel.add(coinMode);
		this.add(panelLabel);
	}
}
