import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Create a panel for name entry.
 * @author floren
 *
 */
public class NameEntryPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField playerOneNameField;
    private JTextField playerTwoNameField;
	private StyledButton done;
	private NameEntryPanel current;
	private JLabel panelLabel;
	private BackButton backButton;
	
	private static final int X_POSITION = 215;
	private static final int Y_POSITION = 130;
	private static final int MAX_CHAR = 15;
	
    /**
	 * Create a name entry panel.
     * @param mainFrame the frame where the panel will be made.
     * @param mode the mode of the game (coin/adventure).
     * @param prev the previous panel before this panel is made.
     * @param singleOrMulti the number of players in the game.
     * @param difficulty the difficulty of the game.
    */
	public NameEntryPanel(final JFrame mainFrame, final int mode, final JPanel prev, final int singleOrMulti, final int difficulty) {
		current = this;
		this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
		this.setLayout(null);
		
		panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
		JLabel title;
		JLabel subTitle;
	    JLabel playerOneLabel;
	    JLabel playerTwoLabel;
        
        File enterNameFile = new File("images/Enter_Players_Name.jpg");
        File maxCharFile = new File("images/Max_Char.jpg");
        Image enterNameImage = null;
        Image maxCharImage = null;
        try {
            enterNameImage = ImageIO.read(enterNameFile);
            maxCharImage = ImageIO.read(maxCharFile);
        } catch (IOException e) {}
        
        title = new JLabel();
        title.setSize(new Dimension(400, 60));
        title.setLocation(new Point(100, 20));
        Image scaledEnterName = enterNameImage.getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon enterNameIcon = new ImageIcon(scaledEnterName);
        title.setIcon(enterNameIcon);
        
        subTitle = new JLabel();
        subTitle.setSize(new Dimension(200, 30));
        subTitle.setLocation(new Point(200, 80));
        Image scaledMaxChar = maxCharImage.getScaledInstance(subTitle.getWidth(), subTitle.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon maxCharIcon = new ImageIcon(scaledMaxChar);
        subTitle.setIcon(maxCharIcon);
        
        playerOneLabel = new JLabel();
        playerOneLabel.setSize(new Dimension(100,20));
        playerOneLabel.setIcon(new ImageIcon(Game.PLAYER_1_TEXT_IMAGE.getScaledInstance(playerOneLabel.getWidth(),
                playerOneLabel.getHeight(), Image.SCALE_SMOOTH)));
        playerOneLabel.setLocation(new Point(X_POSITION, Y_POSITION));
        
        playerOneNameField = new JTextField();
        playerOneNameField.setSize(new Dimension(170, 40));
        playerOneNameField.setLocation(new Point(X_POSITION, Y_POSITION + 30));
        
        playerTwoLabel = new JLabel();
        playerTwoLabel.setSize(new Dimension(100, 20));
        playerTwoLabel.setIcon(new ImageIcon(Game.PLAYER_2_TEXT_IMAGE.getScaledInstance(playerTwoLabel.getWidth(),
                playerTwoLabel.getHeight(), Image.SCALE_SMOOTH)));
        playerTwoLabel.setLocation(new Point(X_POSITION, Y_POSITION + 100));
        
        playerTwoNameField = new JTextField();
        playerTwoNameField.setSize(new Dimension(170,40));
        playerTwoNameField.setLocation(new Point(X_POSITION, Y_POSITION + 125));
        
        done = new StyledButton("Submit");
        done.setEnabled(false);
        done.setLocation(new Point(X_POSITION + 15, Y_POSITION + 210));
        
        backButton = new BackButton(prev, mainFrame, this);
        
        panelLabel.add(title);
        panelLabel.add(subTitle);
        panelLabel.add(playerOneLabel);
        panelLabel.add(playerOneNameField);
        panelLabel.add(playerTwoLabel);
        panelLabel.add(playerTwoNameField);
        panelLabel.add(done);
        panelLabel.add(backButton);
        
        this.add(panelLabel);
        
        if (singleOrMulti == Game.SINGLE_PLAYER) {
            playerOneLabel.setVisible(false);
            playerTwoLabel.setVisible(false);
            playerTwoNameField.setVisible(false);
            playerTwoNameField.setText("single_player");
            done.setLocation(new Point(X_POSITION + 15, Y_POSITION + 110));
        }
        
        // Find the button bounds to detect mouse over.
        Rectangle buttonBound = done.getBounds();
        final double buttonWidth = buttonBound.getWidth();
        final double buttonHeight = buttonBound.getHeight();
        final double buttonStartX = buttonBound.getX();
        final double buttonStartY = buttonBound.getY();

        done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String player1Name = playerOneNameField.getText();
				String player2Name = playerTwoNameField.getText();
				SelectCharacterPanel selectChar = new SelectCharacterPanel(mainFrame, mode, current, singleOrMulti, difficulty, player1Name, player2Name);

				current.setVisible(false);
                mainFrame.add(selectChar);
                selectChar.setVisible(true); 
			}
			
		});
		
		playerOneNameField.getDocument().addDocumentListener(new DocumentListener() {
		      public void changedUpdate(DocumentEvent e) {
		        update();
		      }
		      public void removeUpdate(DocumentEvent e) {
		        update();
		      }
		      public void insertUpdate(DocumentEvent e) {
		        update();
		      }
		      private void update() {
		          if (playerOneNameField.getText().trim().length() == 0
		              || playerTwoNameField.getText().trim().length() == 0) {
		              done.setEnabled(false);
		              done.setFont(done.getFont().deriveFont(Font.PLAIN));
		          } else if (playerOneNameField.getText().length() > MAX_CHAR
		              || playerTwoNameField.getText().length() > MAX_CHAR) {
		              done.setEnabled(false);
		              done.setFont(done.getFont().deriveFont(Font.PLAIN));
		          } else {
		              done.setEnabled(true);
		              // Find mouse location and compare.
		              Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
		              Point framePos = mainFrame.getContentPane().getLocationOnScreen();
		              double framePosX = framePos.getX();
		              double framePosY = framePos.getY();
		              double mouseLocX = mouseLoc.getX() - framePosX;
		              double mouseLocY = mouseLoc.getY() - framePosY;
		              
		              if (mouseLocX >= buttonStartX && mouseLocX <= (buttonStartX + buttonWidth)
		                      && mouseLocY >= buttonStartY && mouseLocY <= (buttonStartY + buttonHeight)) {
		                  done.setFont(done.getFont().deriveFont(Font.BOLD));
		              }	                  
		          }
		      }
	    });
		
		playerTwoNameField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              update();
            }
            public void removeUpdate(DocumentEvent e) {
              update();
            }
            public void insertUpdate(DocumentEvent e) {
              update();
            }
            private void update() {
                if (playerOneNameField.getText().trim().length() == 0
                    || playerTwoNameField.getText().trim().length() == 0) {
                    done.setEnabled(false);
                } else if (playerOneNameField.getText().length() > MAX_CHAR
                    || playerTwoNameField.getText().length() > MAX_CHAR) {
                    done.setEnabled(false);
                } else {
                    done.setEnabled(true);
                    // Find mouse location and compare.
                    Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
                    Point framePos = mainFrame.getContentPane().getLocationOnScreen();
                    double framePosX = framePos.getX();
                    double framePosY = framePos.getY();
                    double mouseLocX = mouseLoc.getX() - framePosX;
                    double mouseLocY = mouseLoc.getY() - framePosY;
                    if (mouseLocX >= buttonStartX && mouseLocX <= (buttonStartX + buttonWidth)
                            && mouseLocY >= buttonStartY && mouseLocY <= (buttonStartY + buttonHeight)) {
                        done.setFont(done.getFont().deriveFont(Font.BOLD));
                    } 
                }
            }
      });
	}
}
