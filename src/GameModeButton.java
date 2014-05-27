import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


public class GameModeButton extends StyledButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameModeButton(String mode, Color background){
		Font myFont = new Font("Monospace", Font.BOLD, 20);
		this.setText(mode);
		this.setFont(myFont);
		this.setSize(new Dimension (200, 132));
		this.setBackground(background);
	}

}
