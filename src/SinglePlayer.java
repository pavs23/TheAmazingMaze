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
    public void setEventListenerToMaze() {
        // Key bindings (so that it works with panel).
        Action leftKeyPressed = new PressedAction(0, Game.WEST);
        Action leftKeyReleased = new ReleasedAction(0);
        Action rightKeyPressed = new PressedAction(1, Game.EAST);
        Action rightKeyReleased = new ReleasedAction(1);
        Action upKeyPressed = new PressedAction(2, Game.NORTH);
        Action upKeyReleased = new ReleasedAction(2);
        Action downKeyPressed = new PressedAction(3, Game.SOUTH);
        Action downKeyReleased = new ReleasedAction(3);
        
        KeyStroke leftKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
        KeyStroke rightKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
        KeyStroke upKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
        KeyStroke downKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
        
        KeyStroke leftKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
        KeyStroke rightKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
        KeyStroke upKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
        KeyStroke downKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
        
        setKeyBinding(leftKeyDown, leftKeyPressed, "leftD");
        setKeyBinding(rightKeyDown, rightKeyPressed, "rightD");
        setKeyBinding(upKeyDown, upKeyPressed, "upD");
        setKeyBinding(downKeyDown, downKeyPressed, "downD");
        
        setKeyBinding(leftKeyUp, leftKeyReleased, "leftU");
        setKeyBinding(rightKeyUp, rightKeyReleased, "rightU");
        setKeyBinding(upKeyUp, upKeyReleased, "upU");
        setKeyBinding(downKeyUp, downKeyReleased, "downU");
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
     */
    public void gameEndWin(String playerName) {
        gameFinished = true;
        freeze();
        timer.pause();    
        disposeFrame();
        int currSec = timer.getCurrentSecond();
        int score = currSec * Game.SCORE_MULTIPLIER;
        // CREATE A FRAME FOR WINNING PLAYER HERE.
        // Store the score in the leader board.
        ScoreManager manageScore = new ScoreManager();
        manageScore.setNewScore(getMode(), difficulty, playerName, score);
        ArrayList<LeaderBoardEntry> lists = manageScore.getScores(getMode(), difficulty);
        for (LeaderBoardEntry each : lists) {
            System.out.println(each.getScoreName() + " " + each.getScoreNum());
        }
        System.out.println(playerName + " wins! The score = " + score);
    }
    
    /**
     * A method that is called to indicate that a player has lost.
     * Create a lost frame and dispose the current game frame.
     */
    public void gameEndLost() {
        gameFinished = true;
        freeze();
        disposeFrame();
        // CREATE A FRAME FOR LOSING PLAYER HERE
        System.out.println("You lose...");
    }
    
    /**
     * Create the side menu with a hint button.
     */
    private void generateHint() {
        hintButton = new JButton("Get Hint : " + hintRemaining);
        hintButton.setFocusable(false);
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
