import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;


public class FinishingPanel extends JPanel {
    private JPanel message;
    private static final long serialVersionUID = 1L;
    
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
			
		MainMenuButton menu = new MainMenuButton(frame);
		menu.setLocation(new Point(225, 450));
		menu.setSize(new Dimension(150, 40));
		
		QuitButton quit = new QuitButton(frame);
		quit.setLocation(new Point(225, 500));
		quit.setSize(new Dimension(150, 40));
		
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
