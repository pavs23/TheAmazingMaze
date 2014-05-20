import java.awt.event.*;

import javax.swing.*;
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
     * @param x the number of roads needed in X direction.
     * @param y the number of roads needed in Y direction.
     * @param player1Code the code representing the player 1's character.
     * @param player1Name the name of the player 1.
     * @param player2Code the code representing the player 2's character.
     * @param player2Name the name of the player 2.
     */
    public MultiPlayer(int mode, int x, int y, int player1Code, String player1Name, int player2Code, String player2Name) {
        // Create the super class.
        super(mode, x, y);
        
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
        addToGamePanel(mazePanel1);
        addToGamePanel(mazePanel2);
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
    @SuppressWarnings("serial")
    public void setEventListenerToMaze() {
        // Key bindings (so that it works with panel).
        // 2nd player
        Action leftKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[0] == null) {      
                    timers[0] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player2, Game.WEST, labels2, maze2); 
                             if (gameFinished) {
                                 timers[0].stop();
                             }
                        }
                    });
                    movePlayer(player2, Game.WEST, labels2, maze2);        
                    timers[0].start();
                }   
            }
        };
        Action leftKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[0] != null) {
                    timers[0].stop();
                    timers[0] = null;
                }
            }
        };
        Action rightKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[1] == null) {     
                    timers[1] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player2, Game.EAST, labels2, maze2);
                             if (gameFinished) {
                                 timers[1].stop();
                             }
                        }
                    });
                    movePlayer(player2, Game.EAST, labels2, maze2);   
                    timers[1].start();
                }
            }
        };
        Action rightKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[1] != null) {
                    timers[1].stop();
                    timers[1] = null;
                }
            }
        };
        Action upKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[2] == null) {      
                    timers[2] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player2, Game.NORTH, labels2, maze2); 
                             if (gameFinished) {
                                 timers[2].stop();
                             }
                        }
                    });
                    movePlayer(player2, Game.NORTH, labels2, maze2);     
                    timers[2].start();
                }
            }
        };
        Action upKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[2] != null) {
                    timers[2].stop();
                    timers[2] = null;
                }
            }
        };
        Action downKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[3] == null) {      
                    timers[3] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player2, Game.SOUTH, labels2, maze2);
                             if (gameFinished) {
                                 timers[3].stop();
                             }
                        }
                    });
                    movePlayer(player2, Game.SOUTH, labels2, maze2); 
                    timers[3].start();
                }
            }
        };
        Action downKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[3] != null) {
                    timers[3].stop();
                    timers[3] = null;
                }
            }
        };
        
        // 1st player.
        Action aKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[4] == null) {      
                    timers[4] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player1, Game.WEST, labels1, maze1); 
                             if (gameFinished) {
                                 timers[4].stop();
                             }
                        }
                    });
                    movePlayer(player1, Game.WEST, labels1, maze1);
                    timers[4].start();
                }   
            }
        };
        Action aKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[4] != null) {
                    timers[4].stop();
                    timers[4] = null;
                }
            }
        };
        Action dKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[5] == null) {      
                    timers[5] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player1, Game.EAST, labels1, maze1); 
                             if (gameFinished) {
                                 timers[5].stop();
                             }
                        }
                    });
                    movePlayer(player1, Game.EAST, labels1, maze1);   
                    timers[5].start();
                }
            }
        };
        Action dKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[5] != null) {
                    timers[5].stop();
                    timers[5] = null;
                }
            }
        };
        Action wKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[6] == null) {      
                    timers[6] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player1, Game.NORTH, labels1, maze1);
                             if (gameFinished) {
                                 timers[6].stop();
                             }
                        }
                    });
                    movePlayer(player1, Game.NORTH, labels1, maze1);  
                    timers[6].start();
                }
            }
        };
        Action wKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers != null && timers[6] != null) {
                    timers[6].stop();
                    timers[6] = null;
                }
            }
        };
        Action sKeyPressed = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[7] == null) {      
                    timers[7] = new Timer(Game.MOVING_TIME, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             movePlayer(player1, Game.SOUTH, labels1, maze1);
                             if (gameFinished) {
                                 timers[7].stop();
                             }
                        }
                    });
                    movePlayer(player1, Game.SOUTH, labels1, maze1);    
                    timers[7].start();
                }
            }
        };
        Action sKeyReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (timers[7] != null) {
                    timers[7].stop();
                    timers[7] = null;
                }
            }
        };
        
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
     * Dispose the frame, stop and dispose the timers, and show which player wins on game end.
     */
    public void gameEndWin(String playerName) {
        gameFinished = true;
        freeze();
        disposeFrame();
        // CREATE FRAME FOR WINNING PLAYER IN MULTI GAME HERE
        System.out.println(playerName + " wins!");
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
