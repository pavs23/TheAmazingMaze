import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NameEntrySinglePlayer extends JPanel{
	
	private JTextField nameField;
	private JLabel title;
	private StyledButton done;
	private JPanel current;
	private BackButton backButton;
	
	public NameEntrySinglePlayer(final JFrame mainFrame, final int mode, final JPanel prev){
		current = this;
		this.setSize(new Dimension(500, 500));
		this.setLayout(null);
		//this.setSize(new Dimension(40, 20));
		
		backButton = new BackButton(new Point(20, 380), prev, mainFrame, this);
		
		nameField = new JTextField();
		nameField.setSize(new Dimension(100, 20));
		nameField.setLocation(new Point(200, 100));		//Make sure to change all this when it's made into full screen
		
		title = new JLabel("Enter your player name");
		title.setSize(new Dimension(250,40));
		title.setLocation(new Point(150, 30));
		
		done = new StyledButton();
		done.setText("Done");
		done.setSize(new Dimension(100, 40));
		done.setLocation(new Point(200, 150));
		
		this.add(nameField);
		this.add(title);
		this.add(done);
		this.add(backButton);
		
		done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String playerName = nameField.getText();
				//Add difficulty selection here
				DifficultySelection dsScreen = new DifficultySelection(mainFrame, mode, Game.SINGLE_PLAYER, current, playerName);
				mainFrame.add(dsScreen);
				mainFrame.remove(prev);
				dsScreen.setVisible(true);
				current.setVisible(false);
			}
			
		});
		
	}
	
	

}
