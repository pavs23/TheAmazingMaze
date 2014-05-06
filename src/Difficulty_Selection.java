import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Difficulty_Selection extends JFrame {

	private JButton easyButton;
	private JButton mediumButton;
	private JButton hardButton;
	private JButton backButton;
	public JFrame difficultySelection;
	
	
	public Difficulty_Selection(){
		difficultySelection = new JFrame("Select Difficulty");
		
		easyButton = new JButton("Easy");
		mediumButton = new JButton("Medium");
		hardButton = new JButton("Hard");
		backButton = new JButton("Back");
		
		difficultySelection.add(easyButton);
		difficultySelection.add(mediumButton);
		difficultySelection.add(hardButton);
		difficultySelection.add(backButton);
		
		Dimension mainFrameDimension = new Dimension(500, 500);
		difficultySelection.setSize(mainFrameDimension);
		
		difficultySelection.setLocationRelativeTo(null);
		difficultySelection.setLayout(null);
		
		Insets insets = difficultySelection.getInsets();
		easyButton.setBounds(insets.left + 175, insets.top + 80, 150, 70);
		easyButton.setVisible(true);
		mediumButton.setBounds(insets.left + 175, insets.top + 180, 150, 70);
		mediumButton.setVisible(true);
		hardButton.setBounds(insets.left + 175, insets.top + 280, 150, 70);
		hardButton.setVisible(true);
		backButton.setBounds(insets.left + 20, insets.top + 380, 100, 40);
		backButton.setVisible(true);
		
		easyButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create an easy game");
				
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");
				
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");
				
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go back to the main menu");
				//difficultySelection.dispose();
				//Figure out how to get back to the main menu from here...
			}
		});
		
	}
	
	public void run(){
		difficultySelection.setVisible(true);
		difficultySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
