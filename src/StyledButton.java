import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;


public class StyledButton extends JButton{

	 public StyledButton (){
		 this.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
		 //this.setFont(this.getFont().deriveFont(16.0f));
		
		 
	 }
}
