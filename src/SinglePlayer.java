import java.awt.event.*;
import javax.swing.*;
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
    private JButton hintButton;
    private GameTimer timer;

    /**
     * Constructor of the class to create the maze game.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param x the number of roads needed in X direction.
     * @param y the number of roads needed in Y direction.
     * @param playerCode the code representing the player's character.
     * @param playerName the name of the player.
     */
    public SinglePlayer(int mode, int x, int y, int playerCode, String playerName) {
        super(mode, x, y);
        
        // Initialize maze;
        maze = getMaze();
        
        // Create the labels, mazePanel, and player.
        labels = generateLabels();
        mazePanel = generateMazePanel(labels);
        
        addToGamePanel(mazePanel);
        player = generatePlayer(playerName, playerCode);
        generateHint();
        generateTimer();
        
        // Paint the player.
        paintPlayer(player, player.getCoordinate(), Game.EAST, labels);
        
        // All components are added, how the frame.
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
    @SuppressWarnings("serial")
    public void setEventListenerToMaze() {
        // Key bindings (so that it works with panel).
        Action leftKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player, Game.WEST, labels, maze);
            }
        };
        Action rightKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player, Game.EAST, labels, maze);
            }
        };
        Action upKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player, Game.NORTH, labels, maze);
            }
        };
        Action downKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player, Game.SOUTH, labels, maze);
            }
        };
        KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke upKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        
        setKeyBinding(leftKey, leftKeyPressed, "left");
        setKeyBinding(rightKey, rightKeyPressed, "right");
        setKeyBinding(upKey, upKeyPressed, "up");
        setKeyBinding(downKey, downKeyPressed, "down");
    }
    
    /**
     * Dispose the frame and create new frame for winning player.
     * Show the score of the winning player.
     */
    public void gameEndWin(String playerName) {
        timer.pause();
        disposeFrame();
        int currSec = timer.getCurrentSecond();
        int score = currSec * Game.SCORE_MULTIPLIER;
        // CREATE A FRAME FOR WINNING PLAYER HERE.
        System.out.println(playerName + " wins! The score = " + score);
    }
    
    /**
     * A method that is called to indicate that a player has lost.
     * Create a lost frame and dispose the current game frame.
     */
    public void gameEndLost() {
        disposeFrame();
        // CREATE A FRAME FOR LOSING PLAYER HERE
        System.out.println("You lose...");
    }
    
    /**
     * Create the side menu with a hint button.
     */
    private void generateHint() {
        hintButton = new JButton("Get Hint");
        hintButton.setFocusable(false);
        // Print the first few steps to goal.
        // The number of steps are depending on the maze size.
        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disabledGame(); 
                Coordinate currPos = player.getCoordinate();
                final ArrayList<Coordinate> path = maze.getHint(currPos);
                // n = the dimension of the array/3.
                for (int i = 0; i < labels.length/3 && i < path.size(); i++) {
                    Coordinate curr = path.get(i);
                    labels[curr.getX()][curr.getY()].setIcon(getHintIcon());
                }
                Timer newTimer = new Timer(500, new ActionListener() {
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
        addToSidePanel(hintButton);
    }
    
    /**
     * Method to create timer for the game.
     */
    private void generateTimer() {
        JLabel timeLabel = new JLabel();
        timeLabel.setVisible(true);
        addToSidePanel(timeLabel);
        timer = new GameTimer(timeLabel, this);
        timer.start();    
    }
    
    /**
     * A method that will disable the buttons and action listeners in the game.
     */
    private void disabledGame() {
        super.freeze();
        hintButton.setEnabled(false);
    }
    
    /**
     * A method that will enable the buttons and action listeners in the game.
     */
    private void enabledGame() {
        super.resume();
        hintButton.setEnabled(true);
    }
}
