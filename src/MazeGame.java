/**
 * A class that connects the game with UI selection.
 * @author floren
 *
 */

public class MazeGame {    
    /**
     * Constructor of the class to create the maze game.
     */
    public MazeGame(String players, String mode, String difficulty) {
        int x = 0;
        int y = 0;
        if (difficulty.equals("easy")) {
            x = 10;
            y = 10;
        } else if (difficulty.equals("normal")) {
            x = 20;
            y = 20;
        } else if (difficulty.equals("hard")) {
            x = 30;
            y = 30;
        }
        if (players.equals("single")) {
            new SinglePlayer(mode, x, y);
        } else if (players.equals("multi")) {
            new MultiPlayer(mode, x, y);
        }
    }
    

    
}
