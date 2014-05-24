import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author pavan
 *
 */

public class DifficultySelection extends JPanel {

	private DifficultyButton easyButton;
	private DifficultyButton mediumButton;
	private DifficultyButton hardButton;
	private BackButton backButton;
	private JPanel current;
	
	private static final int xPositionButton = 225;
	private static final int yPositionButton = 100;
	private static final int vGap = 30;
	
	//public JFrame difficultySelection;
	
	
	public DifficultySelection(final JFrame mainFrame, final int mode, final int players, final JPanel prev){
		
		easyButton = new DifficultyButton("Easy", new Point(xPositionButton, yPositionButton));
		mediumButton = new DifficultyButton("Medium", new Point(xPositionButton, (2 * yPositionButton + vGap)));
		hardButton = new DifficultyButton("Hard", new Point(xPositionButton,3 * yPositionButton + (2 * vGap)));
		backButton = new BackButton(new Point(20, 480), prev, mainFrame, this);
		current = this;
		
		this.add(easyButton);
		this.add(mediumButton);
		this.add(hardButton);
		this.add(backButton);
		this.setSize(new Dimension(600, 600));
		this.setLayout(null);
		
		easyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create an easy game");
				NameEntryPanel nameEntry = new NameEntryPanel(mainFrame, mode, current, players, Game.EASY);
				current.setVisible(false);
				mainFrame.add(nameEntry);
				nameEntry.setVisible(true);		
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a medium game");
				NameEntryPanel nameEntry = new NameEntryPanel(mainFrame, mode, current, players, Game.MEDIUM);
				current.setVisible(false);
				mainFrame.add(nameEntry);
				nameEntry.setVisible(true);
				
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Create a hard game");
				NameEntryPanel nameEntry = new NameEntryPanel(mainFrame, mode, current, players, Game.HARD);
				current.setVisible(false);
				mainFrame.add(nameEntry);
				nameEntry.setVisible(true);

			}
		});
				
	}
	
	
	public void run(){
		this.setVisible(true);
		//difficultySelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
