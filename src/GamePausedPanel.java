import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author floren
 *
 */

public class GamePausedPanel extends JPanel{	
    JLabel pauseLabel;
	/**
	 * Constructor of the class.
	 * @param x the x dimension of pause panel.
	 * @param y the y dimension of pause panel.
	 */
	public GamePausedPanel(int x, int y){
	    File pauseFile = new File("Pause.jpg");
        Image pauseImage = null;
        try {
            pauseImage = ImageIO.read(pauseFile);
        } catch (IOException e) {}
        Image scaledPause = pauseImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);    
        ImageIcon pauseIcon = new ImageIcon(scaledPause);
        
		this.setLayout(new FlowLayout());
		
		pauseLabel = new JLabel();
		pauseLabel.setLayout(new FlowLayout());
		pauseLabel.setIcon(pauseIcon);
		this.add(pauseLabel);
	}
	
	/**
	 * Add a JComponent to the panel.
	 * @param comp the component that will be added to the panel.
	 */
	public void addToPanel(JComponent comp) {
	    pauseLabel.add(comp);
	}
}
