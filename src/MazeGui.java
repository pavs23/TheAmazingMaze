import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.imageio.*;

import java.io.*;

/**
 * A class to handle the user interface of the maze.
 * @author floren
 *
 */
public class MazeGui {
    public MazeGui(JFrame aFrame) {
        frame = aFrame;
    }
    
    public void paintMaze(int[][] mazeArray) {
        // Get the dimension of the array.
        int xDimension = mazeArray.length;
        int yDimension = mazeArray[0].length;
        
        // Set the JLabels to the frame.
        labels = new JLabel[xDimension][yDimension];
        for (int j = 0; j < yDimension; j++) {
            for (int i = 0; i < xDimension; i++) {
                labels[i][j] = new JLabel();
                labels[i][j].setPreferredSize(new Dimension(FRAME_WIDTH/xDimension,FRAME_WIDTH/yDimension));
                frame.add(labels[i][j]);
            }
        }
        
        // Update the icon for the maze (wall, road, and player).
        File wallFile = new File("wall.jpg");
        File roadFile = new File("road.jpg");
        File playerFile = new File("playerRoad.jpg");
        
        BufferedImage wallImg;
        BufferedImage roadImg;
        BufferedImage playerImg;
        Image scaledWall;
        Image scaledRoad;
        Image scaledPlayer;
        
        try { 
            wallImg = ImageIO.read(wallFile);
            roadImg = ImageIO.read(roadFile);
            playerImg = ImageIO.read(playerFile);
            
            int height = labels[0][0].getPreferredSize().height;
            int width = labels[0][0].getPreferredSize().width;
            scaledWall = wallImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledRoad = roadImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            scaledPlayer = playerImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            
            wallIcon = new ImageIcon(scaledWall);
            roadIcon = new ImageIcon(scaledRoad);
            playerIcon = new ImageIcon(scaledPlayer);
            
            for (int j = 0; j < yDimension; j++) {
                for (int i = 0; i < xDimension; i++) {
                    if (mazeArray[i][j] == ROAD) {
                        labels[i][j].setIcon(roadIcon);
                    } else {
                        labels[i][j].setIcon(wallIcon);
                    }
                    frame.add(labels[i][j]);
                }
            }
        } catch(IOException e){}
    }
    
    /**
     * Paint the player in the maze.
     * @param direction the direction that the player is facing.
     */
    public void paintPlayer(int xPos, int yPos, Direction direction) {
        labels[xPos][yPos].setIcon(playerIcon);
    }
    
    /**
     * Paint the label as a road.
     * @param xPos the x position of the label.
     * @param yPos the y position of the label.
     */
    public void paintRoad(int xPos, int yPos) {
        labels[xPos][yPos].setIcon(roadIcon);
    }
    
    private JFrame frame;
    private JLabel[][] labels;
    private ImageIcon playerIcon;
    private ImageIcon roadIcon;
    private ImageIcon wallIcon;
    
    private static final Direction ABOVE = new Direction("above", 0, -1);
    private static final Direction BOTTOM = new Direction("bottom", 0, 1);
    private static final Direction LEFT = new Direction("left", -1, 0);
    private static final Direction RIGHT = new Direction("right", 1, 0);
    
    private static final int FRAME_WIDTH = 500;
    private static final int ROAD = 1;
}
