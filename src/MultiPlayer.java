import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    
    private static final Direction ABOVE = new Direction("above", 0, -1);
    private static final Direction BOTTOM = new Direction("bottom", 0, 1);
    private static final Direction LEFT = new Direction("left", -1, 0);
    private static final Direction RIGHT = new Direction("right", 1, 0);
    

    /**
     * Constructor of the class to create the maze game.
     * @param mode the mode of the single game (normal/coin).
     */
    public MultiPlayer(String mode, int x, int y) {
        // Set the mode of the game.
        setMode(mode);
        // Create the maze.
        createMaze(x, y);
        // Create the icons.
        generateIcon();
        
        // Initialize maze;
        maze1 = getMaze();
        if (mode.equals("coin")) {
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
        paintPlayer(player1, player1.getCoordinate(), RIGHT, labels1);
        paintPlayer(player2, player2.getCoordinate(), RIGHT, labels2);
        setEventListenerToMaze();
        
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    /**
     * Set the event listener to the frame (arrow key press).
     * Use key binding for it.
     */
    public void setEventListenerToMaze() {
        // Key bindings (so that it works with panel).
        
        Action leftKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player1, LEFT, labels1, maze1);
            }
        };
        Action rightKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player1, RIGHT, labels1, maze1);
            }
        };
        Action upKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player1, ABOVE, labels1, maze1);
            }
        };
        Action downKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player1, BOTTOM, labels1, maze1);
            }
        };
        
        Action aKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player2, LEFT, labels2, maze2);
            }
        };
        
        Action dKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player2, RIGHT, labels2, maze2);
            }
        };
        
        Action wKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player2, ABOVE, labels2, maze2);
            }
        };
        
        Action sKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player2, BOTTOM, labels2, maze2);
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
