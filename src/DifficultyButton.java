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
	
	public DifficultyButton(String text, Point p){
		
		this.setLocation(p);
		this.setText(text);
		this.setSize(150, 90);
		this.setBackground(Color.gray);
		this.setForeground(Color.white);
	}
}
