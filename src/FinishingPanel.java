import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class FinishingPanel extends JPanel{

	private JLabel message;
	
	public FinishingPanel(String text){
		this.setSize(new Dimension(500, 500));
		message.setText(text);
	}
}
