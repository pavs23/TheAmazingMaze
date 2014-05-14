import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.io.*;

public class PlayerModes {
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
    
    
    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public int getMode() {
        return mode;
    }
    
    public void createMaze(int x, int y) {
        if (mode == MazeGame.COIN_MODE) {
            maze = new CoinMaze(x, y);
        } else {
            maze = new AdventureMaze(x, y);
        }
        mazeArray = maze.getMazeArray();
    }
        
    public GameMode getMaze() {
        return maze;
    }
    
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
        
    public Player generatePlayer(String name) {
        Coordinate startCoordinate = maze.getStartCoordinate();
        Player player = new Player(startCoordinate, name);
        return player;
    }
        
        
        
    /**
     * Paint the player in the maze.
     * @param coordinate the Coordinate of player..
     * @param direction the direction that the player is facing.
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
     */
    public void paintRoad(Coordinate pos, JLabel[][] labels) {
        labels[pos.getX()][pos.getY()].setIcon(roadIcon);
    }
    

    /**
     * Move the player in the system. Update the player coordinates and repaint.
     * If the player has finished, send a congratulation message.
     * @param direction the direction of movement of the player.
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
