import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.*;

/**
 * Create a button with specific style for the game.
 * @author floren & JosephineJs & pavan
 *
 */
public class StyledButton extends JButton{
	private static final long serialVersionUID = 1L;

	/**
	 * Create a button with a specific style for the game.
	 * @param text the text that will be shown inside the button.
	 */
	public StyledButton(String text) {
		 this.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
		 this.setOpaque(true);
		 final StyledButton curr = this;
		 this.setBackground(Color.ORANGE);
		 this.setForeground(Color.BLACK);
		 this.setText(text); 
		 this.setFont(new Font("Arial", Font.PLAIN, 20));
		 this.setSize(new Dimension(150, 50));
		 this.setPreferredSize(new Dimension(150, 50));
		 this.addMouseListener(new MouseAdapter(){
		     public void mouseEntered(MouseEvent e) {
		         if (curr.isEnabled()) {
		             curr.setFont(curr.getFont().deriveFont(Font.BOLD));
		         } else {
		             curr.setFont(curr.getFont().deriveFont(Font.PLAIN));
		         }
		     }
             public void mouseExited(MouseEvent e) {
                 curr.setFont(curr.getFont().deriveFont(Font.PLAIN));        
             }
             public void mouseReleased(MouseEvent e) {
                 curr.setFont(curr.getFont().deriveFont(Font.PLAIN)); 
             }
		 });
	 }
	 
}
