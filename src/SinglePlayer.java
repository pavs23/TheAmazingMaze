import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;

/**
 * A class that represents single player mode.
 * @author floren
 *
 */
public class SinglePlayer extends PlayerModes {
    private Player player;
    private GameMode maze;
    private JPanel mazePanel;
    private JLabel[][] labels;
    private StyledButton hintButton;
    private GameTimer timer;
    private Timer[] timers = new Timer[4];
    private boolean gameFinished = false;
    private int difficulty;
    private int hintRemaining = 3;

    /**
     * Constructor of the class to create the maze game.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param difficulty the difficulty of the game.
     * @param playerCode the code representing the player's character.
     * @param playerName the name of the player.
     */
    public SinglePlayer(int mode, int difficulty, int playerCode, String playerName) {
        super(mode, difficulty);
        this.difficulty = difficulty;
        // Initialize maze;
        maze = getMaze();
        
        // Create the labels, mazePanel, and player.
        labels = generateLabels();
        mazePanel = generateMazePanel(labels);
        
        JPanel mazeAndInst = new JPanel();
        mazeAndInst.setLayout(new BoxLayout(mazeAndInst, BoxLayout.Y_AXIS));
        mazeAndInst.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        
        JPanel instruction = new JPanel();
        instruction.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        instruction.setLayout(new FlowLayout());
        
        JPanel playerLabel = new JPanel();
        playerLabel.setBorder(new EmptyBorder(0, 0, 0, 20) );
        playerLabel.setLayout(new BoxLayout(playerLabel, BoxLayout.X_AXIS));
        playerLabel.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        
        JLabel playerIcon = new JLabel();
        Image playerImg;
        if (playerCode == Game.PLAYER_0) {
            playerImg = Game.PLAYER_0_IMAGE;
        } else if (playerCode == Game.PLAYER_1) {
            playerImg = Game.PLAYER_1_IMAGE;
        } else {
            playerImg = Game.PLAYER_2_IMAGE;
        }
        ImageIcon icon = new ImageIcon(playerImg.getScaledInstance(50, 80, Image.SCALE_SMOOTH));
        playerIcon.setIcon(icon);
        playerIcon.setBorder(new EmptyBorder(0, 0, 0, 20) );
        
        JLabel namePlayer = new JLabel();
        namePlayer.setText(playerName);
        namePlayer.setHorizontalAlignment(JLabel.CENTER);
        namePlayer.setForeground(Color.BLACK);
        namePlayer.setOpaque(false);
        namePlayer.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel playerKeys = new JLabel();
        
        File aswdFile = new File("images/ASWD_Key.jpg");
        Image aswdImage = null;
        try {
            aswdImage = ImageIO.read(aswdFile);
        } catch (IOException e) {}
        Image scaledAswd = aswdImage.getScaledInstance(180, 90, Image.SCALE_SMOOTH);    
        ImageIcon aswdIcon = new ImageIcon(scaledAswd);
        playerKeys.setIcon(aswdIcon);
         
        playerLabel.add(playerIcon);
        playerLabel.add(namePlayer);
        
        instruction.add(playerLabel);
        instruction.add(playerKeys);
        
        mazeAndInst.add(instruction);
        mazeAndInst.add(mazePanel);
        
        addToGamePanel(mazeAndInst);
        player = generatePlayer(playerName, playerCode);
        
        generateHint();
        generateTimer();
        
        // Paint the player.
        paintPlayer(player, player.getCoordinate(), Game.EAST, labels);
        
        // All components are added, show the frame.
        showFrame();
    }
    
    @Override
    public void freeze() {
        disabledGame();  
        timer.pause();
    }
    
    @Override
    public void resume() {
        enabledGame();
        timer.start();
    }
    
