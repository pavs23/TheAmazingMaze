import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;

/**
 * A class that represents common things between Game Modes.
 * Create the frame and game panel for the game.
 * @author floren
 *
 */
public abstract class PlayerModes {
    public static final Color MAZE_BACKGROUND_COLOR = new Color(230, 230, 250);
    
    // The icons.
    private ImageIcon roadIcon;
    private ImageIcon wallIcon;
    private ImageIcon hintIcon;
    private ImageIcon coinIcon;

    private GameMode maze;
    private int mode;
    private int[][] mazeArray;
    private int iconWidth;
    private int iconHeight;
    
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel sidePanel;
    private GamePausedPanel pausePanel = null;
    
    private StyledButton mainMenuButton;
    private StyledButton pauseButton;

    private static final int MAZE_PANEL_WIDTH = 540;
    
    /**
     * Constructor of the class.
     * Create the frame and gamePanel for the game.
     * Create the maze and set the mode for the game.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param difficulty the difficulty level of the maze.
     */
    public PlayerModes(int mode, int difficulty) {
        // Set the mode of the game.
        this.mode = mode;
        
        int x = 0;
        int y = 0;
        if (difficulty == Game.EASY) {
            x = Game.EASY_X;
            y = Game.EASY_Y;
        } else if (difficulty == Game.MEDIUM) {
            x = Game.MEDIUM_X;
            y = Game.MEDIUM_Y;
        } else if (difficulty == Game.HARD) {
            x = Game.HARD_X;
            y = Game.HARD_Y;
        }
        
        // Create the maze.
        if (mode == Game.COIN_MODE) {
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
        frame.getContentPane().setBackground(MAZE_BACKGROUND_COLOR);
        
        // Create the gamePanel.
        gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.setBackground(MAZE_BACKGROUND_COLOR);
        gamePanel.setOpaque(true);
        gamePanel.setVisible(true);
        
        // Create the sidePanel and menu. 
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(MAZE_BACKGROUND_COLOR);
        sidePanel.setOpaque(true);
        sidePanel.setVisible(true);    
        generateSideMenu();
        
        addToFrame(gamePanel);
        addToFrame(sidePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        
        // Set event listener to gamePanel.
        setEventListenerToMaze();
    }
    
    /**
     * A method to set the listeners to the maze game.
     */
    public abstract void setEventListenerToMaze();
    
    
    /**
     * A method that is called to indicate that a player has won.
     * @param playerName the name of the player that won the game.
     */
    public abstract void gameEndWin(String playerName);
  
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
        double buttonWidth = mainMenuButton.getWidth();
        double buttonHeight = mainMenuButton.getHeight();
        // Find mouse location and compare to button's.
        Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
        double mouseLocX = mouseLoc.getX();
        double mouseLocY = mouseLoc.getY();
        Point buttonLoc = mainMenuButton.getLocationOnScreen();
        double buttonStartX = buttonLoc.getX();
        double buttonStartY = buttonLoc.getY();
        if (mouseLocX >= buttonStartX && mouseLocX <= (buttonStartX + buttonWidth)
                && mouseLocY >= buttonStartY && mouseLocY <= (buttonStartY + buttonHeight)) {
            mainMenuButton.setFont(mainMenuButton.getFont().deriveFont(Font.BOLD));
        } 
        
        buttonWidth = pauseButton.getWidth();
        buttonHeight = pauseButton.getHeight();
        // Compare mouse location pause button's.
        buttonLoc = pauseButton.getLocationOnScreen();
        buttonStartX = buttonLoc.getX();
        buttonStartY = buttonLoc.getY();
        if (mouseLocX >= buttonStartX && mouseLocX <= (buttonStartX + buttonWidth)
                && mouseLocY >= buttonStartY && mouseLocY <= (buttonStartY + buttonHeight)) {
            pauseButton.setFont(pauseButton.getFont().deriveFont(Font.BOLD));
        } 
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
     * Get the scaled wall icon.
     * @return the ImageIcon of the wall.
     */
    public ImageIcon getWallIcon() {
        return wallIcon;
    }
    
    /**
     * Get the scaled road icon.
     * @return the ImageIcon of the road.
     */
    public ImageIcon getRoadIcon() {
        return roadIcon;
    }
    
    /**
     * Get the scaled hint icon.
     * @return the ImageIcon of the hint.
     */
    public ImageIcon getHintIcon() {
        return hintIcon;
    }
    
    /**
     * Get the scaled coin icon.
     * @return the ImageIcon of the coin.
     */
    public ImageIcon getCoinIcon() {
        return coinIcon;
    }

    
    /**
     * A method to throw away the frame.
     */
    public void disposeFrame() {
        frame.dispose();
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
                if (mazeArray[i][j] == Game.ROAD) {
                    labels[i][j].setIcon(roadIcon);
                } else {
                    labels[i][j].setIcon(wallIcon);
                }
                mazePanel.add(labels[i][j]);
            }
        }
        
        mazePanel.setBackground(MAZE_BACKGROUND_COLOR);
        
        // Paint the coin if it's coin mode.
        if (mode == Game.COIN_MODE) {
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

        Image scaledPlayerFront =  null;
        Image scaledPlayerBack = null;
        Image scaledPlayerLeft = null;
        Image scaledPlayerRight = null;
        ImageIcon playerIconFront;
        ImageIcon playerIconBack;
        ImageIcon playerIconLeft;
        ImageIcon playerIconRight;
        if (playerCode == Game.PLAYER_0) {          
            scaledPlayerFront = Game.PLAYER_0_FRONT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerBack = Game.PLAYER_0_BACK_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerLeft = Game.PLAYER_0_LEFT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerRight = Game.PLAYER_0_RIGHT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);               
        } else if (playerCode == Game.PLAYER_1) {
            scaledPlayerFront = Game.PLAYER_1_FRONT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerBack = Game.PLAYER_1_BACK_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerLeft = Game.PLAYER_1_LEFT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerRight = Game.PLAYER_1_RIGHT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        } else if (playerCode == Game.PLAYER_2) {
            scaledPlayerFront = Game.PLAYER_2_FRONT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerBack = Game.PLAYER_2_BACK_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerLeft = Game.PLAYER_2_LEFT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            scaledPlayerRight = Game.PLAYER_2_RIGHT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH); 
        }
        playerIconFront = new ImageIcon(scaledPlayerFront);
        playerIconBack = new ImageIcon(scaledPlayerBack);
        playerIconLeft = new ImageIcon(scaledPlayerLeft);
        playerIconRight = new ImageIcon(scaledPlayerRight); 
        Player player = new Player(startCoordinate, name, playerIconFront, playerIconBack, playerIconLeft, playerIconRight);
        return player;
    }
            
        
    /**
     * Paint the player in the maze.
     * @param player the player that will be painted.
     * @param coordinate the Coordinate of player..
     * @param direction the direction that the player is facing.
     * @param labels the labels associated with the player.
     */
    public void paintPlayer(Player player, Coordinate coordinate, Direction direction, JLabel[][] labels) {
        int xPos = coordinate.getX();
        int yPos = coordinate.getY();

        if (direction.equals(Game.NORTH)) {
            labels[xPos][yPos].setIcon(player.getBackView());
        } else if (direction.equals(Game.SOUTH)) {
            labels[xPos][yPos].setIcon(player.getFrontView());
        } else if (direction.equals(Game.WEST)) {
            labels[xPos][yPos].setIcon(player.getLeftView());
        } else if (direction.equals(Game.EAST)) {
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
            if (mode == Game.COIN_MODE) {
                CoinMaze coinMaze = (CoinMaze) maze;
                if (coinMaze.isCoinFound(newPos)) {
                    coinMaze.removeCoin(newPos);
                }
            }
            
            // The player wins.
            if (maze.gameFinished(newPos)) {
                gameEndWin(player.getName());        
            }
        }
    }
    
    /**
     * Generate the common buttons on the side menu and their listeners.
     */
    private void generateSideMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new FlowLayout());       
        mainMenu.setBorder(new EmptyBorder(40, 0, 20, 0));
        mainMenu.setBackground(MAZE_BACKGROUND_COLOR);
 
        mainMenuButton = new StyledButton("Main Menu");
        mainMenu.add(mainMenuButton);
        mainMenu.setVisible(true);
        
        JPanel pause = new JPanel();
        pause.setLayout(new FlowLayout()); 
        pause.setBorder(new EmptyBorder(0, 0, 20, 0));
        pause.setBackground(MAZE_BACKGROUND_COLOR);
        
        pauseButton = new StyledButton("Pause");
        pause.add(pauseButton);
        pause.setVisible(true);
        
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                freeze();
                // Dispose the current frame.
                frame.dispose();
                // Create a new menu.
                (new Game()).run();
            }
        });
        
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                freeze();
                gamePanel.setVisible(false);
                sidePanel.setVisible(false);
                JPanel resumePanel = new JPanel();
                resumePanel.setLayout(new FlowLayout());
                resumePanel.setBorder(new EmptyBorder(500, 0, 0, 0));
                resumePanel.setOpaque(false);
                StyledButton resumeButton = new StyledButton("Resume");
                resumeButton.setVisible(true);
                resumeButton.setPreferredSize(new Dimension(200, 100));
                resumeButton.setFont(new Font("Arial", Font.PLAIN, 40));
                resumePanel.add(resumeButton);
                if (pausePanel == null) {
                    pausePanel = new GamePausedPanel(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
                    pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));
                    pausePanel.addToPanel(resumePanel);
                    addToFrame(pausePanel);
                }
                
                pausePanel.setVisible(true);
                
                resumeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pausePanel.setVisible(false);
                        gamePanel.setVisible(true);
                        sidePanel.setVisible(true);
                        showFrame();
                        resume();
                    }
                });
                showFrame();
            }
        });
        
        JPanel objective = new JPanel();
        objective.setLayout(new BoxLayout(objective, BoxLayout.Y_AXIS));       
        objective.setBackground(new Color(100, 100, 250));
        objective.setBorder(new EmptyBorder(10, 10, 10, 10));
        objective.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel objTitle = new JLabel("OBJECTIVE");
        objTitle.setHorizontalAlignment(JLabel.CENTER);
        objTitle.setForeground(Color.WHITE);
        objTitle.setFont(new Font("Arial", Font.BOLD, 24));
        objTitle.setBackground(new Color(100, 100, 250));
        objTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        String text;
        if (mode == Game.ADVENTURE_MODE) {
            text = "Get to the end of the maze!";
        } else {
            text = "Collect all coins!";
        }
        
        JLabel objInst = new JLabel(text);
        objInst.setForeground(Color.WHITE);
        objInst.setFont(new Font("Arial", Font.PLAIN, 18));
        objInst.setBackground(new Color(100, 100, 250));
        objInst.setHorizontalAlignment(JLabel.CENTER);
        objective.add(objTitle);
        objective.add(objInst);
        
        addToSidePanel(objective);
        addToSidePanel(mainMenu);
        addToSidePanel(pause);
        
    }
   
    /**
     * Create the icons needed for the maze game.
     */
    private void generateIcon() {
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        
        iconWidth = MAZE_PANEL_WIDTH/xDimension;
        iconHeight = MAZE_PANEL_WIDTH/yDimension;

        // Set the image dimension.
        Image scaledWall = Game.WALL_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        Image scaledRoad = Game.ROAD_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        Image scaledHint = Game.HINT_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        Image scaledCoin = Game.COIN_IMAGE.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
                    
        // Create icon from image.        
        wallIcon = new ImageIcon(scaledWall);
        roadIcon = new ImageIcon(scaledRoad);
        hintIcon = new ImageIcon(scaledHint);
        coinIcon = new ImageIcon(scaledCoin);
    }
}
