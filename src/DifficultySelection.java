import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * @author pavan & Jo
 *
 */

public class DifficultySelection extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StyledButton easyButton;
	private StyledButton mediumButton;
	private StyledButton hardButton;
	private BackButton back;
	private JPanel current;
	
	//public JFrame difficultySelection;
	private JLabel titleLabel;
	
	/**
	 * Select Difficulty Level
	 * @param mainFrame main frame
	 * @param mode game mode
	 * @param players 
	 * @param prev
	 */
	public DifficultySelection(final JFrame mainFrame, final int mode, final int players, final JPanel prev){
		
		final int xPositionButton = 225;
		final int yPositionButton = 150;
		final int lengthDifficultyLabel = 150;
		final int widthDifficultyLabel = 70;
		final int vGap = 30;
		
		File titleFile = new File("selectDifficulty.png");
		
		Image titleImage = null;
        try {
        	titleImage = ImageIO.read(titleFile);
        } catch (IOException e) {}
        
        titleLabel = new JLabel();
        titleLabel.setSize(new Dimension(400, 70));
        titleLabel.setLocation(new Point(100, 20));
        Image scaledTitleButton = titleImage.getScaledInstance(titleLabel.getWidth(), titleLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon titleIcon = new ImageIcon(scaledTitleButton);
        titleLabel.setIcon(titleIcon);
        
        easyButton = new StyledButton("Easy");
		mediumButton = new StyledButton("Medium");
		hardButton = new StyledButton("Hard");
		
		easyButton.setSize(lengthDifficultyLabel, widthDifficultyLabel);
		mediumButton.setSize(lengthDifficultyLabel, widthDifficultyLabel);
		hardButton.setSize(lengthDifficultyLabel, widthDifficultyLabel);
		
		easyButton.setLocation(xPositionButton, yPositionButton);
		mediumButton.setLocation(xPositionButton, yPositionButton + widthDifficultyLabel + vGap);
		hardButton.setLocation(xPositionButton, yPositionButton + 2 * widthDifficultyLabel + 2 * vGap);
		
        back = new BackButton(prev, mainFrame, this);

		current = this;
		
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        this.setLayout(null);
		
		JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
        panelLabel.add(titleLabel);
        panelLabel.add(easyButton);
        panelLabel.add(mediumButton);
        panelLabel.add(hardButton);
        panelLabel.add(back);
        
        this.add(panelLabel);
       
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
