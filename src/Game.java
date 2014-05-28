import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class that creates the game.
 * @author floren & pavan & Bronte Kalebic
 *
 */
public class Game {
	private JFrame mainFrame;
	private StyledButton newGameButton;
	private StyledButton quitButton;
	private JPanel mainPanel;
	private StyledButton leaderboard;
	
	//image files
	private ImageIcon heading;
	private JLabel title;
	private ImageIcon yoshiIcon;
	private JLabel yoshiLabel;
	private ImageIcon marioIcon;
	private JLabel marioLabel;
	private ImageIcon peachIcon;
	private JLabel peachLabel;
	
	// Positions of the buttons on the panel
	private static final int X_BUTTON_POSITION = 225;
	private static final int Y_BUTTON_POSITION = 200;
	private static final int VERTICAL_GAP = 90;
	
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
    public static final Image PLAYER_0_IMAGE;
    public static final Image PLAYER_1_IMAGE;
    public static final Image PLAYER_2_IMAGE;
    public static final Image PLAYER_1_TEXT_IMAGE;
    public static final Image PLAYER_2_TEXT_IMAGE;
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
    public static final int MOVING_TIME = 110;
    
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
    public static final int MEDIUM_X = 14;
    public static final int MEDIUM_Y = 14;   
    public static final int HARD_X = 18;
    public static final int HARD_Y = 18;
    
    // Frame size
    public static final int FRAME_SIZE = 600;
    
    // Background For MazeGame
    public static final ImageIcon BACKGROUND;
    

    /**
     * Load the images from the files.
     */
    static {
        File wallFile = new File("images/wall.jpg");
        File roadFile = new File("images/road.jpg");
        File hintFile = new File("images/hint.jpg");
        File coinFile = new File("images/coin.jpg"); 
        File player0FrontFile = new File("images/Mario_Front.jpg");
        File player0BackFile = new File("images/Mario_Back.jpg");
        File player0LeftFile = new File("images/Mario_Left.jpg");
        File player0RightFile = new File("images/Mario_Right.jpg"); 
        File player1FrontFile = new File("images/Peach_Front.jpg");
        File player1BackFile = new File("images/Peach_Back.jpg");
        File player1LeftFile = new File("images/Peach_Left.jpg");
        File player1RightFile = new File("images/Peach_Right.jpg"); 
        File player2FrontFile = new File("images/Yoshi_Front.jpg");
        File player2BackFile = new File("images/Yoshi_Back.jpg");
        File player2LeftFile = new File("images/Yoshi_Left.jpg");
        File player2RightFile = new File("images/Yoshi_Right.jpg"); 
        File player0File = new File("images/Mario.jpg");
        File player1File = new File("images/Peach.jpg");
        File player2File = new File("images/Yoshi.jpg");
        File backgroundFile = new File("images/Background.jpg");
        File player1TextFile = new File("images/Player_1_Text.jpg");
        File player2TextFile = new File("images/Player_2_Text.jpg");

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
            PLAYER_1_TEXT_IMAGE = player1TextImg;
            PLAYER_2_TEXT_IMAGE = player2TextImg;
            BACKGROUND = new ImageIcon(backgroundImg.getScaledInstance(FRAME_SIZE, FRAME_SIZE, Image.SCALE_SMOOTH));
        }   
    }
    
    /**
     * Constructor of the game class that creates the game.
     */
	public Game(){
	    //Making the Objects which go on the screen
		mainFrame = new JFrame("Game Menu");
		
		newGameButton = new StyledButton("New Game");
		quitButton = new QuitButton(mainFrame);
		leaderboard = new StyledButton("Leaderboard");
		
		// Load images
	    Image superMarioMazeHeading = null;
	    Image yoshiTongueImage = null;
	    Image mario2Image = null;
	    Image peach2Image = null;
	    
	    File superMarioMazeHeadingFile = new File("images/superMarioMaze.jpg");
        File yoshiTongueFile = new File("images/yoshiTongue.jpg");
        File mario2File = new File("images/mario2.png");
        File peach2File = new File("images/peach2.jpg");
        
        try {
            superMarioMazeHeading = ImageIO.read(superMarioMazeHeadingFile);
            yoshiTongueImage = ImageIO.read(yoshiTongueFile);
            mario2Image = ImageIO.read(mario2File);
            peach2Image = ImageIO.read(peach2File);
        } catch (IOException e) {}
        
		//images
		Image scaledHeading = superMarioMazeHeading.getScaledInstance(394, 122, Image.SCALE_SMOOTH);
		Image scaledPeach = peach2Image.getScaledInstance(190, 240, Image.SCALE_SMOOTH);		
		heading = new ImageIcon(scaledHeading);
		title = new JLabel();
		yoshiIcon = new ImageIcon(yoshiTongueImage);
		yoshiLabel = new JLabel();
		marioIcon = new ImageIcon(mario2Image);
		marioLabel = new JLabel();
		peachIcon = new ImageIcon(scaledPeach);
		peachLabel = new JLabel();
		
		//Setting the size of the Frame
		Dimension mainFrameDimension = new Dimension(FRAME_SIZE, FRAME_SIZE);
		mainFrame.setSize(mainFrameDimension);
		mainFrame.setResizable(false);
		
		mainPanel = new JPanel();
		mainPanel.setSize(mainFrameDimension);
		//Setting the layout of the Frame
		mainFrame.setLayout(null);
		mainPanel.setLayout(null);
		
		//Setting the dimensions of the newGameButton and setting it to visible
		newGameButton.setLocation(X_BUTTON_POSITION, Y_BUTTON_POSITION);
		newGameButton.setVisible(true);
		
		quitButton.setLocation(new Point(X_BUTTON_POSITION, Y_BUTTON_POSITION + (2 * VERTICAL_GAP)));
		quitButton.setVisible(true);
		
		leaderboard.setLocation(new Point(X_BUTTON_POSITION, Y_BUTTON_POSITION + VERTICAL_GAP));
		leaderboard.setVisible(true);
		
		//images
		title.setIcon(heading);
		title.setLocation(new Point(100, 0));
		title.setSize(new Dimension(398, 146));
		title.setBackground(null);
		title.setVisible(true);
		
		yoshiLabel.setIcon(yoshiIcon);
		yoshiLabel.setLocation(new Point(20, 375));
		yoshiLabel.setSize(new Dimension(320, 180));
		yoshiLabel.setVisible(true);
		
		marioLabel.setIcon(marioIcon);
		marioLabel.setLocation(new Point(380, 150));
		marioLabel.setSize(new Dimension(192, 292));
		marioLabel.setVisible(true);
		
		peachLabel.setIcon(peachIcon);
		peachLabel.setLocation(new Point(20, 140));
		peachLabel.setSize(new Dimension(190, 240));
		peachLabel.setVisible(true);
		
		//Adding the newGameButton to the frame
		mainPanel.add(newGameButton);
		mainPanel.add(leaderboard);
		mainPanel.add(quitButton);
		mainPanel.add(title);
		mainPanel.add(yoshiLabel);
		mainPanel.add(peachLabel);
		mainPanel.add(marioLabel);
		mainPanel.setBackground(Color.WHITE);
		
		mainFrame.add(mainPanel);
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                SelectGameMode gameModeScreen = new SelectGameMode(mainFrame, mainPanel);
                mainFrame.setVisible(true);
                mainPanel.setVisible(false);
                mainFrame.add(gameModeScreen);
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
	}
	
	/**	
	 * Method to start showing the game to the players.
	 */
	public void run() {	
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
