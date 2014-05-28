import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;

/**
 * A class to create the finish panel of the game.
 * @author floren
 *
 */
public class FinishingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel message;
    private MainMenuButton menu;
    private QuitButton quit;
    
	/**
	 * Create a finishing panel.
	 * @param frame the frame associated with the panel.
	 */
	public FinishingPanel(JFrame frame){
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setVisible(true);
		
		message = new JPanel();
		message.setSize(new Dimension(600, 430));
		message.setLocation(new Point(0, 0));
		message.setLayout(null);
		message.setBackground(Color.WHITE);
			
		menu = new MainMenuButton(frame);
		menu.setLocation(new Point(225, 410));
		
		quit = new QuitButton(frame);
		quit.setLocation(new Point(225, 480));
		
		this.add(message);
		this.add(menu);
		this.add(quit);
	}
	
	/**
	 * Add the message to the panel.
	 * @param messageLabel the message that wants to be shown in finishing panel.
	 */
	public void setMessage(JLabel messageLabel) {
	    message.add(messageLabel);
	}
}
