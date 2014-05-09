import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Difficulty_Selection extends JFrame {

	private Difficulty_Button easyButton;
	private Difficulty_Button mediumButton;
	private Difficulty_Button hardButton;
	private BackButton backButton;
	public JFrame difficultySelection;
	
	
	public Difficulty_Selection(JFrame mainMenu){
		difficultySelection = new JFrame("Select Difficulty");
		
		Insets insets = difficultySelection.getInsets();
		
		easyButton = new Difficulty_Button("Easy", new Point(175, 80), insets, new Dimension(150, 70));
		mediumButton = new Difficulty_Button("Medium", new Point(175, 180), insets, new Dimension(150, 70));
		hardButton = new Difficulty_Button("Hard", new Point(175, 280), insets, new Dimension(150, 70));
		backButton = new BackButton(new Point(20, 380));
		
		difficultySelection.add(easyButton);
		difficultySelection.add(mediumButton);
		difficultySelection.add(hardButton);
		difficultySelection.add(backButton);
		
		Dimension mainFrameDimension = new Dimension(500, 500);
		difficultySelection.setSize(mainFrameDimension);
		
		difficultySelection.setLocationRelativeTo(null);
		difficultySelection.setLayout(null);
		
		//Setting the positions of the buttons
		//Insets insets = difficultySelection.getInsets();
		//easyButton.setBounds(insets.left + 175, insets.top + 80, 150, 70);
		//mediumButton.setBounds(insets.left + 175, insets.top + 180, 150, 70);
		//hardButton.setBounds(insets.left + 175, insets.top + 280, 150, 70);

		
		easyButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create an easy game");
				new MazeGame();
				
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");
				new MazeGame();
				
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");
				new MazeGame();
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go back to the main menu");
				MainScreen main = new MainScreen();
				main.run();
				difficultySelection.dispose();
				
				//Figure out how to get back to the main menu from here...
			}
		});
		
	}
	
	public void run(){
		difficultySelection.setVisible(true);
		difficultySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
