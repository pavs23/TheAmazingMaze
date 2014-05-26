import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Panel for selecting characters in the game.
 * @author floren
 *
 */

public class SelectCharacterPanel extends JPanel {
    private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JRadioButton button4;
    private JRadioButton button5;
    private JRadioButton button6;
    
    ImageIcon player0;
    ImageIcon player1;
    ImageIcon player2;
    
    private JLabel title;
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    
    private StyledButton done;
    private JPanel current;
    private BackButton backButton;
    
    private final int xLabelPosition = 240;
	private final int yLabelPosition = 80;
	
	private final int xImageLabelPosition = 240;
	private final int yImageLabelPosition = 140;
	
	private final int xButton= 150;
	private final int yButton = 80;
    
    private static final int CHAR_X_DIMENSION = 75;
    private static final int CHAR_Y_DIMENSION = 100;
    
    public SelectCharacterPanel(final JFrame mainFrame, final int mode, final JPanel prev, final int singleOrMulti, 
                                final int difficulty, final String player1Name, final String player2Name){
        
        // Set the image dimension.
        Image scaledPlayer0 = Game.PLAYER_0_IMAGE.getScaledInstance(CHAR_X_DIMENSION, CHAR_Y_DIMENSION, Image.SCALE_SMOOTH);
        Image scaledPlayer1 = Game.PLAYER_1_IMAGE.getScaledInstance(CHAR_X_DIMENSION, CHAR_Y_DIMENSION, Image.SCALE_SMOOTH);
        Image scaledPlayer2 = Game.PLAYER_2_IMAGE.getScaledInstance(CHAR_X_DIMENSION, CHAR_Y_DIMENSION, Image.SCALE_SMOOTH);
                    
        // Create icon from image.        
        player0 = new ImageIcon(scaledPlayer0);
        player1 = new ImageIcon(scaledPlayer1);
        player2 = new ImageIcon(scaledPlayer2);
        
        current = this;
        this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        
        File selectCharacterFile = new File("Select_Character.jpg");
        Image selectCharacterImage = null;
        try {
            selectCharacterImage = ImageIO.read(selectCharacterFile);
        } catch (IOException e) {}
        title = new JLabel();
        title.setSize(new Dimension(420, 80));
        title.setLocation(new Point(90, 20));
        Image scaledSelectCharacter = selectCharacterImage.getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon selectCharacterIcon = new ImageIcon(scaledSelectCharacter);
        title.setIcon(selectCharacterIcon);
        
        playerOneLabel = new JLabel("Player 1:");
        playerOneLabel.setSize(new Dimension(100,30));
        playerOneLabel.setLocation(new Point(xLabelPosition, yLabelPosition));
        
        button1 = new JRadioButton("Mario");
        button1.setSize(new Dimension(90, 30));
        button1.setLocation(new Point(xButton, 110));
        button1.setSelected(true);
        
        button2 = new JRadioButton("Peach");
        button2.setSize(new Dimension(90, 30));
        button2.setLocation(new Point(xButton + 100, 110));
        
        button3 = new JRadioButton("Yoshi");
        button3.setSize(new Dimension(90, 30));
        button3.setLocation(new Point(xButton + 200, 110));
        
        button4 = new JRadioButton("Mario");
        button4.setSize(new Dimension(90, 30));
        button4.setLocation(new Point(xButton, 250));
        button4.setSelected(true);
        
        button5 = new JRadioButton("Peach");
        button5.setSize(new Dimension(90, 30));
        button5.setLocation(new Point(xButton + 100, 250));
        
        button6 = new JRadioButton("Yoshi");
        button6.setSize(new Dimension(90, 30));
        button6.setLocation(new Point(xButton + 200, 250));
        
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        
        ButtonGroup group2 = new ButtonGroup();
        group2.add(button4);
        group2.add(button5);
        group2.add(button6);
        
        playerTwoLabel = new JLabel("Player 2:");
        playerTwoLabel.setSize(new Dimension(100, 30));
        playerTwoLabel.setLocation(new Point(xLabelPosition, 220));
        
        imageLabel1 = new JLabel();
        imageLabel1.setSize(new Dimension(CHAR_X_DIMENSION, CHAR_Y_DIMENSION));
        imageLabel1.setLocation(new Point(xImageLabelPosition, yImageLabelPosition));
        imageLabel1.setIcon(player0);
        
        imageLabel2 = new JLabel();
        imageLabel2.setSize(new Dimension(CHAR_X_DIMENSION, CHAR_Y_DIMENSION));
        imageLabel2.setLocation(new Point(xImageLabelPosition, yImageLabelPosition + 150));
        imageLabel2.setIcon(player0);
        
        done = new StyledButton();
        done.setText("Start Game!");
        done.setSize(new Dimension(100, 40));
        done.setLocation(new Point(240, 390));
        
        backButton = new BackButton(prev, mainFrame, this);
        
        this.add(title);
        this.add(playerOneLabel);
        this.add(playerTwoLabel);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(imageLabel1);
        this.add(imageLabel2);
        
        this.add(done);
        this.add(backButton);
        
        if (singleOrMulti == Game.SINGLE_PLAYER) {
            playerOneLabel.setVisible(false);
            playerTwoLabel.setVisible(false);
            button4.setVisible(false);
            button5.setVisible(false);
            button6.setVisible(false);
            imageLabel2.setVisible(false);
            done.setLocation(new Point(200, 250));
        }
        
        done.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int char1 = 0;
                int char2 = 0;
                if (button1.isSelected()) {
                    char1 = Game.PLAYER_0;
                } else if (button2.isSelected()) {
                    char1 = Game.PLAYER_1;
                } else if (button3.isSelected()) {
                    char1 = Game.PLAYER_2;
                }
                
                if (singleOrMulti == Game.MULTI_PLAYER) {
                    if (button4.isSelected()) {
                        char2 = Game.PLAYER_0;
                    } else if (button5.isSelected()) {
                        char2 = Game.PLAYER_1;
                    } else if (button6.isSelected()) {
                        char2 = Game.PLAYER_2;
                    }
                }
                new MazeGame(singleOrMulti, char1, player1Name, char2, player2Name, mode, difficulty);

                mainFrame.remove(prev);
                mainFrame.dispose();
                current.setVisible(false);
            }
            
        });
        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                imageLabel1.setIcon(player0);
            }
        });
        
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                imageLabel1.setIcon(player1);
            }
        });
        
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                imageLabel1.setIcon(player2);
            }
        });
        
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                imageLabel2.setIcon(player0);
            }
        });
        
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                imageLabel2.setIcon(player1);
            }
        });
        
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                imageLabel2.setIcon(player2);
            }
        });   
    
    }
}
