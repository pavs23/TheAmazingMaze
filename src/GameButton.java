import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.*;


public class GameButton extends JButton{

     public GameButton() {
         this.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
         this.setOpaque(true);
         final GameButton curr = this;
         this.setPreferredSize(new Dimension(150, 50));
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
     
     public GameButton(String text) {
         this();
         this.setText(text);   
     }
}
