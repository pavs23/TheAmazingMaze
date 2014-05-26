import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NameEntryPanel extends JPanel{
	
    private JTextField playerOneNameField;
    private JTextField playerTwoNameField;
	private JLabel title;
	private JLabel playerOneLabel;
	private JLabel playerTwoLabel;
	private StyledButton done;
	private JPanel current;
	private BackButton backButton;
	private static final int X_POSITION = 250;
	private static final int Y_POSITION = 80;
	private static final int MAX_CHAR = 15;
	
	public NameEntryPanel(final JFrame mainFrame, final int mode, final JPanel prev, final int singleOrMulti, final int difficulty){
		current = this;
		this.setSize(new Dimension(600, 600));
		this.setLayout(null);
		
        title = new JLabel();
        title.setSize(new Dimension(200, 30));
        
        JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
        playerOneLabel = new JLabel("Player 1:");
        playerOneLabel.setSize(new Dimension(100,30));
        playerOneLabel.setLocation(new Point(X_POSITION, Y_POSITION));
        
        playerOneNameField = new JTextField();
        playerOneNameField.setSize(new Dimension(100, 30));
        playerOneNameField.setLocation(new Point(X_POSITION, Y_POSITION + 25));        //Make sure to change all this when it's made into full screen
        
        playerTwoLabel = new JLabel("Player 2:");
        playerTwoLabel.setSize(new Dimension(100, 30));
        playerTwoLabel.setLocation(new Point(X_POSITION, Y_POSITION + 70));
        
        playerTwoNameField = new JTextField();
        playerTwoNameField.setSize(new Dimension(100,30));
        playerTwoNameField.setLocation(new Point(X_POSITION, Y_POSITION + 95));
        
        done = new StyledButton();
        done.setText("Select Characters");
        done.setSize(new Dimension(140, 40));
        done.setLocation(new Point(X_POSITION - 15, Y_POSITION + 170));
        done.setEnabled(false);
        
        backButton = new BackButton(prev, mainFrame, this);
        
        panelLabel.add(title);
        panelLabel.add(playerOneLabel);
        panelLabel.add(playerOneNameField);
        panelLabel.add(playerTwoLabel);
        panelLabel.add(playerTwoNameField);
        panelLabel.add(done);
        panelLabel.add(backButton);
        
        this.add(panelLabel);
        
        if (singleOrMulti == Game.SINGLE_PLAYER) {
        	
            title.setText("Enter Your Name (Max " + MAX_CHAR + " Char)");
            title.setLocation(new Point(X_POSITION - 40, 40));
            playerOneLabel.setVisible(false);
            playerTwoLabel.setVisible(false);
            playerTwoNameField.setVisible(false);
            playerTwoNameField.setText("single_player");
        } else {
        	title.setLocation(new Point(X_POSITION - 50, 40));
        	title.setText("Enter Your Names (Max " + MAX_CHAR + " Char Each)");
        }
        
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
		          } else if (playerOneNameField.getText().length() > MAX_CHAR
		              || playerTwoNameField.getText().length() > MAX_CHAR) {
		              done.setEnabled(false);
		          } else {
		              done.setEnabled(true);
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
                }
            }
      });
	
	}
}
