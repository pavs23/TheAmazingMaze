import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectGameMode extends JPanel {

	private GameModeButton coinMode;
	private GameModeButton adventureMode;
	private SelectGameMode currentScreen;
	private BackButton back;
	
	public SelectGameMode(Dimension d, final JFrame mainFrame, final JPanel prev) {
		currentScreen = this;
		this.setSize(d);
		this.setLayout(null);
		coinMode = new GameModeButton("Coin Mode", Color.YELLOW);
		adventureMode = new GameModeButton("Adventure", Color.green);
		coinMode.setSize(200, 120);
		adventureMode.setSize(200, 120);

		Insets insets = this.getInsets();
		adventureMode.setBounds(insets.left + 175, insets.top + 100, 150, 90);
		coinMode.setBounds(insets.left + 175, insets.top + 250, 150, 90);
		
		back = new BackButton(new Point(10, 380), prev, mainFrame, this);
		back.setLocation(new Point(20, 380));
		this.add(back);

		// Still need to hook up this panel to the frame.
		// also need to put the frame into the chain of the frames.

		coinMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You have selected Coin Mode");
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, MazeGame.COIN_MODE, MazeGame.SINGLE_PLAYER, currentScreen);
				//See if remove works better rather than keeping these panels running
				
				currentScreen.setVisible(false);
				prev.setVisible(false);
				mainFrame.add(dsScreen);
			}
		});

		adventureMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You have selected Adventure Mode");
				SingleOrMulti players = new SingleOrMulti(MazeGame.ADVENTURE_MODE, mainFrame, currentScreen);
				mainFrame.add(players);
				//DifficultySelection dsScreen = new DifficultySelection(mainFrame, MazeGame.ADVENTURE_MODE);
				currentScreen.setVisible(false);					//Passed this variable into actionListener as a private
				prev.setVisible(false);
				players.setVisible(true);
				//mainFrame.add(dsScreen);
			}
		});
	
		this.add(adventureMode);
		this.add(coinMode);
		

	}
}
