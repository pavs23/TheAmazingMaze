
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

import java.io.*;

public class MazeGame {
    public static void main(String[] args) {
        new MazeGame();
    }
    
    /**
     * Constructor of the class to create the maze game.
     */
    public MazeGame() {
        // Create the JFrame.
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        setEventListenerToMaze();
        generateRandomMaze(5, 5);
    }
    
    /**
     * Create the maze and paint it.
     * @param x the number of road in x direction.
     * @param y the number of road in y direction.
     */
    public void generateRandomMaze(int x, int y) {
        // Create maze.
        maze = new Maze(x, y);
        int[][] mazeArray = maze.getMaze();
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        int startingX = maze.getStartX();
        int startingY = maze.getStartY();
        player = new Player(startingX, startingY);
        
        // Create panel for the maze.
        mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(xDimension, yDimension));
        mazePanel.setVisible(true);
        frame.add(mazePanel);
        
        File wallFile = new File("wall.jpg");
        File roadFile = new File("road.jpg");
        File playerFrontFile = new File("playerRoadFront.jpg");
        File playerBackFile = new File("playerRoadBack.jpg");
        File playerLeftFile = new File("playerRoadLeft.jpg");
        File playerRightFile = new File("playerRoadRight.jpg");

        
        BufferedImage wallImg;
        BufferedImage roadImg;
        BufferedImage playerFrontImg;
        BufferedImage playerBackImg;
        BufferedImage playerLeftImg;
        BufferedImage playerRightImg;
        
        Image scaledWall;
        Image scaledRoad;
        Image scaledPlayerFront;
        Image scaledPlayerBack;
        Image scaledPlayerLeft;
        Image scaledPlayerRight;
        
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
            
            int height = labels[0][0].getPreferredSize().height;
            int width = labels[0][0].getPreferredSize().width;
            scaledWall = wallImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledRoad = roadImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerFront = playerFrontImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerBack = playerBackImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerLeft = playerLeftImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerRight = playerRightImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            
            wallIcon = new ImageIcon(scaledWall);
            roadIcon = new ImageIcon(scaledRoad);
            playerIconFront = new ImageIcon(scaledPlayerFront);
            playerIconBack = new ImageIcon(scaledPlayerBack);
            playerIconLeft = new ImageIcon(scaledPlayerLeft);
            playerIconRight = new ImageIcon(scaledPlayerRight);
            
            
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
        paintPlayer(startingX, startingY, RIGHT);
        } catch (IOException e) {}

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Set the event listener to the frame (arrow keypress).
     */
    public void setEventListenerToMaze() {
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
    }
    
    /**
     * Paint the player in the maze.
     * @param xPos the x coordinate of player.
     * @param yPos the y coordinate of player.
     * @param direction the direction that the player is facing.
     */
    public void paintPlayer(int xPos, int yPos, Direction direction) {
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
     * @param xPos the x coordinate of road.
     * @param yPos the y coordinate of road.
     */
    public void paintRoad(int xPos, int yPos) {
        labels[xPos][yPos].setIcon(roadIcon);
    }

    /**
     * Move the player in the system. Update the player coordinates and repaint.
     * If the player has finished, send a congratulatory message.
     * @param direction the direction of movement of the player.
     */
    public void movePlayer(Direction direction) {
        int currX = player.getXPosition();
        int currY = player.getYPosition();
        if (maze.isPath(currX, currY, direction)) {
            paintRoad(currX, currY);
            // Find the new position of the player.
            currX += direction.getXDirection();
            currY += direction.getYDirection();
            player.setXPosition(currX);
            player.setYPosition(currY);
            paintPlayer(currX, currY, direction);
            int mazeFinishX = maze.getFinishX();
            int mazeFinishY = maze.getFinishY();
            if (currX == mazeFinishX && currY == mazeFinishY) {
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
                        player.setXPosition(maze.getStartX());
                        player.setYPosition(maze.getStartY());
                        paintPlayer(player.getXPosition(), player.getYPosition(), RIGHT);
                        paintRoad(maze.getFinishX(), maze.getFinishY());
                    }
                });
                newGame.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        dialog.setVisible(false);
                        mazePanel.setVisible(false);
                        generateRandomMaze(20, 20);
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
    
    private Player player;
    private Maze maze;
    private JFrame frame;
    private JPanel mazePanel;
    private JLabel[][] labels;
    private ImageIcon roadIcon;
    private ImageIcon wallIcon;
    private ImageIcon playerIconFront;
    private ImageIcon playerIconBack;
    private ImageIcon playerIconLeft;
    private ImageIcon playerIconRight;
    
    private static final Direction ABOVE = new Direction("above", 0, -1);
    private static final Direction BOTTOM = new Direction("bottom", 0, 1);
    private static final Direction LEFT = new Direction("left", -1, 0);
    private static final Direction RIGHT = new Direction("right", 1, 0);
    
    private static final int ROAD = 1;
    private static final int FRAME_WIDTH = 600;
}
