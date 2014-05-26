import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class DifficultyButton extends StyledButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DifficultyButton(String text, Point p, Insets inset, Dimension d){
		this.setBounds(inset.left + p.x, inset.top + p.y, d.width, d.height );
		this.setLocation(p);
		this.setText(text);
		
		Font myFont = new Font("Monospaced", Font.PLAIN, 25);
		this.setFont(myFont);
		this.setForeground(Color.black);
		this.setBackground(Color.black);
		this.setOpaque(false);
		
		
		//ImageIcon icon = new ImageIcon(WALL_IMAGE); 
		//JLabel thumb = new JLabel();
		//thumb.setIcon(icon);

	}

}
