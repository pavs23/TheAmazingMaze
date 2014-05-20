import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NameEntryDoublePlayer extends JPanel{
	
	private JTextField playerOneNameField;
	private JTextField playerTwoNameField;
	private JLabel title;
	private StyledButton done;
	private JPanel current;
	private BackButton backButton;
	private DifficultySelection dsScreen; 
	
	public NameEntryDoublePlayer(final JFrame mainFrame, final int mode, final JPanel prev, final int difficulty){
		current = this;
		
		this.setSize(new Dimension(500, 500));
		this.setLayout(null);
		
		backButton = new BackButton(new Point(20, 380), prev, mainFrame, this);
		
		playerOneNameField = new JTextField();
		playerOneNameField.setSize(new Dimension(100, 20));
		playerOneNameField.setLocation(new Point(200, 100));		//Make sure to change all this when it's made into full screen
		
		playerTwoNameField = new JTextField();
		playerTwoNameField.setSize(new Dimension(100,20));
		playerTwoNameField.setLocation(new Point(200, 150));
		
		title = new JLabel("Enter your player name");
		title.setSize(new Dimension(250,40));
		title.setLocation(new Point(150, 30));
		
		done = new StyledButton();
		done.setText("Done");
		done.setSize(new Dimension(100, 40));
		done.setLocation(new Point(200, 200));
		
		this.add(playerOneNameField);
		this.add(playerTwoNameField);
		this.add(title);
		this.add(done);
		this.add(backButton);
		
		done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String playerOne = playerOneNameField.getText();
				String playerTwo = playerTwoNameField.getText();
				//Add difficulty selection here
				
				new MazeGame(Game.PLAYER_0, playerOne, Game.PLAYER_1, playerTwo, mode, difficulty);
				
				current.setVisible(false);
			}
		});
	}
}
