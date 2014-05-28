import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * A class that represents Multiplayer Game.
 * @author floren
 *
 */
public class MultiPlayer extends PlayerModes {
    private Player player1;
    private Player player2;
    private GameMode maze1;
    private GameMode maze2;
    private JPanel mazePanel1;
    private JPanel mazePanel2;
    private JLabel[][] labels1;
    private JLabel[][] labels2;
    private Timer[] timers = new Timer[8];
    private boolean gameFinished = false;

    /**
     * Constructor of the class to create the maze MazeGame.
     * @param mode the mode of the single game (ADVENTURE_MODE/COIN_MODE).
     * @param difficulty the difficulty of the maze.
     * @param player1Code the code representing the player 1's character.
     * @param player1Name the name of the player 1.
     * @param player2Code the code representing the player 2's character.
     * @param player2Name the name of the player 2.
     */
    public MultiPlayer(int mode, int difficulty, int player1Code, String player1Name, int player2Code, String player2Name) {
        // Create the super class.
        super(mode, difficulty);
        
        // Initialize maze;
        maze1 = getMaze();
        if (mode == Game.COIN_MODE) {
            CoinMaze coinMaze = (CoinMaze) maze1;
            maze2 = (GameMode) coinMaze.generateClone();
        } else {
            maze2 = getMaze();
        }
        
        // Create the labels, mazePanel, and player.
        labels1 = generateLabels();
        labels2 = generateLabels();
        mazePanel1 = generateMazePanel(labels1);
        mazePanel2 = generateMazePanel(labels2);
        
        
        JPanel mazeAndInst1 = new JPanel();
        mazeAndInst1.setLayout(new BoxLayout(mazeAndInst1, BoxLayout.Y_AXIS));
        mazeAndInst1.setBorder(new EmptyBorder(0, 0, 0, 30));
        mazeAndInst1.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        
        JPanel mazeAndInst2 = new JPanel();
        mazeAndInst2.setLayout(new BoxLayout(mazeAndInst2, BoxLayout.Y_AXIS));
        mazeAndInst2.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        
        JPanel instruction1 = new JPanel();
        instruction1.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        instruction1.setLayout(new FlowLayout());
        
        JPanel instruction2 = new JPanel();
        instruction2.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        instruction2.setLayout(new FlowLayout());
        
        JPanel playerLabel1 = new JPanel();
        playerLabel1.setBorder(new EmptyBorder(0, 0, 0, 20) );
        playerLabel1.setLayout(new BoxLayout(playerLabel1, BoxLayout.X_AXIS));
        playerLabel1.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        
        JPanel playerLabel2 = new JPanel();
        playerLabel2.setBorder(new EmptyBorder(0, 0, 0, 20) );
        playerLabel2.setLayout(new BoxLayout(playerLabel2, BoxLayout.X_AXIS));
        playerLabel2.setBackground(PlayerModes.MAZE_BACKGROUND_COLOR);
        
        JLabel player1Icon = new JLabel();
        Image player1Img;
        if (player1Code == Game.PLAYER_0) {
            player1Img = Game.PLAYER_0_IMAGE;
        } else if (player1Code == Game.PLAYER_1) {
            player1Img = Game.PLAYER_1_IMAGE;
        } else {
            player1Img = Game.PLAYER_2_IMAGE;
        }
        ImageIcon icon1 = new ImageIcon(player1Img.getScaledInstance(50, 80, Image.SCALE_SMOOTH));
        player1Icon.setIcon(icon1);
        player1Icon.setBorder(new EmptyBorder(0, 0, 0, 20) );
        
        JLabel player2Icon = new JLabel();
        Image player2Img;
        if (player2Code == Game.PLAYER_0) {
            player2Img = Game.PLAYER_0_IMAGE;
        } else if (player1Code == Game.PLAYER_1) {
            player2Img = Game.PLAYER_1_IMAGE;
        } else {
            player2Img = Game.PLAYER_2_IMAGE;
        }
        ImageIcon icon2 = new ImageIcon(player2Img.getScaledInstance(50, 80, Image.SCALE_SMOOTH));
        player2Icon.setIcon(icon2);
        player2Icon.setBorder(new EmptyBorder(0, 0, 0, 20) );
        
        JLabel namePlayer1 = new JLabel();
        namePlayer1.setText(player1Name);
        namePlayer1.setHorizontalAlignment(JLabel.CENTER);
        namePlayer1.setForeground(Color.WHITE);
        namePlayer1.setOpaque(false);
        namePlayer1.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel player1Keys = new JLabel();
        
        JLabel namePlayer2 = new JLabel();
        namePlayer2.setText(player2Name);
        namePlayer2.setHorizontalAlignment(JLabel.CENTER);
        namePlayer2.setForeground(Color.WHITE);
        namePlayer2.setOpaque(false);
        namePlayer2.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel player2Keys = new JLabel();
        
        
        File aswdFile = new File("images/ASWD_Key.jpg");
        Image aswdImage = null;
        try {
            aswdImage = ImageIO.read(aswdFile);
        } catch (IOException e) {}
        Image scaledAswd = aswdImage.getScaledInstance(220, 80, Image.SCALE_SMOOTH);    
        ImageIcon aswdIcon = new ImageIcon(scaledAswd);
        player1Keys.setIcon(aswdIcon);
         
        File arrowFile = new File("images/Arrow_Key.jpg");
        Image arrowImage = null;
        try {
            arrowImage = ImageIO.read(arrowFile);
        } catch (IOException e) {}
        Image scaledArrow = arrowImage.getScaledInstance(220, 80, Image.SCALE_SMOOTH);    
        ImageIcon arrowIcon = new ImageIcon(scaledArrow);
        player2Keys.setIcon(arrowIcon);
        
        playerLabel1.add(player1Icon);
        playerLabel1.add(namePlayer1);
        
        instruction1.add(playerLabel1);
        instruction1.add(player1Keys);
        
        mazeAndInst1.add(instruction1);
        mazeAndInst1.add(mazePanel1);
        
        playerLabel2.add(player2Icon);
        playerLabel2.add(namePlayer2);
        
        instruction2.add(playerLabel2);
        instruction2.add(player2Keys);
        
        mazeAndInst2.add(instruction2);
        mazeAndInst2.add(mazePanel2);
        
        addToGamePanel(mazeAndInst1);
        addToGamePanel(mazeAndInst2);
        
        player1 = generatePlayer(player1Name, player1Code);
        player2 = generatePlayer(player2Name, player2Code);
        
        // Paint the players.

        paintPlayer(player1, player1.getCoordinate(), Game.EAST, labels1);
        paintPlayer(player2, player2.getCoordinate(), Game.EAST, labels2);
        
        // All components are added, show the frame.
        showFrame();
    }
    
