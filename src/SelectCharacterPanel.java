import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
    private JLabel title;
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    
    private StyledButton done;
    private JPanel current;
    private BackButton backButton;
    
    private ImageIcon player0;
    private ImageIcon player1;
    private ImageIcon player2;
    
    private static final int CHAR_DIMENSION = 75;
    
    public SelectCharacterPanel(final JFrame mainFrame, final int mode, final JPanel prev, final int singleOrMulti, 
                                final int difficulty, final String player1Name, final String player2Name){
        
        // Set the image dimension.
        Image scaledPlayer0 = Game.PLAYER_0_FRONT_IMAGE.getScaledInstance(CHAR_DIMENSION, CHAR_DIMENSION, Image.SCALE_SMOOTH);
        Image scaledPlayer1 = Game.PLAYER_1_FRONT_IMAGE.getScaledInstance(CHAR_DIMENSION, CHAR_DIMENSION, Image.SCALE_SMOOTH);
        Image scaledPlayer2 = Game.PLAYER_2_FRONT_IMAGE.getScaledInstance(CHAR_DIMENSION, CHAR_DIMENSION, Image.SCALE_SMOOTH);
                    
        // Create icon from image.        
        player0 = new ImageIcon(scaledPlayer0);
        player1 = new ImageIcon(scaledPlayer1);
        player2 = new ImageIcon(scaledPlayer2);
        
        current = this;
        this.setSize(new Dimension(500, 500));
        this.setLayout(null);
        
        title = new JLabel();
        title.setSize(new Dimension(250, 40));
        title.setLocation(new Point(190, 20));
        
        playerOneLabel = new JLabel("Player 1:");
        playerOneLabel.setSize(new Dimension(100,30));
        playerOneLabel.setLocation(new Point(200, 80));
        
        button1 = new JRadioButton("Boy");
        button1.setSize(new Dimension(90, 30));
        button1.setLocation(new Point(100, 110));
        button1.setSelected(true);
        
        button2 = new JRadioButton("Girl");
        button2.setSize(new Dimension(90, 30));
        button2.setLocation(new Point(200, 110));
        
        button3 = new JRadioButton("Pikachu");
        button3.setSize(new Dimension(90, 30));
        button3.setLocation(new Point(300, 110));
        
        button4 = new JRadioButton("Boy");
        button4.setSize(new Dimension(90, 30));
        button4.setLocation(new Point(100, 250));
        button4.setSelected(true);
        
        button5 = new JRadioButton("Girl");
        button5.setSize(new Dimension(90, 30));
        button5.setLocation(new Point(200, 250));
        
        button6 = new JRadioButton("Pikachu");
        button6.setSize(new Dimension(90, 30));
        button6.setLocation(new Point(300, 250));
        
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
        playerTwoLabel.setLocation(new Point(200, 220));
        
        imageLabel1 = new JLabel();
        imageLabel1.setSize(new Dimension(CHAR_DIMENSION, CHAR_DIMENSION));
        imageLabel1.setLocation(new Point(200, 140));
        imageLabel1.setIcon(player0);
        
        imageLabel2 = new JLabel();
        imageLabel2.setSize(new Dimension(CHAR_DIMENSION, CHAR_DIMENSION));
        imageLabel2.setLocation(new Point(200, 290));
        imageLabel2.setIcon(player0);
        
        done = new StyledButton();
        done.setText("Done");
        done.setSize(new Dimension(100, 40));
        done.setLocation(new Point(200, 390));
        
        backButton = new BackButton(new Point(20, 380), prev, mainFrame, this);
        
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
            title.setText("Select Character");
            playerOneLabel.setVisible(false);
            playerTwoLabel.setVisible(false);
            button4.setVisible(false);
            button5.setVisible(false);
            button6.setVisible(false);
            imageLabel2.setVisible(false);
            done.setLocation(new Point(200, 250));
        } else {
            title.setText("Select Characters");
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
