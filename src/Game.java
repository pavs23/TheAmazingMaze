import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game {
	private JFrame mainFrame;
	private JButton newGameButton;
	private JButton cancelButton;
	private JPanel mainPanel;
	private JButton leaderboard;
	private JButton instructions;
	
	// Positions of the buttons on the panel
	private static final int xButtonPosition = 225;
	private static final int yButtonPosition = 100;
	private static final int verticalGap = 110;
	
	// The Images used in the game.
	public static final Image ROAD_IMAGE;
	public static final Image WALL_IMAGE;
	public static final Image HINT_IMAGE;
	public static final Image COIN_IMAGE;
	public static final Image PLAYER_0_FRONT_IMAGE;
	public static final Image PLAYER_0_BACK_IMAGE;
	public static final Image PLAYER_0_LEFT_IMAGE;
	public static final Image PLAYER_0_RIGHT_IMAGE;
	public static final Image PLAYER_1_FRONT_IMAGE;
    public static final Image PLAYER_1_BACK_IMAGE;
    public static final Image PLAYER_1_LEFT_IMAGE;
    public static final Image PLAYER_1_RIGHT_IMAGE;
    public static final Image PLAYER_2_FRONT_IMAGE;
    public static final Image PLAYER_2_BACK_IMAGE;
    public static final Image PLAYER_2_LEFT_IMAGE;
    public static final Image PLAYER_2_RIGHT_IMAGE;
	
	
	// Constants in the game.
    public static final int SINGLE_PLAYER = 0;
    public static final int MULTI_PLAYER = 1;
    public static final int ADVENTURE_MODE = 0;
    public static final int COIN_MODE = 1;
    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;
    public static final int ROAD = 1;
    public static final int WALL = 0;
    public static final int SCORE_MULTIPLIER = 10;
    public static final int MOVING_TIME = 120;
    
    // The player characters.
    public static final int PLAYER_0 = 0;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;

    // Directions in the game.
    public static final Direction NORTH = new Direction(0, -1);
    public static final Direction SOUTH = new Direction(0, 1);
    public static final Direction WEST = new Direction(-1, 0);
    public static final Direction EAST = new Direction(1, 0);
	
    // Difficulties
    public static final int EASY_X = 10;
    public static final int EASY_Y = 10;
    public static final int MEDIUM_X = 20;
    public static final int MEDIUM_Y = 20;   
    public static final int HARD_X = 30;
    public static final int HARD_Y = 30;

    /**
     * Load the images from the files.
     */
    static {
        File wallFile = new File("wall2.jpg");
        File roadFile = new File("road2.jpg");
        File hintFile = new File("hint2.jpg");
        File coinFile = new File("coin2.jpg"); 
        File player0FrontFile = new File("boyFront.png");
        File player0BackFile = new File("boyBack.png");
        File player0LeftFile = new File("boyLeft.png");
        File player0RightFile = new File("boyRight.png"); 
        File player1FrontFile = new File("girlFront.png");
        File player1BackFile = new File("girlBack.png");
        File player1LeftFile = new File("girlLeft.png");
        File player1RightFile = new File("girlRight.png"); 
        File player2FrontFile = new File("pikachuFront.png");
        File player2BackFile = new File("pikachuBack.png");
        File player2LeftFile = new File("pikachuLeft.png");
        File player2RightFile = new File("pikachuRight.png"); 
        
        Image wallImg = null;
        Image roadImg = null;
        Image hintImg = null;
        Image coinImg = null;
        Image player0FrontImg = null;
        Image player0BackImg = null;
        Image player0LeftImg = null;
        Image player0RightImg = null;
        Image player1FrontImg = null;
        Image player1BackImg = null;
        Image player1LeftImg = null;
        Image player1RightImg = null;
        Image player2FrontImg = null;
        Image player2BackImg = null;
        Image player2LeftImg = null;
        Image player2RightImg = null;
        
        try {
            wallImg = ImageIO.read(wallFile);
            roadImg = ImageIO.read(roadFile);
            hintImg = ImageIO.read(hintFile);
            coinImg = ImageIO.read(coinFile);
            player0FrontImg = ImageIO.read(player0FrontFile);
            player0BackImg = ImageIO.read(player0BackFile);
            player0LeftImg = ImageIO.read(player0LeftFile);
            player0RightImg = ImageIO.read(player0RightFile);
            player1FrontImg = ImageIO.read(player1FrontFile);
            player1BackImg = ImageIO.read(player1BackFile);
            player1LeftImg = ImageIO.read(player1LeftFile);
            player1RightImg = ImageIO.read(player1RightFile);
            player2FrontImg = ImageIO.read(player2FrontFile);
            player2BackImg = ImageIO.read(player2BackFile);
            player2LeftImg = ImageIO.read(player2LeftFile);
            player2RightImg = ImageIO.read(player2RightFile);
        } catch (IOException e) {
        } finally {
            WALL_IMAGE = wallImg;
            ROAD_IMAGE = roadImg;
            HINT_IMAGE = hintImg;
            COIN_IMAGE = coinImg;
            PLAYER_0_FRONT_IMAGE = player0FrontImg;
            PLAYER_0_BACK_IMAGE = player0BackImg;
            PLAYER_0_LEFT_IMAGE = player0LeftImg;
            PLAYER_0_RIGHT_IMAGE = player0RightImg;
            PLAYER_1_FRONT_IMAGE = player1FrontImg;
            PLAYER_1_BACK_IMAGE = player1BackImg;
            PLAYER_1_LEFT_IMAGE = player1LeftImg;
            PLAYER_1_RIGHT_IMAGE = player1RightImg;
            PLAYER_2_FRONT_IMAGE = player2FrontImg;
            PLAYER_2_BACK_IMAGE = player2BackImg;
            PLAYER_2_LEFT_IMAGE = player2LeftImg;
            PLAYER_2_RIGHT_IMAGE = player2RightImg;
        }   
    }
    
	public Game(){
	    //Making the Objects which go on the screen
		mainFrame = new JFrame("Game Menu");
		newGameButton = new JButton("New Game");
		cancelButton = new JButton("Quit");
		leaderboard = new JButton("Leaderboard");
		instructions = new JButton("Instructions");
		
		//newGameButton.setBorder(BorderFactory.createBevelBorder(0, null, null, Color.black, null));
		//cancelButton.setBorder(BorderFactory.createBevelBorder(0, null, null, Color.black, null));
		
		newGameButton.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
		cancelButton.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
		
		//Setting the size of the Frame
		Dimension mainFrameDimension = new Dimension(600, 600);
		mainFrame.setSize(mainFrameDimension);
		mainPanel = new MainPanel(mainFrameDimension);		
		//Setting the layout of the Frame
		mainFrame.setLayout(null);
		mainPanel.setLayout(null);
		
		//Setting the dimensions of the newGameButton and setting it to visible
		Insets insets = mainPanel.getInsets();
		newGameButton.setLocation(xButtonPosition, yButtonPosition);
		newGameButton.setSize(150, 90);
		newGameButton.setVisible(true);
		
		cancelButton.setLocation(new Point(xButtonPosition, yButtonPosition + (2 * verticalGap)));
		cancelButton.setSize(150, 90);
		cancelButton.setVisible(true);
		
		leaderboard.setLocation(new Point(xButtonPosition, yButtonPosition + verticalGap));
		leaderboard.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
		leaderboard.setSize(new Dimension(150, 90));
		leaderboard.setVisible(true);
		
		instructions.setLocation(175, 400);
		instructions.setSize(150, 90);
		instructions.setVisible(true);
		
		//Adding the newGameButton to the frame
		mainPanel.add(newGameButton);
		mainPanel.add(leaderboard);
		mainPanel.add(cancelButton);
		mainPanel.add(instructions);
		
		mainFrame.add(mainPanel);
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the New Game Button");
                //DifficultySelection dsFrame = new DifficultySelection(mainFrame);
                SelectGameMode gameModeScreen = new SelectGameMode(new Dimension(600,600), mainFrame, mainPanel);
                mainFrame.setVisible(true);
                mainPanel.setVisible(false);
                mainFrame.add(gameModeScreen);
               
                //dsFrame.run();
            }
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the Exit Button");
                mainFrame.dispose();
            }
		});
		
		
		leaderboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			    // READ THE LEADERBOARDS FOR THE MODES & DIFFICULTY HERE.
				ArrayList<LeaderBoardEntry> leaders = new ArrayList<LeaderBoardEntry>();
				LeaderBoardEntry a = new LeaderBoardEntry("Pavan", 1000);
				LeaderBoardEntry b = new LeaderBoardEntry("Jo", 800);
				LeaderBoardEntry t = new LeaderBoardEntry("Tim", 100);
				leaders.add(a);
				leaders.add(b);
				leaders.add(t);
				
				LeaderBoard leaderBoard = new LeaderBoard(leaders, mainPanel, mainFrame);
				mainPanel.setVisible(false);
				
				leaderboard.setVisible(true);
				mainFrame.remove(mainPanel);
				
			}
		});
		
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the Instructions Button");
                //DifficultySelection dsFrame = new DifficultySelection(mainFrame);
                InstructionPanel instructionScreen = new InstructionPanel(mainFrame, mainPanel);
                mainFrame.setVisible(true);
                mainPanel.setVisible(false);
                mainFrame.remove(mainPanel);
                mainFrame.add(instructionScreen);
                
               
                //dsFrame.run();
            }
		});
		
	}
	
		
	
	public void run(){	
		//newGameButton.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
