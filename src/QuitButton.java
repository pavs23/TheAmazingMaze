import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * A class to create quit button.
 * @author floren
 *
 */
public class QuitButton extends StyledButton {
    private static final long serialVersionUID = 1L;
    private JFrame disposedFrame;
    
    /**
     * Create a quit button.
     * @param frame the frame that will be disposed after quitting the game.
     */
	public QuitButton(JFrame frame) {	    
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
