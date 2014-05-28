import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* A panel to display the current leader board
* @author Bronte Kalebic
*
*/
public class LeaderBoard extends JPanel{
    private static final long serialVersionUID = 1L;
    
    //constants for iterating through array list of array lists
    private static final int EASY_ADVENTURE_LOC = 0;
    private static final int EASY_COIN_LOC = 1;
    private static final int MEDIUM_ADVENTURE_LOC = 2;
    private static final int MEDIUM_COIN_LOC = 3;
    private static final int HARD_ADVENTURE_LOC = 4;
    private static final int HARD_COIN_LOC = 5;
    
    //constants for GUI positions
    private static final int LEADER_BOARD_X_COORDINATE = 110;
    private static final int LEADER_BOARD_Y_COORDINATE = 70;
    private static final int X_INCREMENT = 200;
    private static final int Y_INCREMENT = 140;
    private static final int SCORE_INCREMENT = 100;
    private static final int HEADING_DECREMENT = 5;
    private static final Dimension LEADER_BOARD_TITLE_SIZE = new Dimension(150, 40);
    private static final Dimension LEADER_BOARD_SIZE = new Dimension(100, 100);
    
    private BackButton back;
    
    
    //score displaying JLabels
    private JLabel headingLabel;
    private JLabel easyAdventureTitle;
    private JLabel easyAdventureNames;
    private JLabel easyAdventureScores;
    private JLabel easyCoinTitle;
    private JLabel easyCoinNames;
    private JLabel easyCoinScores;
    private JLabel mediumAdventureTitle;
    private JLabel mediumAdventureNames;
    private JLabel mediumAdventureScores;
    private JLabel mediumCoinTitle;
    private JLabel mediumCoinNames;
    private JLabel mediumCoinScores;
    private JLabel hardAdventureTitle;
    private JLabel hardAdventureNames;
    private JLabel hardAdventureScores;
    private JLabel hardCoinTitle;
    private JLabel hardCoinNames;
    private JLabel hardCoinScores;
    private JLabel panelLabel;
    
