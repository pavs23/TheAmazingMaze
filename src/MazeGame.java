/**
 * A class that connects the game with UI selection.
 * @author floren
 *
 */

public class MazeGame {    
    // THIS ONE NEEDS TO BE REPLACED LATER AFTER CHARACTER SELECTION HAS BEEN MADE.
    // THIS IS JUST A TEMPLATE TO MAKE THE 18/5 VERSION WORKED.
    public MazeGame (int players, int mode, int difficulty) {
        if (players == Game.SINGLE_PLAYER) {
            new MazeGame(Game.PLAYER_0, "player 1", mode, difficulty);
        } else {
            new MazeGame(Game.PLAYER_0, "player 1", Game.PLAYER_0, "player 2", mode, difficulty);
        }
    }
    
     /**
     * Constructor to create a single player game.
     * @param playerCode the code representing player's character.
     * @param playerName the name of the player.
     * @param mode ADVENTURE_MODE or COIN_MODE.
     * @param difficulty EASY or MEDIUM or HARD.
     */
    public MazeGame(int playerCode, String playerName, int mode, int difficulty) {
        new SinglePlayer(mode, difficulty, playerCode, playerName);
    }
    
    /**
     * Constructor to create a multiplayer game.
     * @param player1Code the code representing player 1's character.
     * @param player1Name the name of player 1.
     * @param player2Code the code representing player 2's character.
     * @param player2Name the name of player 2.
     * @param mode ADVENTURE_MODE or COIN_MODE.
     * @param difficulty EASY or MEDIUM or HARD.
     */
    public MazeGame(int player1Code, String player1Name, int player2Code, String player2Name, int mode, int difficulty) {
        new MultiPlayer(mode, difficulty, player1Code, player1Name, player2Code, player2Name);
    }
}