    @Override
    public void freeze() {
        super.freeze();
        stopTimers();
    }
    
    @Override
    public void resume() {
        super.resume();
        setTimersToNull();
    }
    
    /**
     * Set the event listener to the frame (arrows key press and asdw key press).
     * Use key binding for it.
     */ 
    public void setEventListenerToMaze() {
        // Key bindings (so that it works with panel).
        // 2nd player
        Action leftKeyPressed = new PressedActionPlayer2 (0, Game.WEST); 
        Action leftKeyReleased = new ReleasedAction(0);
        Action rightKeyPressed =  new PressedActionPlayer2 (1, Game.EAST); 
        Action rightKeyReleased = new ReleasedAction(1);
        Action upKeyPressed = new PressedActionPlayer2 (2, Game.NORTH);  
        Action upKeyReleased = new ReleasedAction(2);
        Action downKeyPressed = new PressedActionPlayer2 (3, Game.SOUTH); 
        Action downKeyReleased = new ReleasedAction(3);
        
        // 1st player.
        Action aKeyPressed = new PressedActionPlayer1 (4, Game.WEST); 
        Action aKeyReleased = new ReleasedAction(4);
        Action dKeyPressed = new PressedActionPlayer1 (5, Game.EAST); 
        Action dKeyReleased = new ReleasedAction(5);
        Action wKeyPressed = new PressedActionPlayer1 (6, Game.NORTH); 
        Action wKeyReleased = new ReleasedAction(6);
        Action sKeyPressed = new PressedActionPlayer1 (7, Game.SOUTH); 
        Action sKeyReleased = new ReleasedAction(7);
        
        KeyStroke leftKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
        KeyStroke rightKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
        KeyStroke upKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
        KeyStroke downKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
        
        KeyStroke leftKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
        KeyStroke rightKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
        KeyStroke upKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
        KeyStroke downKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
        
        setKeyBinding(leftKeyDown, leftKeyPressed, "leftD");
        setKeyBinding(rightKeyDown, rightKeyPressed, "rightD");
        setKeyBinding(upKeyDown, upKeyPressed, "upD");
        setKeyBinding(downKeyDown, downKeyPressed, "downD");
        
        setKeyBinding(leftKeyUp, leftKeyReleased, "leftU");
        setKeyBinding(rightKeyUp, rightKeyReleased, "rightU");
        setKeyBinding(upKeyUp, upKeyReleased, "upU");
        setKeyBinding(downKeyUp, downKeyReleased, "downU");
        
        KeyStroke aKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false);
        KeyStroke dKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false);
        KeyStroke wKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false);
        KeyStroke sKeyDown = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false);
        
        KeyStroke aKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true);
        KeyStroke dKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true);
        KeyStroke wKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true);
        KeyStroke sKeyUp = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true);
        
        setKeyBinding(aKeyDown, aKeyPressed, "aD");
        setKeyBinding(dKeyDown, dKeyPressed, "dD");
        setKeyBinding(wKeyDown, wKeyPressed, "wD");
        setKeyBinding(sKeyDown, sKeyPressed, "sD");
        
        setKeyBinding(aKeyUp, aKeyReleased, "aU");
        setKeyBinding(dKeyUp, dKeyReleased, "dU");
        setKeyBinding(wKeyUp, wKeyReleased, "wU");
        setKeyBinding(sKeyUp, sKeyReleased, "sU"); 
    }
    
    /**
     * A class that represents pressed action.
     * @author floren
     *
     */
    @SuppressWarnings("serial")
    private class PressedActionPlayer1 extends AbstractAction {
        private int index;
        private Direction dir;
        
        
        /**
         * Constructor of the class.
         * @param index the index of timer related to the event listener.
         * @param dir the direction of player movement that is tied to this listener.
         */
        public PressedActionPlayer1(int index, Direction dir) {
            this.index = index;
            this.dir = dir;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timers[index] == null) {      
                timers[index] = new Timer(Game.MOVING_TIME, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                         movePlayer(player1, dir, labels1, maze1);
                         if (gameFinished) {
                             timers[index].stop();
                         }
                    }
                });
                movePlayer(player1, dir, labels1, maze1); 
                timers[index].start();
            }
        }
    }
    
    
    /**
     * A class that represents pressed action.
     * @author floren
     *
     */
    @SuppressWarnings("serial")
    private class PressedActionPlayer2 extends AbstractAction {
        private int index;
        private Direction dir;
        
        
        /**
         * Constructor of the class.
         * @param index the index of timer related to the event listener.
         * @param dir the direction of player movement that is tied to this listener.
         */
        public PressedActionPlayer2(int index, Direction dir) {
            this.index = index;
            this.dir = dir;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timers[index] == null) {      
                timers[index] = new Timer(Game.MOVING_TIME, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                         movePlayer(player2, dir, labels2, maze2);
                         if (gameFinished) {
                             timers[index].stop();
                         }
                    }
                });
                movePlayer(player2, dir, labels2, maze2); 
                timers[index].start();
            }
        }
    }
    
    /**
     * A class that represents released action.
     * @author floren
     *
     */
    @SuppressWarnings("serial")
    private class ReleasedAction extends AbstractAction {
        private int index;
        
        /**
         * Constructor of the class.
         * @param index the index of timer related to the event listener.
         */
        public ReleasedAction(int index) {
            this.index = index;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (timers[index] != null) {
                timers[index].stop();
                timers[index] = null;
            }
        }
    }
    
    /**
     * Dispose the frame, stop and dispose the timers, and show which player wins on game end.
     * @param playerName the name of the winning player.
     */
    public void gameEndWin(String playerName) {
        gameFinished = true;
        freeze();
        disposeFrame();
        // Create a frame for winning player.
        JFrame newFrame = new JFrame();
        newFrame.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        newFrame.setResizable(false);
        newFrame.setLayout(null);
        JPanel winPanel = new WinPanel(newFrame, playerName);
        winPanel.setLocation(new Point(0, 0));
        newFrame.add(winPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
    }
    
    /**
     * A helper method to stop all timers.
     */
    private void stopTimers() {
        for (int i = 0; i < timers.length; i++) {
            if (timers[i] != null) {
                timers[i].stop();
            }
        }
    }
    
    /**
     * A helper method to set all timers to null.
     */
    private void setTimersToNull() {
        for (int i = 0; i < timers.length; i++) {
            timers[i] = null;
        }
    }
}
