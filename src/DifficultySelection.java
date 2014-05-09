import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DifficultySelection extends JPanel {

	private Difficulty_Button easyButton;
	private Difficulty_Button mediumButton;
	private Difficulty_Button hardButton;
	private BackButton backButton;
	//public JFrame difficultySelection;
	
	
	public DifficultySelection(final JFrame mainMenu){
		//difficultySelection = new JFrame("Select Difficulty");
		
		Insets insets = mainMenu.getInsets();
		
		easyButton = new Difficulty_Button("Easy", new Point(175, 80), insets, new Dimension(150, 70));
		mediumButton = new Difficulty_Button("Medium", new Point(175, 180), insets, new Dimension(150, 70));
		hardButton = new Difficulty_Button("Hard", new Point(175, 280), insets, new Dimension(150, 70));
		backButton = new BackButton(new Point(20, 380));
		
		this.add(easyButton);
		this.add(mediumButton);
		this.add(hardButton);
		this.add(backButton);
		
		Dimension mainFrameDimension = new Dimension(500, 500);
		this.setSize(mainFrameDimension);
		
		//this.setLocationRelativeTo(null);
		this.setLayout(null);
		
	
		easyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create an easy game");
				mainMenu.dispose();
				new MazeGame();
				
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");
				mainMenu.dispose();
				new MazeGame();
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");
				mainMenu.dispose();
				new MazeGame();
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go back to the main menu");
				MainPanel panel = new MainPanel(new Dimension(500, 500));
				MainScreen main = new MainScreen();
				mainMenu.dispose();
				main.run();
				
				
				//MainScreen main = new MainScreen();
				//main.run();
				//this.dispose();
			}
		});
		
	}
	
	public void run(){
		this.setVisible(true);
		//difficultySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
