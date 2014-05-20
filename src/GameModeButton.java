import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;


public class GameModeButton extends StyledButton{
	public GameModeButton(String mode, Color background){
		this.setText(mode);
		this.setSize(new Dimension (200, 132));
		this.setBackground(background);
	}

}