    /**
    * Sets up all of the leader boards and displays them.
    * @param leaders is an array list of leader boards
    * @param prev is the previous window
    * @param mainFrame is the main window
    */
    public LeaderBoard(ArrayList<ArrayList<LeaderBoardEntry>> leaders, final JPanel prev, final JFrame mainFrame){
        // Load images
        Image leaderBoardHeadingImg = null;
        Image easyAdventureHeadingImg = null;
        Image easyCoinHeadingImg = null;
        Image mediumAdventureHeadingImg = null;
        Image mediumCoinHeadingImg = null;
        Image hardAdventureHeadingImg = null;
        Image hardCoinHeadingImg = null;
        File leaderBoardHeadingFile = new File("images/leaderBoardHeading.png");
        File easyAdventureHeadingFile = new File("images/easyAdventureHeading.png");
        File easyCoinHeadingFile = new File("images/easyCoinHeading.png");
        File mediumAdventureHeadingFile = new File("images/mediumAdventureHeading.png");
        File mediumCoinHeadingFile = new File("images/mediumCoinHeading.png");
        File hardAdventureHeadingFile = new File("images/hardAdventureHeading.png");
        File hardCoinHeadingFile = new File("images/hardCoinHeading.png");
        try {
            leaderBoardHeadingImg = ImageIO.read(leaderBoardHeadingFile);
            easyAdventureHeadingImg = ImageIO.read(easyAdventureHeadingFile);
            easyCoinHeadingImg = ImageIO.read(easyCoinHeadingFile);
            mediumAdventureHeadingImg = ImageIO.read(mediumAdventureHeadingFile);
            mediumCoinHeadingImg = ImageIO.read(mediumCoinHeadingFile);
            hardAdventureHeadingImg = ImageIO.read(hardAdventureHeadingFile);
            hardCoinHeadingImg = ImageIO.read(hardCoinHeadingFile);  
        } catch (IOException e) {}
        
        //images
        ImageIcon headingIcon;
        ImageIcon easyAdventureIcon;
        ImageIcon easyCoinIcon;
        ImageIcon mediumAdventureIcon;
        ImageIcon mediumCoinIcon;
        ImageIcon hardAdventureIcon;
        ImageIcon hardCoinIcon;  
        
        //set current panel
        this.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        this.setLayout(null);   
        
        panelLabel = new JLabel();
        panelLabel.setSize(new Dimension(Game.FRAME_SIZE, Game.FRAME_SIZE));
        panelLabel.setLayout(null);
        panelLabel.setIcon(Game.BACKGROUND);
                
        //set back button
        back = new BackButton(prev, mainFrame, this);
        back.setVisible(true);
        
        //set heading
        headingIcon = new ImageIcon(leaderBoardHeadingImg);
        headingLabel = new JLabel();
        headingLabel.setIcon(headingIcon);
        headingLabel.setSize(new Dimension(377, 19));
        headingLabel.setLocation(new Point(110, 20));
        headingLabel.setVisible(true);
                
        //set leader board
        //Make an image scaledEasyAdventure = Game..... and set size
        //Use the ready made icon and assign the image to the icon
        //Use a JLabel and set the icon to the JLabel
        
        //easy adventure
        Image scaledEasyAdventure = easyAdventureHeadingImg.getScaledInstance(135, 7, Image.SCALE_SMOOTH);
        easyAdventureIcon = new ImageIcon(scaledEasyAdventure);
        easyAdventureTitle = new JLabel();
        easyAdventureTitle.setIcon(easyAdventureIcon);
        easyAdventureTitle.setSize(135, 7);
        easyAdventureTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE-HEADING_DECREMENT));
        easyAdventureTitle.setVisible(true);
        
        easyAdventureNames = new JLabel(displayLeaderBoardNames(leaders.get(EASY_ADVENTURE_LOC)));
        easyAdventureNames.setSize(LEADER_BOARD_SIZE);
        easyAdventureNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE));
        easyAdventureNames.setVisible(true);   
        easyAdventureScores = new JLabel(displayLeaderBoardScores(leaders.get(EASY_ADVENTURE_LOC)));
        easyAdventureScores.setSize(LEADER_BOARD_SIZE);
        easyAdventureScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE));
        easyAdventureScores.setVisible(true);
      
        //easy coin
        Image scaledCoinEasy = easyCoinHeadingImg.getScaledInstance(97, 7, Image.SCALE_SMOOTH);
        easyCoinIcon = new ImageIcon(scaledCoinEasy);
        easyCoinTitle = new JLabel();
        easyCoinTitle.setIcon(easyCoinIcon);
        easyCoinTitle.setSize(97, 7);
        easyCoinTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT, LEADER_BOARD_Y_COORDINATE-HEADING_DECREMENT));
        easyCoinTitle.setVisible(true);
        
        easyCoinNames = new JLabel(displayLeaderBoardNames(leaders.get(EASY_COIN_LOC)));
        easyCoinNames.setSize(LEADER_BOARD_SIZE);
        easyCoinNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT, LEADER_BOARD_Y_COORDINATE));
        easyCoinNames.setVisible(true); 
        easyCoinScores = new JLabel(displayLeaderBoardScores(leaders.get(EASY_COIN_LOC)));
        easyCoinScores.setSize(LEADER_BOARD_SIZE);
        easyCoinScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE));
        easyCoinScores.setVisible(true);
        
        
        //medium adventure
        Image scaledAdventureMedium = mediumAdventureHeadingImg.getScaledInstance(150, 7 , Image.SCALE_SMOOTH);
        mediumAdventureIcon = new ImageIcon(scaledAdventureMedium);
        mediumAdventureTitle = new JLabel(mediumAdventureIcon);
        mediumAdventureTitle.setSize(150, 7);
        mediumAdventureTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT-HEADING_DECREMENT));
        mediumAdventureTitle.setVisible(true);
        
        mediumAdventureNames = new JLabel(displayLeaderBoardNames(leaders.get(MEDIUM_ADVENTURE_LOC)));
        mediumAdventureNames.setSize(LEADER_BOARD_SIZE);
        mediumAdventureNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT));
        mediumAdventureNames.setVisible(true); 
        
        mediumAdventureScores = new JLabel(displayLeaderBoardScores(leaders.get(MEDIUM_ADVENTURE_LOC)));
        mediumAdventureScores.setSize(LEADER_BOARD_SIZE);
        mediumAdventureScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT));
        mediumAdventureScores.setVisible(true);
       
        
        
        //medium coin
        Image scaledCoinMedium = mediumCoinHeadingImg.getScaledInstance(112, 7, Image.SCALE_SMOOTH);
        mediumCoinIcon = new ImageIcon(scaledCoinMedium);
        mediumCoinTitle = new JLabel(mediumCoinIcon);
        mediumCoinTitle.setSize(112, 7);
        mediumCoinTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT-HEADING_DECREMENT));
        mediumCoinTitle.setVisible(true);
        mediumCoinNames = new JLabel(displayLeaderBoardNames(leaders.get(MEDIUM_COIN_LOC)));
        mediumCoinNames.setSize(LEADER_BOARD_SIZE);
        mediumCoinNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT));
        mediumCoinNames.setVisible(true);   
        mediumCoinScores = new JLabel(displayLeaderBoardScores(leaders.get(MEDIUM_COIN_LOC)));
        mediumCoinScores.setSize(LEADER_BOARD_SIZE);
        mediumCoinScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT));
        mediumCoinScores.setVisible(true);
       
        
        
        //hard adventure
        Image scaledAdventureHard = hardAdventureHeadingImg.getScaledInstance(135, 7, Image.SCALE_SMOOTH);
        hardAdventureIcon = new ImageIcon(scaledAdventureHard);
        hardAdventureTitle = new JLabel(hardAdventureIcon);
        hardAdventureTitle.setSize(135, 7);
        hardAdventureTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)-HEADING_DECREMENT));
        hardAdventureTitle.setVisible(true);
        hardAdventureNames = new JLabel(displayLeaderBoardNames(leaders.get(HARD_ADVENTURE_LOC)));
        hardAdventureNames.setSize(LEADER_BOARD_SIZE);
        hardAdventureNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)));
        hardAdventureNames.setVisible(true);   
        hardAdventureScores = new JLabel(displayLeaderBoardScores(leaders.get(HARD_ADVENTURE_LOC)));
        hardAdventureScores.setSize(LEADER_BOARD_SIZE);
        hardAdventureScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)));
        hardAdventureScores.setVisible(true);
        
        
        //hard coin
        Image scaledCoinHard = hardCoinHeadingImg.getScaledInstance(97, 7, Image.SCALE_SMOOTH);
        hardCoinIcon= new ImageIcon(scaledCoinHard);
        hardCoinTitle = new JLabel(hardCoinIcon);
        hardCoinTitle.setSize(97, 7);
        hardCoinTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)-HEADING_DECREMENT));
        hardCoinTitle.setVisible(true);
        hardCoinNames = new JLabel(displayLeaderBoardNames(leaders.get(HARD_COIN_LOC)));
        hardCoinNames.setSize(LEADER_BOARD_SIZE);
        hardCoinNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)));
        hardCoinNames.setVisible(true); 
        hardCoinScores = new JLabel(displayLeaderBoardScores(leaders.get(HARD_COIN_LOC)));
        hardCoinScores.setSize(LEADER_BOARD_SIZE);
        hardCoinScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+X_INCREMENT+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)));
        hardCoinScores.setVisible(true);
        
        //generate frame
        panelLabel.add(back);
        panelLabel.add(headingLabel);
        panelLabel.add(easyAdventureTitle);
        panelLabel.add(easyAdventureNames);
        panelLabel.add(easyAdventureScores);
        panelLabel.add(easyCoinTitle);
        panelLabel.add(easyCoinNames);
        panelLabel.add(easyCoinScores);
        panelLabel.add(mediumAdventureTitle);
        panelLabel.add(mediumAdventureNames);
        panelLabel.add(mediumAdventureScores);
        panelLabel.add(mediumCoinTitle);
        panelLabel.add(mediumCoinNames);
        panelLabel.add(mediumCoinScores);
        panelLabel.add(hardAdventureTitle);
        panelLabel.add(hardAdventureNames);
        panelLabel.add(hardAdventureScores);
        panelLabel.add(hardCoinTitle);
        panelLabel.add(hardCoinNames);
        panelLabel.add(hardCoinScores);
        
        this.add(panelLabel);
        
    }
        
    /**
    * This method finds all of the names in the entries in the leader
    * board and converts them into a string to be displayed
    * @param entries is an array list of entries to be displayed
    * @return a string of all of the names
    */
    private String displayLeaderBoardNames(ArrayList<LeaderBoardEntry> entries) {
        int i = 0;
        int numEntries = entries.size();
        LeaderBoardEntry LBE;
        String returnVal = "<html>";
        while (i < numEntries) {
            LBE = entries.get(i);
            returnVal += LBE.getScoreName() + "<br>";
            i++;
        }
        returnVal += "</html>";
        
        return returnVal;
    }
    
    /**
    * This method finds all of the scores in the entries in the leader
    * board and converts them into a string to be displayed
    * @param entries is an array list of entries to be displayed
    * @return a string of all of the scores
    */
    private String displayLeaderBoardScores(ArrayList<LeaderBoardEntry> entries) {
        int i = 0;
        int numEntries = entries.size();
        LeaderBoardEntry LBE;
        String returnVal = "<html>";
        while (i < numEntries) {
            LBE = entries.get(i);
            returnVal += LBE.getScoreNum() + "<br>";
            i++;
        }
        returnVal += "</html>";
        
        return returnVal;
    }
}