
import java.awt.Point;

import javax.swing.JLabel;

/**
 * 
 * @author pavan
 * 
 */

public class NumberOfPlayersButton extends StyledButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberOfPlayersButton(JLabel numOfPlayerLabel, Point p) {

		this.setLocation(p);

		this.setSize(150, 70);
		this.add(numOfPlayerLabel);
		this.setOpaque(true);

	}
}
