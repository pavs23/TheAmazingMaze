import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * 
 * @author pavan
 *
 */

public class NumberOfPlayersButton extends StyledButton{

	/**
	 * 
	 * @param mode : refers to the mode being either Adventure or Coin
	 * @param text : the text which the button should display
	 */
	public NumberOfPlayersButton(int mode, String text) {
		this.setSize(new Dimension(150, 70));
		this.setText(text);
		//Setting borders to the button
	}
}
