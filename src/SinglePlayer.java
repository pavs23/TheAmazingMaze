import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SinglePlayer extends PlayerModes {
    private Player player;
    private GameMode maze;
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel mazePanel;
    private JPanel sideMenu;
    private JLabel[][] labels;

    /**
     * Constructor of the class to create the maze game.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     */
    public SinglePlayer(int mode, int x, int y) {
        // Set the mode of the game.
        setMode(mode);
        // Create the maze.
        createMaze(x, y);
        // Create the icons.
        generateIcon();
        
        // Initialize maze;
        maze = getMaze();
        
        // Create the JFrame.
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        
        // Create the Game Panel, maze and side menu.
        gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.setVisible(true);
        
        // Create the labels, mazePanel, and player.
        labels = generateLabels();
        mazePanel = generateMazePanel(labels);
        
        gamePanel.add(mazePanel);
        player = generatePlayer("player1");
        generateSideMenu();
        
        // Paint the player and set the listener.
        paintPlayer(player, player.getCoordinate(), MazeGame.EAST, labels);
        setEventListenerToMaze();
        
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Create the side menu with a hint button.
     */
    public void generateSideMenu() {
        sideMenu = new JPanel();
        sideMenu.setLayout(new FlowLayout());
        gamePanel.add(sideMenu);
        final JButton getHint = new JButton("Get Hint");
        getHint.setFocusable(false);
        // Print the first few steps to goal.
        // The number of steps are depending on the maze size.
        getHint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getHint.setEnabled(false);
                gamePanel.setEnabled(false); 
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
                        mazePanel.setEnabled(true);
                        getHint.setEnabled(true);                     
                    }
                });
                timer.setRepeats(false);
                timer.start();             
            }
        });
        sideMenu.add(getHint);
        sideMenu.setVisible(true);
    }
    
    /**
     * Set the event listener to the frame (arrow key press).
     * Use key binding for it.
     */
    public void setEventListenerToMaze() {   
        /*
        frame.addKeyListener(new KeyAdapter() {
           public void keyPressed(KeyEvent e) {
               int keyCode = e.getKeyCode();
               Direction dir = null;
               if (keyCode == KeyEvent.VK_LEFT) {
                   dir = MazeGame.WEST;
               } else if (keyCode == KeyEvent.VK_RIGHT) {
                   dir = MazeGame.EAST;
               } else if (keyCode == KeyEvent.VK_UP) {
                   dir = MazeGame.NORTH;
               } else if (keyCode == KeyEvent.VK_DOWN) {
                   dir = MazeGame.SOUTH;
               }
               if (dir != null) {
                   movePlayer(player, dir, labels, maze);
               }
           }
        });
        */
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
        
        gamePanel.getInputMap().put(leftKey, "left");
        gamePanel.getActionMap().put("left", leftKeyPressed);
        gamePanel.getInputMap().put(rightKey, "right");
        gamePanel.getActionMap().put("right", rightKeyPressed);
        gamePanel.getInputMap().put(upKey, "up");
        gamePanel.getActionMap().put("up", upKeyPressed);
        gamePanel.getInputMap().put(downKey, "down");
        gamePanel.getActionMap().put("down", downKeyPressed);
    }
}
