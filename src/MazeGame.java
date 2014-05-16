/**
 * A class that connects the game with UI selection.
 * @author floren
 *
 */

public class MazeGame {
    // Some constants for the game.
    public static final int SINGLE_PLAYER = 0;
    public static final int MULTI_PLAYER = 1;
    public static final int ADVENTURE_MODE = 0;
    public static final int COIN_MODE = 1;
    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 1;

    public static final Direction NORTH = new Direction(0, -1);
    public static final Direction SOUTH = new Direction(0, 1);
    public static final Direction WEST = new Direction(-1, 0);
    public static final Direction EAST = new Direction(1, 0);
    
    /**
     * Constructor to create the maze game.
     * @param player SINGLE_PLAYER or MULTI_PLAYER.
     * @param mode ADVENTURE_MODE or COIN_MODE.
     * @param difficulty EASY or MEDIUM or HARD.
     */
    public MazeGame(int player, int mode, int difficulty) {
        int x = 0;
        int y = 0;
        if (difficulty == EASY) {
            x = 10;
            y = 10;
        } else if (difficulty == MEDIUM) {
            x = 20;
            y = 20;
        } else if (difficulty == HARD) {
            x = 30;
            y = 30;
        }
        
        if (player == SINGLE_PLAYER) {
            new SinglePlayer(mode, x, y);
        } else if (player == MULTI_PLAYER) {
            new MultiPlayer(mode, x, y);
        }
    }
}
