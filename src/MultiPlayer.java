import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 * A class that represents Multiplayer game.
 * @author floren
 *
 */
public class MultiPlayer extends PlayerModes {
    private Player player1;
    private Player player2;
    private GameMode maze1;
    private GameMode maze2;
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel mazePanel1;
    private JPanel mazePanel2;
    private JLabel[][] labels1;
    private JLabel[][] labels2;

    /**
     * Constructor of the class to create the maze game.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param x the number of roads needed in X direction.
     * @param y the number of roads needed in Y direction.
     */
    public MultiPlayer(int mode, int x, int y) {
        // Set the mode of the game.
        setMode(mode);
        // Create the maze.
        createMaze(x, y);
        // Create the icons.
        generateIcon();
        
        // Initialize maze;
        maze1 = getMaze();
        if (mode == MazeGame.COIN_MODE) {
            CoinMaze coinMaze = (CoinMaze) maze1;
            maze2 = (GameMode) coinMaze.generateClone();
        } else {
            maze2 = getMaze();
        }
       
        // Create the JFrame.
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        
        // Create the Game Panel, maze and side menu.
        gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.setVisible(true);
        
        // Create the labels, mazePanel, and player.
        labels1 = generateLabels();
        labels2 = generateLabels();
        mazePanel1 = generateMazePanel(labels1);
        mazePanel2 = generateMazePanel(labels2);
        gamePanel.add(mazePanel1);
        gamePanel.add(mazePanel2);
        player1 = generatePlayer("player1");
        player2 = generatePlayer("player2");
        
        // Paint the player and set the listener.
        paintPlayer(player1, player1.getCoordinate(), MazeGame.EAST, labels1);
        paintPlayer(player2, player2.getCoordinate(), MazeGame.EAST, labels2);
        setEventListenerToMaze();
        
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    /**
     * Set the event listener to the frame (arrows key press and asdw key press).
     * Use key binding for it.
     */
    public void setEventListenerToMaze() {
        /*
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    movePlayer(player2, MazeGame.WEST, labels2, maze2);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    movePlayer(player2, MazeGame.EAST, labels2, maze2);
                } else if (keyCode == KeyEvent.VK_UP) {
                    movePlayer(player2, MazeGame.NORTH, labels2, maze2);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    movePlayer(player2, MazeGame.SOUTH, labels2, maze2);
                } else if (keyCode == KeyEvent.VK_A) {
                    movePlayer(player1, MazeGame.WEST, labels1, maze1);
                } else if (keyCode == KeyEvent.VK_D) {
                    movePlayer(player1, MazeGame.EAST, labels1, maze1);
                } else if (keyCode == KeyEvent.VK_W) {
                    movePlayer(player1, MazeGame.NORTH, labels1, maze1);
                } else if (keyCode == KeyEvent.VK_S) {
                    movePlayer(player1, MazeGame.SOUTH, labels1, maze1);
                }
            }
         });
         */
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
        
        gamePanel.getInputMap().put(leftKey, "left");
        gamePanel.getActionMap().put("left", leftKeyPressed);
        gamePanel.getInputMap().put(rightKey, "right");
        gamePanel.getActionMap().put("right", rightKeyPressed);
        gamePanel.getInputMap().put(upKey, "up");
        gamePanel.getActionMap().put("up", upKeyPressed);
        gamePanel.getInputMap().put(downKey, "down");
        gamePanel.getActionMap().put("down", downKeyPressed);
        
        gamePanel.getInputMap().put(aKey, "a");
        gamePanel.getActionMap().put("a", aKeyPressed);
        gamePanel.getInputMap().put(dKey, "d");
        gamePanel.getActionMap().put("d", dKeyPressed);
        gamePanel.getInputMap().put(wKey, "w");
        gamePanel.getActionMap().put("w", wKeyPressed);
        gamePanel.getInputMap().put(sKey, "s");
        gamePanel.getActionMap().put("s", sKeyPressed);
    }
}
