import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.io.*;

public class MazeGame {
    private Player player;
    private Maze maze;
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel mazePanel;
    private JPanel sideMenu;
    private JLabel[][] labels;
    private ImageIcon roadIcon;
    private ImageIcon wallIcon;
    private ImageIcon playerIconFront;
    private ImageIcon playerIconBack;
    private ImageIcon playerIconLeft;
    private ImageIcon playerIconRight;
    private ImageIcon hintIcon;
    
    private static final Direction ABOVE = new Direction("above", 0, -1);
    private static final Direction BOTTOM = new Direction("bottom", 0, 1);
    private static final Direction LEFT = new Direction("left", -1, 0);
    private static final Direction RIGHT = new Direction("right", 1, 0);
    
    private static final int ROAD = 1;
    private static final int FRAME_WIDTH = 600;
    
    /**
     * The main function. Launch the game.
     * @param args array of string.
     */
    public static void main(String[] args) {
        new MazeGame(5, 5);
    }
    
    /**
     * Constructor of the class to create the maze game.
     */
    public MazeGame(int x, int y) {
        // Create the JFrame.
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        newNormalGame(x,y);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Create the panel for a new maze, the maze, and side menu.
     * @param x the number of road in x direction.
     * @param y the number of road in y direction.
     */
    public void newNormalGame(int x, int y) {
        // Create the Game Panel, maze and side menu.
        gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.setVisible(true);
        generateRandomMaze(x, y);
        generateSideMenu();
        frame.add(gamePanel);
    }
    
    
    /**
     * Create the maze and paint it.
     * @param x the number of road in x direction.
     * @param y the number of road in y direction.
     */
    public void generateRandomMaze(int x, int y) {
        // Create maze.
        maze = new Maze(x, y);
        int[][] mazeArray = maze.getMazeArray();
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        Coordinate startCoordinate = maze.getStartCoordinate();
        player = new Player(startCoordinate);
        
        // Create panel for the maze.
        mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(xDimension, yDimension));
        mazePanel.setVisible(true);
        gamePanel.add(mazePanel);
        
        File wallFile = new File("wall.jpg");
        File roadFile = new File("road.jpg");
        File playerFrontFile = new File("playerRoadFront.jpg");
        File playerBackFile = new File("playerRoadBack.jpg");
        File playerLeftFile = new File("playerRoadLeft.jpg");
        File playerRightFile = new File("playerRoadRight.jpg");
        File hintFile = new File("hintTile.jpg");
        
        BufferedImage wallImg;
        BufferedImage roadImg;
        BufferedImage playerFrontImg;
        BufferedImage playerBackImg;
        BufferedImage playerLeftImg;
        BufferedImage playerRightImg;
        BufferedImage hintImg;
        
        Image scaledWall;
        Image scaledRoad;
        Image scaledPlayerFront;
        Image scaledPlayerBack;
        Image scaledPlayerLeft;
        Image scaledPlayerRight;
        Image scaledHint;
        
        labels = new JLabel[xDimension][yDimension];
        for (int j = 0; j < yDimension; j++) {
            for (int i = 0; i < xDimension; i++) {
                labels[i][j] = new JLabel();
                labels[i][j].setPreferredSize(new Dimension(FRAME_WIDTH/xDimension, FRAME_WIDTH/yDimension));
                mazePanel.add(labels[i][j]);
            }
        }
        
        try { 
            wallImg = ImageIO.read(wallFile);
            roadImg = ImageIO.read(roadFile);
            playerFrontImg = ImageIO.read(playerFrontFile);
            playerBackImg = ImageIO.read(playerBackFile);
            playerLeftImg = ImageIO.read(playerLeftFile);
            playerRightImg = ImageIO.read(playerRightFile);
            hintImg = ImageIO.read(hintFile);
            
            int height = labels[0][0].getPreferredSize().height;
            int width = labels[0][0].getPreferredSize().width;
            scaledWall = wallImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledRoad = roadImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerFront = playerFrontImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerBack = playerBackImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerLeft = playerLeftImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerRight = playerRightImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledHint = hintImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            
            wallIcon = new ImageIcon(scaledWall);
            roadIcon = new ImageIcon(scaledRoad);
            playerIconFront = new ImageIcon(scaledPlayerFront);
            playerIconBack = new ImageIcon(scaledPlayerBack);
            playerIconLeft = new ImageIcon(scaledPlayerLeft);
            playerIconRight = new ImageIcon(scaledPlayerRight);
            hintIcon = new ImageIcon(scaledHint);
            
            for (int j = 0; j < yDimension; j++) {
                for (int i = 0; i < xDimension; i++) {
                    if (mazeArray[i][j] == ROAD) {
                        labels[i][j].setIcon(roadIcon);
                    } else {
                        labels[i][j].setIcon(wallIcon);
                    }
                }
            }
            
        // Paint player initially.
        paintPlayer(startCoordinate, RIGHT);
        setEventListenerToMaze();
        } catch (IOException e) {}
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
                Timer timer = new Timer(600, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < labels.length/3 && i < path.size(); i++) {
                            Coordinate curr = path.get(i);
                            labels[curr.getX()][curr.getY()].setIcon(roadIcon);
                        }
                        gamePanel.setEnabled(true);
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
                   dir = LEFT;
               } else if (keyCode == KeyEvent.VK_RIGHT) {
                   dir = RIGHT;
               } else if (keyCode == KeyEvent.VK_UP) {
                   dir = ABOVE;
               } else if (keyCode == KeyEvent.VK_DOWN) {
                   dir = BOTTOM;
               }
               if (dir != null) {
                   movePlayer(dir);
               }
           }
        });
        */
        // Key bindings (so that it works with panel).
        
        Action leftKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(LEFT);
            }
        };
        Action rightKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(RIGHT);
            }
        };
        Action upKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(ABOVE);
            }
        };
        Action downKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                movePlayer(BOTTOM);
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
    
    /**
     * Paint the player in the maze.
     * @param coordinate the Coordinate of player..
     * @param direction the direction that the player is facing.
     */
    public void paintPlayer(Coordinate coordinate, Direction direction) {
        int xPos = coordinate.getX();
        int yPos = coordinate.getY();
        if (direction == ABOVE) {
            labels[xPos][yPos].setIcon(playerIconBack);
        } else if (direction == BOTTOM) {
            labels[xPos][yPos].setIcon(playerIconFront);
        } else if (direction == LEFT) {
            labels[xPos][yPos].setIcon(playerIconLeft);
        } else if (direction == RIGHT) {
            labels[xPos][yPos].setIcon(playerIconRight);
        }
    }
    
    /**
     * Paint road in the maze.
     * @param pos the Coordinate of road.
     */
    public void paintRoad(Coordinate pos) {
        labels[pos.getX()][pos.getY()].setIcon(roadIcon);
    }

    /**
     * Move the player in the system. Update the player coordinates and repaint.
     * If the player has finished, send a congratulation message.
     * @param direction the direction of movement of the player.
     */
    public void movePlayer(Direction direction) {
        Coordinate curr = player.getCoordinate();
        if (maze.isPath(curr, direction)) {
            paintRoad(curr);
            // Find the new position of the player.
            int currX = curr.getX();
            int currY = curr.getY();
            currX += direction.getXDirection();
            currY += direction.getYDirection();
            Coordinate newPos = new Coordinate(currX, currY);
            player.setCoordinate(newPos);
            paintPlayer(newPos, direction);
            Coordinate finishCoor = maze.getFinishCoordinate();
            if (finishCoor.equals(newPos)) {
                final JDialog dialog = new JDialog(frame, "Congratulation!", true);
                dialog.setLocationRelativeTo(frame);
                dialog.setUndecorated(true);
                
                JPanel congratulatoryPanel = new JPanel();
                congratulatoryPanel.setLayout(new BoxLayout(congratulatoryPanel, BoxLayout.Y_AXIS));
                JButton restart = new JButton("Restart");
                JButton newGame = new JButton("New Game");
                JButton quit = new JButton("Quit");
                restart.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        dialog.setVisible(false);
                        player.setCoordinate(maze.getStartCoordinate());
                        paintPlayer(player.getCoordinate(), RIGHT);
                        paintRoad(maze.getFinishCoordinate());
                    }
                });
                newGame.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        dialog.setVisible(false);
                        gamePanel.setVisible(false);
                        newNormalGame(20, 20);
                    }
                });
                quit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.setVisible(false);
                        frame.dispose();
                    }
                });
                
                congratulatoryPanel.add(restart);
                congratulatoryPanel.add(newGame);
                congratulatoryPanel.add(quit);
                congratulatoryPanel.setVisible(true); 
                
                dialog.add(congratulatoryPanel);
                dialog.pack();
                dialog.setVisible(true);
            }
        }
    }
}
