
import java.awt.Color;

import java.awt.Point;

import javax.swing.JLabel;




public class DifficultyButton extends StyledButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DifficultyButton(JLabel difficultyLabel, Point p){

		this.setLocation(p);
		//this.setText(text);

		this.setSize(150, 90);	
		this.add(difficultyLabel);
		//Font myFont = new Font("AR Destine", Font.PLAIN, 25);

		//this.setFont(myFont);
		//this.setBackground(Color.black);

		//this.setForeground(Color.white);
	}
}
