import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Create a panel when a player lost a game.
 * @author floren
 *
 */
public class LosePanel extends FinishingPanel {
    private static final long serialVersionUID = 1L;
    private JLabel loseLabel;
    
    /**
     * Create panel for losing game.
     * @param frame the frame that contains the panel.
     */
	public LosePanel(JFrame frame) {
		super(frame);
		
		loseLabel = new JLabel();
		loseLabel.setSize(new Dimension(500, 430));
		loseLabel.setLocation(new Point(0, 0));
		loseLabel.setLayout(null);
		loseLabel.setBackground(Color.WHITE);
		
		File loseFile = new File("lose.jpg");
		File youLoseFile = new File("You_Lose.jpg");
		Image loseImage = null;
		Image youLoseImage = null;
		try {
		    loseImage = ImageIO.read(loseFile);
		    youLoseImage = ImageIO.read(youLoseFile);
		} catch (IOException e) {}
		
		JLabel messageIcon = new JLabel();
		messageIcon.setIcon(new ImageIcon(youLoseImage.getScaledInstance(400, 120, Image.SCALE_SMOOTH)));
		messageIcon.setSize(new Dimension(400, 120));
		messageIcon.setLocation(new Point(100, 10));
		
		JLabel cryIcon = new JLabel();
		cryIcon.setIcon(new ImageIcon(loseImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
		cryIcon.setSize(new Dimension(250, 250));
		cryIcon.setLocation(new Point(175, 140));
		
		loseLabel.add(messageIcon);
		loseLabel.add(cryIcon);
		
		setMessage(loseLabel);
	}
}
