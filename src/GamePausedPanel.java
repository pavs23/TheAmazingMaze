import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author pavan
 *
 */

public class GamePausedPanel extends JPanel{

	private JButton continueButton;
	
	/**
	 * 
	 * @param resume
	 */
	public GamePausedPanel(){
		
		//Still need to add the action listener for the button,
		//	which is to resume() the game.
		/*
		continueButton = new JButton("Continue...");
		continueButton.setBackground(Color.green);
		continueButton.setLocation(250, 250);
		continueButton.setSize(new Dimension(80,50));
		this.add(continueButton);
		*/
	}
	
	private JButton getContinueButton(){
		return continueButton;
	}
}
