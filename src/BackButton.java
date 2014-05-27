import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BackButton extends StyledButton{
    private static final long serialVersionUID = 1L;
    
    /**
     * Create a back button constructor.
     * @param prev the previous panel that the back button will go to.
     * @param mainFrame the frame that contains the panel.
     * @param currentPanel the current panel that contains the button.
     */

	public BackButton(final JPanel prev, final JFrame mainFrame, final JPanel currentPanel){
		super("Back");
		this.setSize(new Dimension(120,40));
		this.setVisible(true);
		
		this.setFont(new Font("AR Destine", Font.PLAIN, 25));
		this.setLocation(new Point(20, 510));
		
		this.setBackground(Color.RED);
        this.setForeground(Color.WHITE);
	        
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				currentPanel.setVisible(false);
				remove(currentPanel);
				mainFrame.add(prev);
				prev.setVisible(true);
			}
		});
	}

}
