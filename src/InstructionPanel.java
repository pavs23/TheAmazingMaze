import java.awt.Dimension;
import java.awt.Point;

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
	private JLabel instructions;
	
	public InstructionPanel(final JFrame mainFrame, final JPanel prev){
		
		//set current panel
		this.setLayout(null);
		this.setSize(new Dimension(500, 500));
		
		//set title
		title = new JLabel("Instructions");
        title.setSize(new Dimension(250, 40));
        title.setLocation(new Point(200, 20));
        title.setVisible(true);
        
        //set instructions
        instructions = new JLabel(getInstructionText());
        instructions.setSize(new Dimension(400, 180));
        instructions.setLocation(new Point(20, 100));
        instructions.setVisible(true);
        
        //set back button
        backButton = new BackButton(new Point(20, 380), prev, mainFrame, this);
        backButton.setVisible(true);
        
        //create frame
        this.add(backButton);
        this.add(title);
        this.add(instructions);
		
	}
	
	private String getInstructionText(){
		String adventureTitle = "<html>Adventure Mode:<br>";
		String adventureText = "In this mode, the player will need to "
				+ "reach the end of the maze in the fastest time "
				+ "possible. If the end is not reached within three "
				+ "minutes then the player will lose the game. The "
				+ "player's score will be how fast it took them to "
				+ "complete the maze.<br><br>";
		String coinTitle = "CoinMode:<br>";
		String coinText = "In this mode, the player will need to "
				+ "collect all the coins in the fastest time possible. "
				+ "If all the coins are not collected in three minutes, "
				+ "then the player will lose the game. The player's "
				+ "score will be how fast it took them to collect all "
				+ "the coins.</html>";
		
		return adventureTitle + adventureText + coinTitle + coinText;
	}
	
}