    /**
     * Set the event listener to the frame (arrows key press).
     * Use key binding for it.
     */
    public void setEventListenerToMaze() {
        Action aKeyPressed = new PressedAction (0, Game.WEST); 
        Action aKeyReleased = new ReleasedAction(0);
        Action dKeyPressed = new PressedAction (1, Game.EAST); 
        Action dKeyReleased = new ReleasedAction(1);
        Action wKeyPressed = new PressedAction (2, Game.NORTH); 
        Action wKeyReleased = new ReleasedAction(2);
        Action sKeyPressed = new PressedAction(3, Game.SOUTH); 
        Action sKeyReleased = new ReleasedAction(3);
        
        KeyStroke aKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false);
        KeyStroke dKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false);
        KeyStroke wKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false);
        KeyStroke sKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false);
        
        KeyStroke aKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true);
        KeyStroke dKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true);
        KeyStroke wKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true);
        KeyStroke sKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true);
        
        setKeyBinding(aKeyDown, aKeyPressed, "aD");
        setKeyBinding(dKeyDown, dKeyPressed, "dD");
        setKeyBinding(wKeyDown, wKeyPressed, "wD");
        setKeyBinding(sKeyDown, sKeyPressed, "sD");
        
        setKeyBinding(aKeyUp, aKeyReleased, "aU");
        setKeyBinding(dKeyUp, dKeyReleased, "dU");
        setKeyBinding(wKeyUp, wKeyReleased, "wU");
        setKeyBinding(sKeyUp, sKeyReleased, "sU"); 
    }
    
    /**
     * A class that represents pressed action.
     * @author floren
     *
     */
    @SuppressWarnings("serial")
    private class PressedAction extends AbstractAction {
        private int index;
        private Direction dir;
        
        /**
         * Constructor of the class.
         * @param index the index of timer related to the event listener.
         * @param dir the direction of player movement that is tied to this listener.
         */
        public PressedAction(int index, Direction dir) {
            this.index = index;
            this.dir = dir;
        }
        
        public void actionPerformed(ActionEvent e) {
            if (timers[index] == null) {      
                timers[index] = new Timer(Game.MOVING_TIME, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                         movePlayer(player, dir, labels, maze);
                         if (gameFinished) {
                             timers[index].stop();
                         }
                    }
                });
                movePlayer(player, dir, labels, maze); 
                timers[index].start();
            }
        }
    }
    
    /**
     * A class that represents released action.
     * @author floren
     *
     */
    @SuppressWarnings("serial")
    private class ReleasedAction extends AbstractAction {
        private int index;
        
        /**
         * Constructor of the class.
         * @param index the index of timer related to the event listener.
         */
        public ReleasedAction(int index) {
            this.index = index;
        }
        
        public void actionPerformed(ActionEvent e) {
            if (timers[index] != null) {
                timers[index].stop();
                timers[index] = null;
            }
        }
    }
    
    /**
     * Dispose the frame and create new frame for winning player.
     * Stop the timers.
     * Show the score of the winning player.
     * @param playerName the name of the winning player.
     */
    public void gameEndWin(final String playerName) {
        gameFinished = true;
        freeze();
        // Make the game freeze for a moment before ending.
        Timer newTimer =  new Timer(250, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disposeFrame();
                int currSec = timer.getCurrentSecond();
                int score = currSec * Game.SCORE_MULTIPLIER;
                
                ScoreManager manageScore = new ScoreManager();
                manageScore.setNewScore(getMode(), difficulty, playerName, score);
                
                // Create a frame for winning player.
                JFrame newFrame = new JFrame();
                newFrame.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
                newFrame.setResizable(false);
                newFrame.setLayout(null);
                JPanel winPanel = new WinPanel(newFrame, playerName, score, getMode(), difficulty);
                winPanel.setLocation(new Point(0, 0));
                newFrame.add(winPanel);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
                newFrame.setLocationRelativeTo(null);
                newFrame.setVisible(true);
            }
        });
        newTimer.setRepeats(false);
        newTimer.start();
    }
    
    /**
     * A method that is called to indicate that a player has lost.
     * Create a lost frame and dispose the current game frame.
     */
    public void gameEndLost() {
        gameFinished = true;
        freeze();
        // Create a frame for losing player.
        // Make the game freeze for a moment before ending.
        Timer newTimer =  new Timer(400, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disposeFrame();
                JFrame newFrame = new JFrame();
                newFrame.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
                newFrame.setResizable(false);
                newFrame.setLayout(null);
                JPanel losePanel = new LosePanel(newFrame);
                losePanel.setLocation(new Point(0, 0));
                newFrame.add(losePanel);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
                newFrame.setLocationRelativeTo(null);
                newFrame.setVisible(true);
            }
        });
        newTimer.setRepeats(false);
        newTimer.start();
    }
    
    /**
     * Create the side menu with a hint button.
     */
    private void generateHint() {
        JPanel hintPanel = new JPanel();
        hintPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        hintPanel.setLayout(new FlowLayout());
        hintPanel.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        hintButton = new StyledButton ("Get Hint : " + hintRemaining);
        hintButton.setFocusable(false);
        hintPanel.add(hintButton);
        // Print the first few steps to goal.
        // The number of steps are depending on the maze size.
        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hintRemaining --;
                hintButton.setText("Get Hint : " + hintRemaining);
                disabledGame(); 
                Coordinate currPos = player.getCoordinate();
                final ArrayList<Coordinate> path = maze.getHint(currPos);
                // n = the dimension of the array/3.
                for (int i = 0; i < labels.length/4 && i < path.size(); i++) {
                    Coordinate curr = path.get(i);
                    labels[curr.getX()][curr.getY()].setIcon(getHintIcon());
                }
                Timer newTimer = new Timer(750, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < labels.length/3 && i < path.size(); i++) {
                            Coordinate curr = path.get(i);
                            labels[curr.getX()][curr.getY()].setIcon(getRoadIcon());
                        }
                        enabledGame();
                    }
                });
                newTimer.setRepeats(false);
                newTimer.start();
            }
        });
        addToSidePanel(hintPanel);
    }
    
    /**
     * Method to create timer for the game.
     */
    private void generateTimer() {
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timePanel.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        timePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel timeLabel = new JLabel();
        timeLabel.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timeLabel.setVisible(true);
        timeLabel.setPreferredSize(new Dimension(150, 50));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timePanel.add(timeLabel);
        addToSidePanel(timePanel);
        timer = new GameTimer(timeLabel, this);        
        timer.start();    
    }
    
    /**
     * A method that will disable the buttons and action listeners in the game.
     */
    private void disabledGame() {
        super.freeze();
        stopTimers();
        hintButton.setEnabled(false);
    }
    
    /**
     * A method that will enable the buttons and action listeners in the game.
     */
    private void enabledGame() {
        setTimersToNull();
        super.resume();   
        if (hintRemaining > 0) {
            hintButton.setEnabled(true);
        }
    }

    /**
     * A helper method to stop all timers.
     */
    private void stopTimers() {
        for (int i = 0; i < timers.length; i++) {
            if (timers[i] != null) {
                timers[i].stop();
            }
        }
    }
    
    /**
     * A helper method to set all timers to null.
     */
    private void setTimersToNull() {
        for (int i = 0; i < timers.length; i++) {
            timers[i] = null;
        }
    }
}
