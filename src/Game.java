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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game {
	private JFrame mainFrame;
	private JButton newGameButton;
	private JButton quitButton;
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
    public static final Image PLAYER_0_IMAGE;
    public static final Image PLAYER_1_IMAGE;
    public static final Image PLAYER_2_IMAGE;
    public static final Image PLAYER_1_TEXT_IMAGE;
    public static final Image PLAYER_2_TEXT_IMAGE;
	
	
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
    
    // Frame size
    public static final int FRAME_SIZE = 600;
    
    // Background For MazeGame
    public static final ImageIcon BACKGROUND;
    

    /**
     * Load the images from the files.
     */
    static {
        File wallFile = new File("wall.jpg");
        File roadFile = new File("road.jpg");
        File hintFile = new File("hint.jpg");
        File coinFile = new File("coin.jpg"); 
        File player0FrontFile = new File("Mario_Front.jpg");
        File player0BackFile = new File("Mario_Back.jpg");
        File player0LeftFile = new File("Mario_Left.jpg");
        File player0RightFile = new File("Mario_Right.jpg"); 
        File player1FrontFile = new File("Peach_Front.jpg");
        File player1BackFile = new File("Peach_Back.jpg");
        File player1LeftFile = new File("Peach_Left.jpg");
        File player1RightFile = new File("Peach_Right.jpg"); 
        File player2FrontFile = new File("Yoshi_Front.jpg");
        File player2BackFile = new File("Yoshi_Back.jpg");
        File player2LeftFile = new File("Yoshi_Left.jpg");
        File player2RightFile = new File("Yoshi_Right.jpg"); 
        File player0File = new File("Mario.jpg");
        File player1File = new File("Peach.jpg");
        File player2File = new File("Yoshi.jpg");
        File backgroundFile = new File("Background.jpg");
        File player1TextFile = new File("Player_1_Text.jpg");
        File player2TextFile = new File("Player_2_Text.jpg");
        
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
        Image player0Img = null;
        Image player1Img = null;
        Image player2Img = null;
        Image backgroundImg = null;
        Image player1TextImg = null;
        Image player2TextImg = null;
         
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
            player0Img = ImageIO.read(player0File);
            player1Img = ImageIO.read(player1File);
            player2Img = ImageIO.read(player2File);
            backgroundImg = ImageIO.read(backgroundFile);
            player1TextImg = ImageIO.read(player1TextFile);
            player2TextImg = ImageIO.read(player2TextFile);
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
            PLAYER_0_IMAGE = player0Img;
            PLAYER_1_IMAGE = player1Img;
            PLAYER_2_IMAGE = player2Img;
            BACKGROUND = new ImageIcon(backgroundImg.getScaledInstance(FRAME_SIZE, FRAME_SIZE, Image.SCALE_SMOOTH));
            PLAYER_1_TEXT_IMAGE = player1TextImg;
            PLAYER_2_TEXT_IMAGE = player2TextImg;
        }   
    }
    
	public Game(){
	    //Making the Objects which go on the screen
		mainFrame = new JFrame("Game Menu");
		
		newGameButton = new StyledButton("New Game");
		quitButton = new QuitButton(mainFrame);
		leaderboard = new StyledButton("Leaderboard");
		instructions = new StyledButton("Instructions");
		
		//Setting the size of the Frame
		Dimension mainFrameDimension = new Dimension(FRAME_SIZE, FRAME_SIZE);
		mainFrame.setSize(mainFrameDimension);
		mainFrame.setResizable(false);
		
		mainPanel = new MainPanel(mainFrameDimension);		
		//Setting the layout of the Frame
		mainFrame.setLayout(null);
		mainPanel.setLayout(null);
		
		//Setting the dimensions of the newGameButton and setting it to visible
		newGameButton.setLocation(xButtonPosition, yButtonPosition);
		newGameButton.setSize(150, 90);
		newGameButton.setVisible(true);
		
		quitButton.setLocation(new Point(xButtonPosition, yButtonPosition + (2 * verticalGap)));
		quitButton.setSize(150, 90);
		quitButton.setVisible(true);
		
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
		mainPanel.add(quitButton);
		mainPanel.add(instructions);
		
		mainFrame.add(mainPanel);
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the New Game Button");
                //DifficultySelection dsFrame = new DifficultySelection(mainFrame);
                SelectGameMode gameModeScreen = new SelectGameMode(new Dimension(Game.FRAME_SIZE,Game.FRAME_SIZE), mainFrame, mainPanel);
                mainFrame.setVisible(true);
                mainPanel.setVisible(false);
                mainFrame.add(gameModeScreen);
               
                //dsFrame.run();
            }
		});
		
		
		leaderboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<ArrayList<LeaderBoardEntry>> leaderBoards = new ArrayList<ArrayList<LeaderBoardEntry>>();
				ScoreManager manager = new ScoreManager();
				leaderBoards.add(manager.getScores(ADVENTURE_MODE, EASY));
				leaderBoards.add(manager.getScores(COIN_MODE, EASY));
				leaderBoards.add(manager.getScores(ADVENTURE_MODE, MEDIUM));
				leaderBoards.add(manager.getScores(COIN_MODE, MEDIUM));
				leaderBoards.add(manager.getScores(ADVENTURE_MODE, HARD));
				leaderBoards.add(manager.getScores(COIN_MODE, HARD));
				
				LeaderBoard leaderBoard = new LeaderBoard(leaderBoards, mainPanel, mainFrame);
				mainPanel.setVisible(false);
				
				mainFrame.add(leaderBoard);
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
