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
 * A class that create a panel for selecting game mode (coin/adventure).
 * @author pavan & JosephineJs
 *
 */
public class SelectGameMode extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
	private StyledButton coinMode;
	private StyledButton adventureMode;
	private SelectGameMode currentScreen;
	private JLabel panelLabel;
	private BackButton back;
	private JLabel adventureInstructions;
	private JLabel coinInstructions;
	
	private static final int WIDTH_BUTTON = 50;
	private static final int X_POSITION_BUTTON = 225;
	private static final int Y_POSITION_BUTTON = 150;
	private static final int V_GAP = 50; 

	/**
	 * Create select game mode panel.
	 * @param mainFrame the frame that contains this panel.
	 * @param prev the previous panel before this panel.
	 */
	public SelectGameMode(final JFrame mainFrame, final JPanel prev) {
		currentScreen = this;
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
		this.setLayout(null);
		
		File titleFile = new File("images/modeSelection.png");
		
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
		
		coinMode = new StyledButton("Coin");
		adventureMode = new StyledButton("Adventure");

		adventureMode.setLocation(X_POSITION_BUTTON, Y_POSITION_BUTTON);
		coinMode.setLocation(X_POSITION_BUTTON, Y_POSITION_BUTTON + WIDTH_BUTTON + V_GAP);

		back = new BackButton(prev, mainFrame, this);
		
		adventureInstructions = new JLabel();
		coinInstructions = new JLabel();
		
		JLabel advTitle = new JLabel("<html><b>Adventure Run</b></html>");
		JLabel coinTitle = new JLabel("<html><b>Coin Run</b></html>");

		advTitle.setSize(new Dimension(400, 10));
		coinTitle.setSize(new Dimension(400, 10));
		advTitle.setLocation(0, 0);
		coinTitle.setLocation(0, 0);
		advTitle.setHorizontalAlignment(JLabel.CENTER);
		coinTitle.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel advInst = new JLabel("<html><div style=\"text-align: center;\">Get to the end of the maze as quickly as possible before the time runs out."
                +" The quicker you get there, the higher your score! </html>");
		JLabel coinInst = new JLabel("<html><div style=\"text-align: center;\">Collect all the coins in the maze before the time runs out." 
                + " The quicker you collect your coins, the higher your score!</html>");
		advInst.setSize(new Dimension(350, 50));
		coinInst.setSize(new Dimension(350, 50));
		advInst.setLocation(25, 12);
		coinInst.setLocation(25, 12);
		advInst.setHorizontalAlignment(JLabel.CENTER);
        coinInst.setHorizontalAlignment(JLabel.CENTER);
		
		adventureInstructions.add(advTitle);
		adventureInstructions.add(advInst);
		
		coinInstructions.add(coinTitle);
        coinInstructions.add(coinInst);
        
		adventureInstructions.setSize(new Dimension(400, 70));
		coinInstructions.setSize(new Dimension(400, 70));
		
		adventureInstructions.setLocation(100, Y_POSITION_BUTTON + 190);
		coinInstructions.setLocation(100, Y_POSITION_BUTTON + 280);
		
		adventureInstructions.setHorizontalAlignment(JLabel.CENTER);
		coinInstructions.setHorizontalAlignment(JLabel.CENTER);
		
		panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
        panelLabel.add(titleLabel);
        panelLabel.add(back);
		panelLabel.add(adventureMode);
		panelLabel.add(coinMode);
		panelLabel.add(adventureInstructions);
		panelLabel.add(coinInstructions);
		
		this.add(panelLabel);

		coinMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SingleOrMulti players = new SingleOrMulti(
						Game.COIN_MODE, mainFrame, currentScreen);
				currentScreen.setVisible(false);
				prev.setVisible(false);
				mainFrame.add(players);
			}
		});

		adventureMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SingleOrMulti players = new SingleOrMulti(
						Game.ADVENTURE_MODE, mainFrame, currentScreen);
				mainFrame.add(players);				
				currentScreen.setVisible(false); 												
				prev.setVisible(false);
				players.setVisible(true);
			}
		});
		
	}
}
