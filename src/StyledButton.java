import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.*;


public class StyledButton extends JButton{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StyledButton() {
		 this.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
		 this.setOpaque(true);
		 final StyledButton curr = this;
		 this.setBackground(Color.ORANGE);
		 this.setForeground(Color.BLACK);
		 this.setFont(new Font("Arial", Font.PLAIN, 20));
		 this.addMouseListener(new MouseAdapter(){
		     public void mouseEntered(MouseEvent e) {
		         curr.setFont(curr.getFont().deriveFont(Font.BOLD));
		     }
             public void mouseExited(MouseEvent e) {
                 curr.setFont(curr.getFont().deriveFont(Font.PLAIN));        
             }
		 });
	 }
	 
	public StyledButton(String text) {
	    this();
	    this.setText(text);   
	}
}
