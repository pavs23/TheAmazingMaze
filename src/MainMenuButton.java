import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * A class to create main menu button.
 * @author floren
 *
 */
public class MainMenuButton extends StyledButton {
    private static final long serialVersionUID = 1L;
    JFrame disposedFrame;
    
    /**
     * Create a mainMenu button.
     * @param frame the frame that will be disposed after returning to main menu.
     */
    public MainMenuButton(JFrame frame) {        
        super("Main Menu");
        disposedFrame = frame;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    
                disposedFrame.dispose(); 
                new Game().run();
            }
        });
    }   
}
