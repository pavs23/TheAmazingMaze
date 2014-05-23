/**
 * A class that connects the game with UI selection.
 * @author floren
 *
 */

public class MazeGame {        
    /**
     * Constructor to create a maze game.
     * @param singleOrMulti SINGLE_PLAYER or MULTI_PLAYER
     * @param player1Code the code representing player 1's character.
     * @param player1Name the name of player 1.
     * @param player2Code the code representing player 2's character (can be anything in SINGLE_PLAYER).
     * @param player2Name the name of player 2 (can be anything in SINGLE_PLAYER).
     * @param mode ADVENTURE_MODE or COIN_MODE.
     * @param difficulty EASY or MEDIUM or HARD.
     */
    public MazeGame(int singleOrMulti, int player1Code, String player1Name, int player2Code, String player2Name, int mode, int difficulty) {
        if (singleOrMulti == Game.SINGLE_PLAYER) {
            new SinglePlayer(mode, difficulty, player1Code, player1Name);
        } else if (singleOrMulti == Game.MULTI_PLAYER) {
            new MultiPlayer(mode, difficulty, player1Code, player1Name, player2Code, player2Name);
        }
       
    }
}
