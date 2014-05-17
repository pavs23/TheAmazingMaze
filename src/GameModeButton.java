import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;


public class GameModeButton extends JButton{
	public GameModeButton(String mode, Color background){
		this.setText(mode);
		this.setSize(new Dimension (200, 132));
		this.setBackground(background);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
	}

}
