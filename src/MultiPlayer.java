import java.awt.event.*;

import javax.swing.*;
/**
 * A class that represents Multiplayer MazeGame.
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

    /**
     * Constructor of the class to create the maze MazeGame.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param x the number of roads needed in X direction.
     * @param y the number of roads needed in Y direction.
     * @param player1Code the code representing the player 1's character.
     * @param player1Name the name of the player 1.
     * @param player2Code the code representing the player 2's character.
     * @param player2Name the name of the player 2.
     */
    public MultiPlayer(int mode, int x, int y, int player1Code, String player1Name, int player2Code, String player2Name) {
        // Create the super class.
        super(mode, x, y);
        
        // Initialize maze;
        maze1 = getMaze();
        if (mode == MazeGame.COIN_MODE) {
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
        paintPlayer(player1, player1.getCoordinate(), MazeGame.EAST, labels1);
        paintPlayer(player2, player2.getCoordinate(), MazeGame.EAST, labels2);
        
        // All components are added, show the frame.
        showFrame();
    }
    
    
    /**
     * Set the event listener to the frame (arrows key press and asdw key press).
     * Use key binding for it.
     */
    


    
    
    @SuppressWarnings("serial")
    public void setEventListenerToMaze() {
        // Key bindings (so that it works with panel).
        
        		Action leftKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player2, MazeGame.WEST, labels2, maze2);
                    }
                };
                Action rightKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player2, MazeGame.EAST, labels2, maze2);
                    }
                };
                Action upKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player2, MazeGame.NORTH, labels2, maze2);
                    }
                };
                Action downKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player2, MazeGame.SOUTH, labels2, maze2);
                    }
                };
                
                Action aKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player1, MazeGame.WEST, labels1, maze1);
                    }
                };
                
                Action dKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player1, MazeGame.EAST, labels1, maze1);
                    }
                };
                
                Action wKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player1, MazeGame.NORTH, labels1, maze1);
                    }
                };
                
                Action sKeyPressed = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        movePlayer(player1, MazeGame.SOUTH, labels1, maze1);
                    }
                };
                
                
                KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
                KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
                KeyStroke upKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
                KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
                
                KeyStroke aKey = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
                KeyStroke dKey = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
                KeyStroke wKey = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
                KeyStroke sKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
                
                
                setKeyBinding(leftKey, leftKeyPressed, "left");
                setKeyBinding(rightKey, rightKeyPressed, "right");
                setKeyBinding(upKey, upKeyPressed, "up");
                setKeyBinding(downKey, downKeyPressed, "down");
                
                setKeyBinding(aKey, aKeyPressed, "a");
                setKeyBinding(dKey, dKeyPressed, "d");
                setKeyBinding(wKey, wKeyPressed, "w");
                setKeyBinding(sKey, sKeyPressed, "s"); 

    }
}
