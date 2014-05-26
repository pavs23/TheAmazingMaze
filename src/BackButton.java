import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BackButton extends StyledButton{
	

	public BackButton(Point p, final JPanel prev, final JFrame mainFrame, final JPanel currentPanel){
		this.setText("Back");
		this.setLocation(p);
		this.setSize(new Dimension(100,40));
		this.setVisible(true);
		
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
