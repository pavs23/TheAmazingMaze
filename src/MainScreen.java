import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainScreen {
	private JFrame mainFrame;
	private StyledButton newGameButton;
	private StyledButton cancelButton;
	private MainPanel mainPanel;
	private StyledButton leaderboard;
	
	public MainScreen(){
		
		
		//Making the Objects which go on the screen
		mainFrame = new JFrame("The Amazing Maze");
		newGameButton = new StyledButton();
		cancelButton = new StyledButton();
		leaderboard = new StyledButton();
		
		newGameButton.setText("New Game");
		cancelButton.setText("Quit");
		leaderboard.setText("Leaderboard");

		//Setting the size of the Frame
		mainFrame.setSize(new Dimension(600, 600));
		mainPanel = new MainPanel(new Dimension(600, 600));		
		//Setting the layout of the Frame
		mainFrame.setLayout(null);
		mainPanel.setLayout(null);
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		mainFrame.setVisible(true);
		
		//Setting the dimensions of the newGameButton and setting it to visible
		newGameButton.setLocation(375,100);
		newGameButton.setSize(150, 90);
		newGameButton.setVisible(true);
		
		cancelButton.setLocation(500, 320);
		cancelButton.setSize(150, 90);
		cancelButton.setVisible(true);
		
		leaderboard.setLocation(new Point(375,210));
		leaderboard.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
		leaderboard.setSize(new Dimension(150, 90));
		leaderboard.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		mainFrame.setSize(xSize,ySize);  
		 
		
		//Adding the newGameButton to the frame
		mainPanel.add(newGameButton);
		mainPanel.add(leaderboard);
		mainPanel.add(cancelButton);
		
		mainFrame.add(mainPanel);
		mainFrame.setResizable(false);
		
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
				ArrayList<ArrayList<LeaderBoardEntry>> test = new ArrayList<ArrayList<LeaderBoardEntry>>();
				test.add(leaders);
				LeaderBoardEntry a = new LeaderBoardEntry("Pavan", 1000);
				LeaderBoardEntry b = new LeaderBoardEntry("Jo", 800);
				LeaderBoardEntry t = new LeaderBoardEntry("Tim", 100);
				leaders.add(a);
				leaders.add(b);
				leaders.add(t);
				
				LeaderBoard leaderBoard = new LeaderBoard(test, mainPanel, mainFrame);
				mainPanel.setVisible(false);
				
				leaderboard.setVisible(true);
				mainFrame.remove(mainPanel);
				
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
