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
 * A class to create difficulty selection panel.
 * @author pavan & JosephineJs
 *
 */
public class DifficultySelection extends JPanel {
	private static final long serialVersionUID = 1L;
	private StyledButton easyButton;
	private StyledButton mediumButton;
	private StyledButton hardButton;
	private BackButton back;
	private JLabel panelLabel;
	private DifficultySelection current;	
	private JLabel titleLabel;
	
	private static final int WIDTH_BUTTON = 50;
    private final int X_POSITION_BUTTON = 225;
    private final int Y_POSITION_BUTTON = 150;
    private static final int V_GAP = 50; 
    
	/**
	 * Select Difficulty Level
	 * @param mainFrame the frame that will contain this panel.
	 * @param mode coin/adventure mode.
	 * @param players single or multi players.
	 * @param prev the previous panel before this panel.
	 */
	public DifficultySelection(final JFrame mainFrame, final int mode, final int players, final JPanel prev) {		
		File titleFile = new File("images/selectDifficulty.png");
		
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
		
		easyButton.setLocation(X_POSITION_BUTTON, Y_POSITION_BUTTON);
		mediumButton.setLocation(X_POSITION_BUTTON, Y_POSITION_BUTTON + WIDTH_BUTTON + V_GAP);
		hardButton.setLocation(X_POSITION_BUTTON, Y_POSITION_BUTTON + 2 * WIDTH_BUTTON + 2 * V_GAP);
		
        back = new BackButton(prev, mainFrame, this);

		current = this;
		
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        this.setLayout(null);
		
		panelLabel = new JLabel();
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
				NameEntryPanel nameEntry = new NameEntryPanel(mainFrame, mode, current, players, Game.EASY);
				current.setVisible(false);
				mainFrame.add(nameEntry);
				nameEntry.setVisible(true);		
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				NameEntryPanel nameEntry = new NameEntryPanel(mainFrame, mode, current, players, Game.MEDIUM);
				current.setVisible(false);
				mainFrame.add(nameEntry);
				nameEntry.setVisible(true);
				
			}
		});
		
		hardButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				NameEntryPanel nameEntry = new NameEntryPanel(mainFrame, mode, current, players, Game.HARD);
				current.setVisible(false);
				mainFrame.add(nameEntry);
				nameEntry.setVisible(true);
			}
		});				
	}
}
