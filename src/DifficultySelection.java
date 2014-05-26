import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private static final int yPositionButton = 150;
	private static final int xDifficultyLabel = 150;
	private static final int yDifficultyLabel = 90;
	private static final int vGap = 20;
	
	//public JFrame difficultySelection;
	private JLabel easyLabel;
	private JLabel mediumLabel;
	private JLabel hardLabel;
	
	public DifficultySelection(final JFrame mainFrame, final int mode, final int players, final JPanel prev){
		

		File easyFile = new File("easy.png");
		File mediumFile = new File("Medium.png");
		File hardFile = new File("Hard.png");
        Image easyImage = null;
        Image mediumImage = null;
        Image hardImage = null;
        try {
            easyImage = ImageIO.read(easyFile);
            mediumImage = ImageIO.read(mediumFile);
            hardImage = ImageIO.read(hardFile);
        } catch (IOException e) {}
        easyLabel = new JLabel();
        easyLabel.setSize(new Dimension(xDifficultyLabel, yDifficultyLabel));
        easyLabel.setLocation(new Point(100, 20));
        Image scaledEasyButton = easyImage.getScaledInstance(easyLabel.getWidth(), easyLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon easyIcon = new ImageIcon(scaledEasyButton);
        easyLabel.setIcon(easyIcon);
        
        mediumLabel = new JLabel();
        mediumLabel.setSize(new Dimension(xDifficultyLabel, yDifficultyLabel));
        mediumLabel.setLocation(new Point(100, 20));
        Image scaledMediumButton = mediumImage.getScaledInstance(mediumLabel.getWidth(), mediumLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon mediumIcon = new ImageIcon(scaledMediumButton);
        mediumLabel.setIcon(mediumIcon);
        
        hardLabel = new JLabel();
        hardLabel.setSize(new Dimension(xDifficultyLabel, yDifficultyLabel));
        hardLabel.setLocation(new Point(100, 20));
        Image scaledHardButton = hardImage.getScaledInstance(hardLabel.getWidth(), hardLabel.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon hardIcon = new ImageIcon(scaledHardButton);
        hardLabel.setIcon(hardIcon);
		
		easyButton = new DifficultyButton(easyLabel, new Point(xPositionButton, yPositionButton));
		mediumButton = new DifficultyButton(mediumLabel, new Point(xPositionButton, (yPositionButton + yPositionButton/2)));
		hardButton = new DifficultyButton(hardLabel, new Point(xPositionButton, 3 * yPositionButton));
		backButton = new BackButton(prev, mainFrame, this);

		current = this;
		
		this.add(easyButton);
		this.add(mediumButton);
		this.add(hardButton);
		this.add(backButton);
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
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
