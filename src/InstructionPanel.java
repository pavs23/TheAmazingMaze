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
	
	private BackButton backButton;
	private JLabel title;
	private JLabel adventureInstructions;
	private JLabel coinInstructions;
	private ImageIcon headingIcon;
	private JPanel headingPanel;
	
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
        
		//set title
		title = new JLabel("Instructions");
        title.setSize(new Dimension(250, 40));
        title.setLocation(new Point(260, 20));
        title.setVisible(true);
        
        //set adventure instructions
        adventureInstructions = new JLabel(getAdventureInstructionText());
        adventureInstructions.setSize(new Dimension(500, 100));
        adventureInstructions.setLocation(new Point(50, 150));
        adventureInstructions.setVisible(true);
        
        //get coin instructions
        coinInstructions = new JLabel(getCoinInstructionText());
        coinInstructions.setSize(new Dimension(500, 100));
        coinInstructions.setLocation(new Point(50, 300));
        coinInstructions.setVisible(true);
        
        //set back button
        backButton = new BackButton(prev, mainFrame, this);
        backButton.setVisible(true);
        
        //create frame
        this.add(backButton);
        this.add(title);
        this.add(adventureInstructions);
        this.add(coinInstructions);        
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
