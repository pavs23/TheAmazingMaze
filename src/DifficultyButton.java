
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;




public class DifficultyButton extends StyledButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DifficultyButton(JLabel difficultyLabel, Point p){

		this.setLocation(p);

		this.setSize(150, 90);	
		this.add(difficultyLabel);
		this.setOpaque(true);

	}
}
