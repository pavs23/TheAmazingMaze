import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Panel for selecting characters in the game.
 * @author floren
 *
 */

public class SelectCharacterPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JRadioButton button4;
    private JRadioButton button5;
    private JRadioButton button6;
    
    ImageIcon player0;
    ImageIcon player1;
    ImageIcon player2;
    
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    
    private StyledButton done;
    private JPanel current;
    private BackButton backButton;
    
    private final int X_LABEL_POSITION = 240;
	private final int Y_LABEL_POSITION = 120;
	
	private final int X_IMAGE_LABEL_POSITION = 255;
	private final int Y_IMAGE_LABEL_POSITION = 190;
	
	private final int X_BUTTON = 150;
    
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
        
        JLabel title;
        JLabel playerOneLabel;
        JLabel playerTwoLabel;
        
        JLabel panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
        
        File selectCharacterFile = new File("Select_Character.jpg");
        Image selectCharacterImage = null;
        try {
            selectCharacterImage = ImageIO.read(selectCharacterFile);
        } catch (IOException e) {}
        title = new JLabel();
        title.setSize(new Dimension(400, 80));
        title.setLocation(new Point(100, 20));
        Image scaledSelectCharacter = selectCharacterImage.getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH);    
        ImageIcon selectCharacterIcon = new ImageIcon(scaledSelectCharacter);
        title.setIcon(selectCharacterIcon);
        
        playerOneLabel = new JLabel();
        playerOneLabel.setSize(new Dimension(100,20));
        playerOneLabel.setIcon(new ImageIcon(Game.PLAYER_1_TEXT_IMAGE.getScaledInstance(playerOneLabel.getWidth(),
                playerOneLabel.getHeight(), Image.SCALE_SMOOTH)));
        playerOneLabel.setLocation(new Point(X_LABEL_POSITION, Y_LABEL_POSITION));
        
        button1 = new JRadioButton("Mario");
        button1.setSize(new Dimension(90, 30));
        button1.setLocation(new Point(X_BUTTON , 145));
        button1.setBackground(Color.WHITE);
        button1.setSelected(true);
        
        button2 = new JRadioButton("Peach");
        button2.setSize(new Dimension(90, 30));
        button2.setBackground(Color.WHITE);
        button2.setLocation(new Point(X_BUTTON  + 100, 145));
        
        button3 = new JRadioButton("Yoshi");
        button3.setSize(new Dimension(90, 30));
        button3.setBackground(Color.WHITE);
        button3.setLocation(new Point(X_BUTTON  + 200, 145));
        
        button4 = new JRadioButton("Mario");
        button4.setSize(new Dimension(90, 30));
        button4.setBackground(Color.WHITE);
        button4.setLocation(new Point(X_BUTTON , 350));
        button4.setSelected(true);
        
        button5 = new JRadioButton("Peach");
        button5.setSize(new Dimension(90, 30));
        button5.setBackground(Color.WHITE);
        button5.setLocation(new Point(X_BUTTON  + 100, 350));
        
        button6 = new JRadioButton("Yoshi");
        button6.setBackground(Color.WHITE);
        button6.setSize(new Dimension(90, 30));
        button6.setLocation(new Point(X_BUTTON  + 200, 350));
        
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        
        ButtonGroup group2 = new ButtonGroup();
        group2.add(button4);
        group2.add(button5);
        group2.add(button6);
        
        playerTwoLabel = new JLabel();
        playerTwoLabel.setSize(new Dimension(100, 20));
        playerTwoLabel.setIcon(new ImageIcon(Game.PLAYER_2_TEXT_IMAGE.getScaledInstance(playerTwoLabel.getWidth(),
                playerTwoLabel.getHeight(), Image.SCALE_SMOOTH)));
        playerTwoLabel.setLocation(new Point(X_LABEL_POSITION, 330));
        
        imageLabel1 = new JLabel();
        imageLabel1.setSize(new Dimension(CHAR_X_DIMENSION, CHAR_Y_DIMENSION));
        imageLabel1.setLocation(new Point(X_IMAGE_LABEL_POSITION, Y_IMAGE_LABEL_POSITION));
        imageLabel1.setIcon(player0);
        
        imageLabel2 = new JLabel();
        imageLabel2.setSize(new Dimension(CHAR_X_DIMENSION, CHAR_Y_DIMENSION));
        imageLabel2.setLocation(new Point(X_IMAGE_LABEL_POSITION, Y_IMAGE_LABEL_POSITION + 205));
        imageLabel2.setIcon(player0);
        
        done = new StyledButton("Start Game");
        
        done.setSize(new Dimension(150, 40));
        done.setLocation(new Point(220, 510));
        
        backButton = new BackButton(prev, mainFrame, this);
        
        panelLabel.add(title);
        panelLabel.add(playerOneLabel);
        panelLabel.add(playerTwoLabel);
        panelLabel.add(button1);
        panelLabel.add(button2);
        panelLabel.add(button3);
        panelLabel.add(button4);
        panelLabel.add(button5);
        panelLabel.add(button6);
        panelLabel.add(imageLabel1);
        panelLabel.add(imageLabel2);
        
        panelLabel.add(done);
        panelLabel.add(backButton);
        
        this.add(panelLabel);
        
        if (singleOrMulti == Game.SINGLE_PLAYER) {
            playerOneLabel.setVisible(false);
            playerTwoLabel.setVisible(false);
            button4.setVisible(false);
            button5.setVisible(false);
            button6.setVisible(false);
            imageLabel2.setVisible(false);
            done.setLocation(new Point(220, 320));
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
