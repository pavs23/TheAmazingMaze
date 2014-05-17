import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.imageio.*;

import java.util.ArrayList;
import java.io.*;

/**
 * A class that represents common things between Game Modes.
 * Create the frame and game panel for the game.
 * @author floren
 *
 */
public class PlayerModes {
    // The icons.
    protected ImageIcon roadIcon;
    protected ImageIcon wallIcon;
    protected ImageIcon playerIconFront;
    protected ImageIcon playerIconBack;
    protected ImageIcon playerIconLeft;
    protected ImageIcon playerIconRight;
    protected ImageIcon hintIcon;
    protected ImageIcon coinIcon;
    
    protected static final int ROAD = 1;
    protected static final int FRAME_WIDTH = 600;
    
    private GameMode maze;
    private int mode;
    private int[][] mazeArray;
    
    private JFrame frame;
    private JPanel gamePanel;
    
    /**
     * Constructor of the class.
     * Create the frame and gamePanel for the game.
     * Create the maze and set the mode for the game.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param x the number of roads needed in X direction.
     * @param y the number of roads needed in Y direction.
     */
    public PlayerModes(int mode, int x, int y) {
        // Set the mode of the game.
        this.mode = mode;
        
        // Create the maze.
        if (mode == MazeGame.COIN_MODE) {
            maze = new CoinMaze(x, y);
        } else {
            maze = new AdventureMaze(x, y);
        }
        mazeArray = maze.getMazeArray();
        
        // Create the icons.
        generateIcon();
        
        // Create the JFrame.
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Create the Game Panel.
        gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.setVisible(true);
        
        addToFrame(gamePanel);
    }
    
