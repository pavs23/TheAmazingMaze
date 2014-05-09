import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;


public class BackButton extends JButton{
	public BackButton(Point p){
		this.setText("Back");
		this.setLocation(p);
		this.setSize(new Dimension(100,40));
		this.setVisible(true);
	}

}
