import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public abstract class PlayerModes {
    // The icons.
    protected ImageIcon roadIcon;
    protected ImageIcon wallIcon;
    protected ImageIcon hintIcon;
    protected ImageIcon coinIcon;

    public static final int MAZE_PANEL_WIDTH = 600;
    
    private GameMode maze;
    private int mode;
    private int[][] mazeArray;
    private int iconWidth;
    private int iconHeight;
    
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel sidePanel;
    private GamePausedPanel pausePanel;
    
    private JButton mainMenuButton;
    private JButton pauseButton;
    
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
        
        // Create the frame.
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        
        // Create the gamePanel.
        gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.setVisible(true);
        
        // Create the sidePanel and menu. 
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setVisible(true);    
        generateSideMenu();
        
        addToFrame(gamePanel);
        addToFrame(sidePanel);
        gamePanel.requestFocus();
        
        // Set event listener to gamePanel.
        setEventListenerToMaze();
    }
    
    public abstract void setEventListenerToMaze();
    
    /**
     * Add a new JComponent to frame.
     * @param newComponent the JComponent that wants to be added to frame.
     */
    public void addToFrame(JComponent newComponent) {
        frame.add(newComponent);
    }
    
    /**
     * Add a new JComponent to gamePanel.
     * @param newComponent the JComponent that wants to be added to gamePanel.
     */
    public void addToGamePanel(JComponent newComponent) {
        gamePanel.add(newComponent);
    }
    
    /**
     * Add a new JComponent to sidePanel.
     * @param newComponent the JComponent that wants to be added to sidePanel.
     */
    public void addToSidePanel(JComponent newComponent) {
        sidePanel.add(newComponent);
    }
    
    /**
     * Pack and show the frame after all components have been added.
     * Has to be called in order to show the frame.
     */
    public void showFrame() {  
        frame.pack();
        frame.setLocationRelativeTo(null);
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
     * Freeze the panel, buttons, and its events.
     */
    public void freeze() {
        gamePanel.setEnabled(false);
        sidePanel.setEnabled(false);
        mainMenuButton.setEnabled(false);
        pauseButton.setEnabled(false);
    }
    
    /**
     * Enable the panel, buttons, and its events.
     */
    public void resume() {
        gamePanel.setEnabled(true);
        gamePanel.requestFocus();
        sidePanel.setEnabled(true);
        mainMenuButton.setEnabled(true);
        pauseButton.setEnabled(true);
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
                if (mazeArray[i][j] == MazeGame.ROAD) {
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
                labels[i][j].setPreferredSize(new Dimension(MAZE_PANEL_WIDTH/xDimension, MAZE_PANEL_WIDTH/yDimension));
            }
        }
        return labels;
    }
     
    /**
     * Create player for the game.
     * @param name the name of the player.
     * @param playerCode the code of the character of the player.
     * @return the Player that is created.
     */
    public Player generatePlayer(String name, int playerCode) {
        Coordinate startCoordinate = maze.getStartCoordinate();
        File playerFrontFile;
        File playerBackFile;
        File playerLeftFile;
        File playerRightFile;
        BufferedImage playerFrontImg;
        BufferedImage playerBackImg;
        BufferedImage playerLeftImg;
        BufferedImage playerRightImg;
        Image scaledPlayerFront;
        Image scaledPlayerBack;
        Image scaledPlayerLeft;
        Image scaledPlayerRight;
        ImageIcon playerIconFront = null;
        ImageIcon playerIconBack = null;
        ImageIcon playerIconLeft = null;
        ImageIcon playerIconRight = null;
        if (playerCode == MazeGame.PLAYER_0) {          
            playerFrontFile = new File("playerRoadFront.jpg");
            playerBackFile = new File("playerRoadBack.jpg");
            playerLeftFile = new File("playerRoadLeft.jpg");
            playerRightFile = new File("playerRoadRight.jpg");
            try {
                playerFrontImg = ImageIO.read(playerFrontFile);
                playerBackImg = ImageIO.read(playerBackFile);
                playerLeftImg = ImageIO.read(playerLeftFile);
                playerRightImg = ImageIO.read(playerRightFile);
                scaledPlayerFront = playerFrontImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                scaledPlayerBack = playerBackImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                scaledPlayerLeft = playerLeftImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                scaledPlayerRight = playerRightImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                playerIconFront = new ImageIcon(scaledPlayerFront);
                playerIconBack = new ImageIcon(scaledPlayerBack);
                playerIconLeft = new ImageIcon(scaledPlayerLeft);
                playerIconRight = new ImageIcon(scaledPlayerRight);
            } catch (IOException e) {}        
        }
        Player player = new Player(startCoordinate, name, playerIconFront, playerIconBack, playerIconLeft, playerIconRight);
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
            labels[xPos][yPos].setIcon(player.getBackView());
        } else if (direction.equals(MazeGame.SOUTH)) {
            labels[xPos][yPos].setIcon(player.getFrontView());
        } else if (direction.equals(MazeGame.WEST)) {
            labels[xPos][yPos].setIcon(player.getLeftView());
        } else if (direction.equals(MazeGame.EAST)) {
            labels[xPos][yPos].setIcon(player.getRightView());
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
                if (coinMaze.isCoinFound(newPos)) {
                    coinMaze.removeCoin(newPos);
                }
            }
            
            // The player wins.
            if (maze.gameFinished(newPos)) {
                System.out.println(player.getName() + " win! Congrats!");
                
            }
        }
    }
    
    /**
     * Generate the common buttons on the side menu and their listeners.
     */
    private void generateSideMenu() {
        mainMenuButton = new JButton("Main Menu");
        pauseButton = new JButton("Pause");
        
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                freeze();
                gamePanel.setVisible(false);
                sidePanel.setVisible(false);
                JButton resumeButton = new JButton("Resume");
                
                pausePanel = new GamePausedPanel();
                pausePanel.add(resumeButton);
                
                resumeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pausePanel.setVisible(false);
                        gamePanel.setVisible(true);
                        sidePanel.setVisible(true);
                        resume();
                    }
                });
                
                pausePanel.add(resumeButton);
                frame.add(pausePanel);
            }
        });
        
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame.
                frame.dispose();
                // Create a new menu.
                (new MainScreen()).run();
            }
        });
        
        addToSidePanel(mainMenuButton);
        addToSidePanel(pauseButton);
    }
   
    /**
     * Create the icons needed for the maze game.
     */
    private void generateIcon() {
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        
        iconWidth = MAZE_PANEL_WIDTH/xDimension;
        iconHeight = MAZE_PANEL_WIDTH/yDimension;
                
        File wallFile = new File("wall.jpg");
        File roadFile = new File("road.jpg");
        File hintFile = new File("hintTile.jpg");
        File coinFile = new File("coinTile.jpg");
        
        BufferedImage wallImg;
        BufferedImage roadImg;
        BufferedImage hintImg;
        BufferedImage coinImg;
        
        Image scaledWall;
        Image scaledRoad;
        Image scaledHint;
        Image scaledCoin;
        
        try { 
            // Read the image.
            wallImg = ImageIO.read(wallFile);
            roadImg = ImageIO.read(roadFile);
            hintImg = ImageIO.read(hintFile);
            coinImg = ImageIO.read(coinFile);
            
            // Set the image dimension.
            scaledWall = wallImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledRoad = roadImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledHint = hintImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledCoin = coinImg.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                    
            // Create icon from image.        
            wallIcon = new ImageIcon(scaledWall);
            roadIcon = new ImageIcon(scaledRoad);
            hintIcon = new ImageIcon(scaledHint);
            coinIcon = new ImageIcon(scaledCoin);
            
        } catch (IOException e) {}
    } 
}
