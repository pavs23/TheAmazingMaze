import javax.swing.*;

/**
 * A class to represent the player in the maze.
 * @author floren
 *
 */
public class Player {
    private Coordinate coordinate;
    private String name;
    private ImageIcon frontView;
    private ImageIcon backView;
    private ImageIcon leftView;
    private ImageIcon rightView;
    
    
    /**
     * Constructor of the class.
     * @param initialCoordinate the initial coordinate of player.
     * @param name the name of the player.
     */
    public Player(Coordinate initialCoordinate, String name, 
            ImageIcon front, ImageIcon back, ImageIcon left, ImageIcon right) {
        coordinate = initialCoordinate;
        this.name = name;
        this.frontView = front;
        this.backView = back;
        this.leftView = left;
        this.rightView = right;
    }
    
    /**
     * Get the coordinate of the player.
     * @return the coordinate position.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
    
    /**
     * Set the current position of the player.
     * @param currCoordinate the current position of player.
     */
    public void setCoordinate(Coordinate currCoordinate) {
        coordinate = currCoordinate;
    }
    
    /**
     * Get the player's name.
     * @return the player's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the front view Icon of the player.
     * @return the Icon that represents the player's front view.
     */
    public ImageIcon getFrontView() {
        return frontView;
    }
    
    /**
     * Get the back view Icon of the player.
     * @return the Icon that represents the player's back view.
     */
    public ImageIcon getBackView() {
        return backView;
    }
    
    /**
     * Get the left view Icon of the player.
     * @return the Icon that represents the player's left view.
     */
    public ImageIcon getLeftView() {
        return leftView;
    }
    
    /**
     * Get the right view Icon of the player.
     * @return the icon that represents the player's right view.
     */
    public ImageIcon getRightView() {
        return rightView;
    }
}