    /**
     * Add a new JPanel to frame.
     * @param newPanel the JPanel that wants to be added to frame.
     */
    public void addToFrame(JPanel newPanel) {
        frame.add(newPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Add a new JPanel to gamePanel.
     * @param newPanel the JPanel that wants to be added to gamePanel.
     */
    public void addToGamePanel(JPanel newPanel) {
        gamePanel.add(newPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Bind a key event to the gamePanel.
     * @param key the key that corresponds to the action.
     * @param action the action that will be done upon a key stroke.
     * @param name the name of the action.
     */
    public void setKeyBinding(KeyStroke key, Action action, String name) {
        gamePanel.getInputMap().put(key, name);
        gamePanel.getActionMap().put(name, action);
    }
    
    /**
     * Freeze the game panel and its events.
     */
    public void freeze() {
        gamePanel.setEnabled(false);
    }
    
    /**
     * Enable the game panel and its events.
     */
    public void resume() {
        gamePanel.setEnabled(true);
    }
    
    /**
     * Get the mode of the game.
     * @return an integer representation of the mode of the game.
     */
    public int getMode() {
        return mode;
    }
    
    /**
     * Get the maze of the object.
     * @return the GameMode representing the maze of the object.
     */
    public GameMode getMaze() {
        return maze;
    }
    
    /**
     * Create the icons needed for the maze game.
     */
    public void generateIcon() {
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        
        File wallFile = new File("wall.jpg");
        File roadFile = new File("road.jpg");
        File playerFrontFile = new File("playerRoadFront.jpg");
        File playerBackFile = new File("playerRoadBack.jpg");
        File playerLeftFile = new File("playerRoadLeft.jpg");
        File playerRightFile = new File("playerRoadRight.jpg");
        File hintFile = new File("hintTile.jpg");
        File coinFile = new File("coinTile.jpg");
        
        BufferedImage wallImg;
        BufferedImage roadImg;
        BufferedImage playerFrontImg;
        BufferedImage playerBackImg;
        BufferedImage playerLeftImg;
        BufferedImage playerRightImg;
        BufferedImage hintImg;
        BufferedImage coinImg;
        
        Image scaledWall;
        Image scaledRoad;
        Image scaledPlayerFront;
        Image scaledPlayerBack;
        Image scaledPlayerLeft;
        Image scaledPlayerRight;
        Image scaledHint;
        Image scaledCoin;
        
        try { 
            // Read the image.
            wallImg = ImageIO.read(wallFile);
            roadImg = ImageIO.read(roadFile);
            playerFrontImg = ImageIO.read(playerFrontFile);
            playerBackImg = ImageIO.read(playerBackFile);
            playerLeftImg = ImageIO.read(playerLeftFile);
            playerRightImg = ImageIO.read(playerRightFile);
            hintImg = ImageIO.read(hintFile);
            coinImg = ImageIO.read(coinFile);
            
            // Set the image dimension.
            int height = FRAME_WIDTH/yDimension;
            int width = FRAME_WIDTH/xDimension;
            scaledWall = wallImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledRoad = roadImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerFront = playerFrontImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerBack = playerBackImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerLeft = playerLeftImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayerRight = playerRightImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledHint = hintImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledCoin = coinImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    
            // Create icon from image.        
            wallIcon = new ImageIcon(scaledWall);
            roadIcon = new ImageIcon(scaledRoad);
            playerIconFront = new ImageIcon(scaledPlayerFront);
            playerIconBack = new ImageIcon(scaledPlayerBack);
            playerIconLeft = new ImageIcon(scaledPlayerLeft);
            playerIconRight = new ImageIcon(scaledPlayerRight);
            hintIcon = new ImageIcon(scaledHint);
            coinIcon = new ImageIcon(scaledCoin);
            
        } catch (IOException e) {}
    }
    
    /**
     * Generate a maze panel that will consist of labels as grids.
     * @param labels an array of labels that will be in the maze panel.
     * @return the maze panel created.
     */
    public JPanel generateMazePanel(JLabel[][] labels) {
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;

        // Create panel for the maze.
        JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(xDimension, yDimension));
        mazePanel.setVisible(true);
        for (int j = 0; j < yDimension; j++) {
            for (int i = 0; i < xDimension; i++) {
                if (mazeArray[i][j] == ROAD) {
                    labels[i][j].setIcon(roadIcon);
                } else {
                    labels[i][j].setIcon(wallIcon);
                }
                mazePanel.add(labels[i][j]);
            }
        }
        
        // Paint the coin if it's coin mode.
        if (mode == MazeGame.COIN_MODE) {
            CoinMaze coinMaze = (CoinMaze) maze;
            ArrayList<Coordinate> coins = coinMaze.getCoinCoordinates();
            for (Coordinate position : coins) {
                labels[position.getX()][position.getY()].setIcon(coinIcon);
            }
        }
        return mazePanel;
    }
    
    /**
     * Generate an array of labels based on the size of the frame and maze.
     * @return the created array of labels.
     */
    public JLabel[][] generateLabels() {
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        JLabel[][] labels = new JLabel[xDimension][yDimension];
        for (int j = 0; j < yDimension; j++) {
            for (int i = 0; i < xDimension; i++) {
                labels[i][j] = new JLabel();
                labels[i][j].setPreferredSize(new Dimension(FRAME_WIDTH/xDimension, FRAME_WIDTH/yDimension));
            }
        }
        return labels;
    }
     
    /**
     * Create player for the game.
     * @param name the name of the player.
     * @return the Player that is created.
     */
    public Player generatePlayer(String name) {
        Coordinate startCoordinate = maze.getStartCoordinate();
        Player player = new Player(startCoordinate, name);
        return player;
    }
        
        
        
    /**
     * Paint the player in the maze.
     * @param coordinate the Coordinate of player..
     * @param direction the direction that the player is facing.
     * @param labels the labels associated with the player.
     */
    public void paintPlayer(Player player, Coordinate coordinate, Direction direction, JLabel[][] labels) {
        int xPos = coordinate.getX();
        int yPos = coordinate.getY();
        if (direction.equals(MazeGame.NORTH)) {
            labels[xPos][yPos].setIcon(playerIconBack);
        } else if (direction.equals(MazeGame.SOUTH)) {
            labels[xPos][yPos].setIcon(playerIconFront);
        } else if (direction.equals(MazeGame.WEST)) {
            labels[xPos][yPos].setIcon(playerIconLeft);
        } else if (direction.equals(MazeGame.EAST)) {
            labels[xPos][yPos].setIcon(playerIconRight);
        }
    }
       
    /**
     * Paint road in the maze.
     * @param pos the Coordinate of road.
     * @param labels the labels associated with the road.
     */
    public void paintRoad(Coordinate pos, JLabel[][] labels) {
        labels[pos.getX()][pos.getY()].setIcon(roadIcon);
    }
    

    /**
     * Move the player in the system. Update the player coordinates and repaint.
     * If the player has finished, send a congratulation message.
     * @param player the player that needs to be moved.
     * @param direction the direction of movement of the player.
     * @param labels the labels associated with the player.
     * @param maze the maze associated with the player.
     */
    public void movePlayer(Player player, Direction direction, JLabel[][] labels, GameMode maze) {
        Coordinate curr = player.getCoordinate();
        if (maze.isPath(curr, direction)) {
            paintRoad(curr, labels);
            // Find the new position of the player.
            int currX = curr.getX();
            int currY = curr.getY();
            currX += direction.getXDirection();
            currY += direction.getYDirection();
            Coordinate newPos = new Coordinate(currX, currY);
            player.setCoordinate(newPos);
            paintPlayer(player, newPos, direction, labels);
            
            // If coin mode, find out whether a player find the coin or not.
            if (mode == MazeGame.COIN_MODE) {
                CoinMaze coinMaze = (CoinMaze) maze;
                if (coinMaze.coinFound(newPos)) {
                    coinMaze.removeCoin(newPos);
                }
            }
            
            
            if (maze.gameFinished(newPos)) {
                System.out.println(player.getName() + " win! Congrats!");
                /*
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
                        paintRoad(player.getCoordinate());
                        player.setCoordinate(maze.getStartCoordinate());
                        paintPlayer(player.getCoordinate(), RIGHT);
                        
                    }
                });
                newGame.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        dialog.setVisible(false);
                        gamePanel.setVisible(false);
                        frame.dispose();
                        new SinglePlayer("normal", 20, 20);
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
            */
            }
        }
    }
    
}
