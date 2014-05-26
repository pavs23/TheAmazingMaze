import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author pavan
 *
 */

public class GamePausedPanel extends JPanel{

	private StyledButton continueButton;
	private JLabel title;
	
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
		this.setLayout(null);
		title = new JLabel("Paused");
		title.setFont(title.getFont().deriveFont(64.0f));
		title.setLocation(new Point(100, 30));
		title.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
		this.add(title);
		
	}
	
	private StyledButton getContinueButton(){
		return continueButton;
	}
}
