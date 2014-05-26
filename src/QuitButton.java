import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 * A class to create quit button.
 * @author floren
 *
 */
public class QuitButton extends StyledButton{
    JFrame disposedFrame;
    /**
     * Create a quit button.
     * @param frame the frame that will be disposed after quitting the game.
     */
	public QuitButton(JFrame frame){	    
	    super("Quit");
	    disposedFrame = frame;
	    this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                disposedFrame.dispose();
            }
        });
	}
	
}
