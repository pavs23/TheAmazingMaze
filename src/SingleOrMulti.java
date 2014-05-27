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
 * Create a panel for single or multiplayer game.
 * @author pavan & JosephineJs
 *
 */
public class SingleOrMulti extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel titleLabel;
	private JLabel panelLabel;
	private SingleOrMulti currentScreen;
	private BackButton back;
	private StyledButton onePlayer;
	private StyledButton twoPlayer;
	
	private static final int WIDTH_BUTTON = 50;
    private static final int X_POSITION_BUTTON = 225;
    private static final int Y_POSITION_BUTTON = 150;
    private static final int V_GAP = 50; 
	
    /**
     * Create a panel for single or multiplayer game.
     * @param mode the mode of the game.
     * @param mainFrame the frame that contains this panel.
     * @param prev the previous panel before this panel.
     */
	public SingleOrMulti(final int mode, final JFrame mainFrame, final JPanel prev) {
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
		this.setLayout(null);
        
		currentScreen = this;
		
		//input image
		File titleFile = new File("numOfPlayer.png");
		Image titleImage = null;
		
		try {
			titleImage = ImageIO.read(titleFile);
		
		} catch (IOException e) {}
		
		titleLabel = new JLabel();
        titleLabel.setSize(new Dimension(400, 70));
        titleLabel.setLocation(new Point(100, 20));
        Image scaledTitleButton = titleImage.getScaledInstance(titleLabel.getWidth(), titleLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon titleIcon = new ImageIcon(scaledTitleButton);
        titleLabel.setIcon(titleIcon);
		
        
        onePlayer = new StyledButton("One Player");
		twoPlayer = new StyledButton("Two Player");
		onePlayer.setLocation(X_POSITION_BUTTON, Y_POSITION_BUTTON);
		twoPlayer.setLocation(X_POSITION_BUTTON, Y_POSITION_BUTTON + WIDTH_BUTTON + V_GAP);
		
		back = new BackButton(prev, mainFrame, this);
		
		panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
		panelLabel.add(titleLabel);
		panelLabel.add(onePlayer);
		panelLabel.add(twoPlayer);
		panelLabel.add(back);
		
		this.add(panelLabel);
				
		onePlayer.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, mode, Game.SINGLE_PLAYER, currentScreen);
				currentScreen.setVisible(false);
				mainFrame.remove(currentScreen);
				mainFrame.add(dsScreen);
				dsScreen.setVisible(true);
				
			}
		});
		
		twoPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, mode, Game.MULTI_PLAYER, currentScreen);
				currentScreen.setVisible(false);
				mainFrame.remove(currentScreen);
				mainFrame.add(dsScreen);
				dsScreen.setVisible(true);
			}
		});
		
		this.setVisible(true);
		
	}
}
