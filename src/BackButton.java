import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BackButton extends StyledButton{
	

	public BackButton(final JPanel prev, final JFrame mainFrame, final JPanel currentPanel){
		this.setSize(new Dimension(120,40));
		this.setVisible(true);
		
		this.setText("Back");
		this.setFont(new Font("AR Destine", Font.PLAIN, 25));
		this.setLocation(new Point(20, 520));
		
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
