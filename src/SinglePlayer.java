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
    private JLabel[][] labels;
    private JButton hintButton;

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
        generateHint();
        
        // Paint the player and set the listener.
        paintPlayer(player, player.getCoordinate(), MazeGame.EAST, labels);
        setEventListenerToMaze();
        
        // All components are added, how the frame.
        showFrame();
    }
    
    @Override
    public void freeze() {
        super.freeze();
        hintButton.setEnabled(false);
    }
    
    @Override
    public void resume() {
        super.resume();
        hintButton.setEnabled(true);
    }
    
    /**
     * Create the side menu with a hint button.
     */
    private void generateHint() {
        hintButton = new JButton("Get Hint");
        // Print the first few steps to goal.
        // The number of steps are depending on the maze size.
        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                    }
                });
                timer.setRepeats(false);
                timer.start();             
            }
        });
        addToSidePanel(hintButton);
    }
    
    /**
     * Set the event listener to the frame (arrows key press).
     * Use key binding for it.
     */
    @SuppressWarnings("serial")
    private void setEventListenerToMaze() {   
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
