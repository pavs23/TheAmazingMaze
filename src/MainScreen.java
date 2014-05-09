import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainScreen {

	private JButton newGameButton;
	public JFrame mainFrame;
	private JButton cancelButton;
	private JPanel mainPanel;
	
	public MainScreen(){
		
		//Making the Objects which go on the screen
		mainFrame = new JFrame("Game Menu");
		newGameButton = new JButton("New Game");
		cancelButton = new JButton("Quit");
		
		
		
		//Setting the size of the Frame
		Dimension mainFrameDimension = new Dimension(500, 500);
		mainFrame.setSize(mainFrameDimension);
		mainPanel = new MainPanel(mainFrameDimension);		
		//Setting the layout of the Frame
		mainFrame.setLayout(null);
		mainPanel.setLayout(null);
		
		//Setting the dimensions of the newGameButton and setting it to visible
		Insets insets = mainPanel.getInsets();
		newGameButton.setBounds(insets.left + 175, insets.top + 100, 150, 90);
		newGameButton.setVisible(true);
		cancelButton.setBounds(insets.left + 175, insets.top + 250, 150, 90);
		cancelButton.setVisible(true);
		
		//Adding the newGameButton to the frame
		mainPanel.add(newGameButton);
		mainPanel.add(cancelButton);
		
		mainFrame.add(mainPanel);
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the New Game Button");
                DifficultySelection dsFrame = new DifficultySelection(mainFrame);
                
                mainFrame.setVisible(true);
                mainPanel.setVisible(false);
                mainFrame.add(dsFrame);
               
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
		
	}
	
	public void run(){
		
		//newGameButton.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
