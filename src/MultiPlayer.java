import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.*;
/**
 *
 * A class that represents Multiplayer Game.
 * @author floren
 *
 */
public class MultiPlayer extends PlayerModes {
    private Player player1;
    private Player player2;
    private GameMode maze1;
    private GameMode maze2;
    private JPanel mazePanel1;
    private JPanel mazePanel2;
    private JLabel[][] labels1;
    private JLabel[][] labels2;
    private Timer[] timers = new Timer[8];
    private boolean gameFinished = false;

    /**
     * Constructor of the class to create the maze MazeGame.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param difficulty the difficulty of the maze.
     * @param player1Code the code representing the player 1's character.
     * @param player1Name the name of the player 1.
     * @param player2Code the code representing the player 2's character.
     * @param player2Name the name of the player 2.
     */
    public MultiPlayer(int mode, int difficulty, int player1Code, String player1Name, int player2Code, String player2Name) {
        // Create the super class.
        super(mode, difficulty);
        
        // Initialize maze;
        maze1 = getMaze();
        if (mode == Game.COIN_MODE) {
            CoinMaze coinMaze = (CoinMaze) maze1;
            maze2 = (GameMode) coinMaze.generateClone();
        } else {
            maze2 = getMaze();
        }
        
        // Create the labels, mazePanel, and player.
        labels1 = generateLabels();
        labels2 = generateLabels();
        mazePanel1 = generateMazePanel(labels1);
        mazePanel2 = generateMazePanel(labels2);
        addToGamePanel(mazePanel1);
        addToGamePanel(mazePanel2);
        player1 = generatePlayer(player1Name, player1Code);
        player2 = generatePlayer(player2Name, player2Code);
        
        // Paint the players.

        paintPlayer(player1, player1.getCoordinate(), Game.EAST, labels1);
        paintPlayer(player2, player2.getCoordinate(), Game.EAST, labels2);
        
        // All components are added, show the frame.
        showFrame();
    }
    
    @Override
    public void freeze() {
        super.freeze();
        stopTimers();
    }
    
    @Override
    public void resume() {
        super.resume();
        setTimersToNull();
    }
    
    /**
     * Set the event listener to the frame (arrows key press and asdw key press).
     * Use key binding for it.
     */ 
    public void setEventListenerToMaze() {
        // Key bindings (so that it works with panel).
        // 2nd player
        Action leftKeyPressed = new PressedActionPlayer2 (0, Game.WEST); 
        Action leftKeyReleased = new ReleasedAction(0);
        Action rightKeyPressed =  new PressedActionPlayer2 (1, Game.EAST); 
        Action rightKeyReleased = new ReleasedAction(1);
        Action upKeyPressed = new PressedActionPlayer2 (2, Game.NORTH);  
        Action upKeyReleased = new ReleasedAction(2);
        Action downKeyPressed = new PressedActionPlayer2 (3, Game.SOUTH); 
        Action downKeyReleased = new ReleasedAction(3);
        
        // 1st player.
        Action aKeyPressed = new PressedActionPlayer1 (4, Game.WEST); 
        Action aKeyReleased = new ReleasedAction(4);
        Action dKeyPressed = new PressedActionPlayer1 (5, Game.EAST); 
        Action dKeyReleased = new ReleasedAction(5);
        Action wKeyPressed = new PressedActionPlayer1 (6, Game.NORTH); 
        Action wKeyReleased = new ReleasedAction(6);
        Action sKeyPressed = new PressedActionPlayer1 (7, Game.SOUTH); 
        Action sKeyReleased = new ReleasedAction(7);
        
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
    private class PressedActionPlayer1 extends AbstractAction {
        private int index;
        private Direction dir;
        
        
        /**
         * Constructor of the class.
         * @param index the index of timer related to the event listener.
         * @param dir the direction of player movement that is tied to this listener.
         */
        public PressedActionPlayer1(int index, Direction dir) {
            this.index = index;
            this.dir = dir;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timers[index] == null) {      
                timers[index] = new Timer(Game.MOVING_TIME, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                         movePlayer(player1, dir, labels1, maze1);
                         if (gameFinished) {
                             timers[index].stop();
                         }
                    }
                });
                movePlayer(player1, dir, labels1, maze1); 
                timers[index].start();
            }
        }
    }
    
    
    /**
     * A class that represents pressed action.
     * @author floren
     *
     */
    @SuppressWarnings("serial")
    private class PressedActionPlayer2 extends AbstractAction {
        private int index;
        private Direction dir;
        
        
        /**
         * Constructor of the class.
         * @param index the index of timer related to the event listener.
         * @param dir the direction of player movement that is tied to this listener.
         */
        public PressedActionPlayer2(int index, Direction dir) {
            this.index = index;
            this.dir = dir;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timers[index] == null) {      
                timers[index] = new Timer(Game.MOVING_TIME, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                         movePlayer(player2, dir, labels2, maze2);
                         if (gameFinished) {
                             timers[index].stop();
                         }
                    }
                });
                movePlayer(player2, dir, labels2, maze2); 
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
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timers[index] != null) {
                timers[index].stop();
                timers[index] = null;
            }
        }
    }
    
    /**
     * Dispose the frame, stop and dispose the timers, and show which player wins on game end.
     * @param playerName the name of the winning player.
     */
    public void gameEndWin(String playerName) {
        gameFinished = true;
        freeze();
        disposeFrame();
        // Create a frame for winning player.
        JFrame newFrame = new JFrame();
        newFrame.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        newFrame.setResizable(false);
        newFrame.setLayout(null);
        JPanel winPanel = new WinPanel(newFrame, playerName);
        winPanel.setLocation(new Point(0, 0));
        newFrame.add(winPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
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
