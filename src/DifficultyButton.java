import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;


public class DifficultyButton extends StyledButton{
	
	public DifficultyButton(String text, Point p, Insets inset, Dimension d){
		this.setBounds(inset.left + p.x, inset.top + p.y, d.width, d.height );
		this.setLocation(p);
		this.setText(text);
		this.setBackground(Color.gray);
		this.setForeground(Color.white);
	}
}
