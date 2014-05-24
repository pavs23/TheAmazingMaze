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
	private final int xPosition = 250;
	private final int yPosition = 80;
	
	public NameEntryPanel(final JFrame mainFrame, final int mode, final JPanel prev, final int singleOrMulti, final int difficulty){
		current = this;
		this.setSize(new Dimension(600, 600));
		this.setLayout(null);
		
        title = new JLabel();
        title.setSize(new Dimension(xPosition, yPosition - 40));
        title.setLocation(new Point(190, 30));
        
        playerOneLabel = new JLabel("Player 1:");
        playerOneLabel.setSize(new Dimension(100,30));
        playerOneLabel.setLocation(new Point(xPosition, yPosition));
        
        playerOneNameField = new JTextField();
        playerOneNameField.setSize(new Dimension(100, 30));
        playerOneNameField.setLocation(new Point(xPosition, yPosition + 25));        //Make sure to change all this when it's made into full screen
        
        playerTwoLabel = new JLabel("Player 2:");
        playerTwoLabel.setSize(new Dimension(100, 30));
        playerTwoLabel.setLocation(new Point(xPosition, yPosition + 70));
        
        playerTwoNameField = new JTextField();
        playerTwoNameField.setSize(new Dimension(100,30));
        playerTwoNameField.setLocation(new Point(xPosition, yPosition + 95));
        
        done = new StyledButton();
        done.setText("Select Characters");
        done.setSize(new Dimension(140, 40));
        done.setLocation(new Point(xPosition - 15, yPosition + 170));
        done.setEnabled(false);
        
        backButton = new BackButton(new Point(20, 480), prev, mainFrame, this);
        
        this.add(title);
        this.add(playerOneLabel);
        this.add(playerOneNameField);
        this.add(playerTwoLabel);
        this.add(playerTwoNameField);
        this.add(done);
        this.add(backButton);
        
        if (singleOrMulti == Game.SINGLE_PLAYER) {
        	
            title.setText("Enter Your Name");
            title.setLocation(new Point(xPosition, 40));
            playerOneLabel.setVisible(false);
            playerTwoLabel.setVisible(false);
            playerTwoNameField.setVisible(false);
            playerTwoNameField.setText("single_player");
        } else {
        	title.setLocation(new Point(xPosition - 10, 40));
            title.setText("Enter Your Names");
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
                } else {
                    done.setEnabled(true);
                }
            }
      });
	
	}
}
