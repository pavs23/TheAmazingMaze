import java.awt.*;
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
    private JPanel sideMenu;
    private JLabel[][] labels;

    /**
     * Constructor of the class to create the maze game.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param x the number of roads needed in X direction.
     * @param y the number of roads needed in Y direction.
     */
    public SinglePlayer(int mode, int x, int y) {
        super(mode, x, y);
        
        // Initialize maze;
        maze = getMaze();
        
        // Create the labels, mazePanel, and player.
        labels = generateLabels();
        mazePanel = generateMazePanel(labels);
        
        addToGamePanel(mazePanel);
        player = generatePlayer("player1");
        generateSideMenu();
        
        // Paint the player and set the listener.
        paintPlayer(player, player.getCoordinate(), MazeGame.EAST, labels);
        setEventListenerToMaze();
    }
    
    /**
     * Create the side menu with a hint button.
     */
    public void generateSideMenu() {
        sideMenu = new JPanel();
        sideMenu.setLayout(new FlowLayout());
        final JButton getHint = new JButton("Get Hint");
        getHint.setFocusable(false);
        // Print the first few steps to goal.
        // The number of steps are depending on the maze size.
        getHint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getHint.setEnabled(false);
                freeze(); 
                Coordinate currPos = player.getCoordinate();
                final ArrayList<Coordinate> path = maze.getHint(currPos);
                // n = the dimension of the array/3.
                for (int i = 0; i < labels.length/3 && i < path.size(); i++) {
                    Coordinate curr = path.get(i);
                    labels[curr.getX()][curr.getY()].setIcon(hintIcon);
                }
                Timer timer = new Timer(500, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < labels.length/3 && i < path.size(); i++) {
                            Coordinate curr = path.get(i);
                            labels[curr.getX()][curr.getY()].setIcon(roadIcon);
                        }
                        resume();
                        getHint.setEnabled(true);                     
                    }
                });
                timer.setRepeats(false);
                timer.start();             
            }
        });
        sideMenu.add(getHint);
        sideMenu.setVisible(true);
        addToGamePanel(sideMenu);
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
                movePlayer(player, MazeGame.WEST, labels, maze);
            }
        };
        Action rightKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player, MazeGame.EAST, labels, maze);
            }
        };
        Action upKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player, MazeGame.NORTH, labels, maze);
            }
        };
        Action downKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(player, MazeGame.SOUTH, labels, maze);
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
}
