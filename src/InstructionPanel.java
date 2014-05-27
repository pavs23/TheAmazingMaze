import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class for the panel that displays instructions on how to play the game
 * @author Bronte Kalebic
 *
 */
public class InstructionPanel extends JPanel {
	
	private static final int INSTRUCTIONS_X_COORDINATE = 125;
	private static final int INSTRUCTIONS_Y_COORDINATE = 150;
	private static final Dimension INSTRUCTIONS_DIMENSION = new Dimension(350, 100);
	private static final int MODE_HEADING_X_COORDINATE = 130;
	private static final int MODE_HEADING_Y_COORDINATE = 120;
	
	private BackButton backButton;
	private JLabel adventureInstructions;
	private JLabel coinInstructions;
	private ImageIcon headingIcon;
	private JLabel headingLabel;
	private ImageIcon adventureIcon;
	private JLabel adventureLabel;
	private ImageIcon coinIcon;
	private JLabel coinLabel;
	
	/**
	 * A constructor to generate the instruction panel
	 * @param mainFrame is the main frame of the game
	 * @param prev is the previous frame to go back to if the back 
	 * button is pressed
	 */
	public InstructionPanel(final JFrame mainFrame, final JPanel prev){
		
		//set current panel
		this.setLayout(null);
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
		
		JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
        //set adventure instructions
        adventureInstructions = new JLabel(getAdventureInstructionText());
        adventureInstructions.setSize(INSTRUCTIONS_DIMENSION);
        adventureInstructions.setLocation(new Point(INSTRUCTIONS_X_COORDINATE, INSTRUCTIONS_Y_COORDINATE));
        adventureInstructions.setVisible(true);
        
        //get coin instructions
        coinInstructions = new JLabel(getCoinInstructionText());
        coinInstructions.setSize(INSTRUCTIONS_DIMENSION);
        coinInstructions.setLocation(new Point(INSTRUCTIONS_X_COORDINATE, (2*INSTRUCTIONS_Y_COORDINATE)));
        coinInstructions.setVisible(true);
        
        //set back button
        backButton = new BackButton(prev, mainFrame, this);
        backButton.setVisible(true);
        
        //set images
        //heading
        headingIcon = new ImageIcon(Game.INSTRUCTIONS_HEADING);
        headingLabel = new JLabel();
        headingLabel.setIcon(headingIcon);
        headingLabel.setSize(new Dimension(286, 30));
        headingLabel.setLocation(new Point(155, 20));
        headingLabel.setVisible(true);
        //adventure mode
        adventureIcon = new ImageIcon(Game.ADVENTURE_MODE_INSTRUCTIONS_HEADING);
        adventureLabel = new JLabel();
        adventureLabel.setIcon(adventureIcon);
        adventureLabel.setSize(new Dimension(240, 21));
        adventureLabel.setLocation(new Point(MODE_HEADING_X_COORDINATE, MODE_HEADING_Y_COORDINATE));
        adventureLabel.setVisible(true);
        //coin mode
        coinIcon = new ImageIcon(Game.COIN_MODE_INSTRUCTIONS_HEADING);
        coinLabel = new JLabel();
        coinLabel.setIcon(coinIcon);
        coinLabel.setSize(new Dimension(151, 22));
        coinLabel.setLocation(new Point(MODE_HEADING_X_COORDINATE, (MODE_HEADING_Y_COORDINATE+160)));
        coinLabel.setVisible(true);
        
        //create frame
        this.add(backButton);
        this.add(adventureInstructions);
        this.add(coinInstructions); 
        this.add(headingLabel);
        this.add(adventureLabel);
        this.add(coinLabel);
        this.add(panelLabel);
		
	}
	
	/**
	 * Generates the text to be displayed on the instructions screen
	 * @return a string with all the text in it. The text is formatted 
	 * using HTML.
	 */
	private String getAdventureInstructionText(){
		String adventureText = "<html>In this mode, the player will "
				+ "need to reach the end of the maze in the fastest "
				+ "time possible. If the end is not reached within "
				+ "three minutes then the player will lose the game. "
				+ "The player's score will be how fast it took them to "
				+ "complete the maze.</html>";
		
		return adventureText;
	}
	
	private String getCoinInstructionText(){
		String coinText = "<html>In this mode, the player will need to "
				+ "collect all the coins in the fastest time possible. "
				+ "If all the coins are not collected in three minutes, "
				+ "then the player will lose the game. The player's "
				+ "score will be how fast it took them to collect all "
				+ "the coins.</html>";
		
		return coinText;
	}
	
}
