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
    private static final int EASY_NORMAL_LOC = 0;
    private static final int EASY_COIN_LOC = 1;
    private static final int MEDIUM_NORMAL_LOC = 2;
    private static final int MEDIUM_COIN_LOC = 3;
    private static final int HARD_NORMAL_LOC = 4;
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
    private JLabel easyNormalTitle;
    private JLabel easyNormalNames;
    private JLabel easyNormalScores;
    private JLabel easyCoinTitle;
    private JLabel easyCoinNames;
    private JLabel easyCoinScores;
    private JLabel mediumNormalTitle;
    private JLabel mediumNormalNames;
    private JLabel mediumNormalScores;
    private JLabel mediumCoinTitle;
    private JLabel mediumCoinNames;
    private JLabel mediumCoinScores;
    private JLabel hardNormalTitle;
    private JLabel hardNormalNames;
    private JLabel hardNormalScores;
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
        File leaderBoardHeadingFile = new File("leaderBoardHeading.png");
        File easyAdventureHeadingFile = new File("easyAdventureHeading.png");
        File easyCoinHeadingFile = new File("easyCoinHeading.png");
        File mediumAdventureHeadingFile = new File("mediumAdventureHeading.png");
        File mediumCoinHeadingFile = new File("mediumCoinHeading.png");
        File hardAdventureHeadingFile = new File ("hardAdventureHeading");
        File hardCoinHeadingFile = new File("hardCoinHeading.png");
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
        ImageIcon easyNormalIcon;
        ImageIcon easyCoinIcon;
        ImageIcon mediumNormalIcon;
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
        //easy normal
        Image scaledEasyNormal = easyAdventureHeadingImg.getScaledInstance(135, 7, Image.SCALE_SMOOTH);
        easyNormalIcon = new ImageIcon(scaledEasyNormal);
        easyNormalTitle = new JLabel();
        easyNormalTitle.setIcon(easyNormalIcon);
        easyNormalTitle.setSize(269, 13);
        easyNormalTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE-HEADING_DECREMENT));
        easyNormalTitle.setVisible(true);
        easyNormalNames = new JLabel(displayLeaderBoardNames(leaders.get(EASY_NORMAL_LOC)));
        easyNormalNames.setSize(LEADER_BOARD_SIZE);
        easyNormalNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE));
        easyNormalNames.setVisible(true);   
        easyNormalScores = new JLabel(displayLeaderBoardScores(leaders.get(EASY_NORMAL_LOC)));
        easyNormalScores.setSize(LEADER_BOARD_SIZE);
        easyNormalScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE));
        easyNormalScores.setVisible(true);
        //easy coin
        Image scaledCoinNormal = easyCoinHeadingImg.getScaledInstance(97, 7, Image.SCALE_SMOOTH);
        easyCoinIcon = new ImageIcon(scaledCoinNormal);
        easyCoinTitle = new JLabel();
        easyCoinTitle.setIcon(easyNormalIcon);
        easyCoinTitle.setSize(269, 13);
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
        //medium normal
        mediumNormalTitle = new JLabel("Medium Adventure Mode");
        mediumNormalTitle.setSize(LEADER_BOARD_TITLE_SIZE);
        mediumNormalTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT-HEADING_DECREMENT));
        mediumNormalTitle.setVisible(true);
        mediumNormalNames = new JLabel(displayLeaderBoardNames(leaders.get(MEDIUM_NORMAL_LOC)));
        mediumNormalNames.setSize(LEADER_BOARD_SIZE);
        mediumNormalNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT));
        mediumNormalNames.setVisible(true); 
        mediumNormalScores = new JLabel(displayLeaderBoardScores(leaders.get(MEDIUM_NORMAL_LOC)));
        mediumNormalScores.setSize(LEADER_BOARD_SIZE);
        mediumNormalScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE+Y_INCREMENT));
        mediumNormalScores.setVisible(true);
        //medium coin
        mediumCoinTitle = new JLabel("Medium Coin Mode");
        mediumCoinTitle.setSize(LEADER_BOARD_TITLE_SIZE);
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
        //hard normal
        hardNormalTitle = new JLabel("Hard Adventure Mode");
        hardNormalTitle.setSize(LEADER_BOARD_TITLE_SIZE);
        hardNormalTitle.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)-HEADING_DECREMENT));
        hardNormalTitle.setVisible(true);
        hardNormalNames = new JLabel(displayLeaderBoardNames(leaders.get(HARD_NORMAL_LOC)));
        hardNormalNames.setSize(LEADER_BOARD_SIZE);
        hardNormalNames.setLocation(new Point(LEADER_BOARD_X_COORDINATE, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)));
        hardNormalNames.setVisible(true);   
        hardNormalScores = new JLabel(displayLeaderBoardScores(leaders.get(HARD_NORMAL_LOC)));
        hardNormalScores.setSize(LEADER_BOARD_SIZE);
        hardNormalScores.setLocation(new Point(LEADER_BOARD_X_COORDINATE+SCORE_INCREMENT, LEADER_BOARD_Y_COORDINATE+(2*Y_INCREMENT)));
        hardNormalScores.setVisible(true);
        //hard coin
        hardCoinTitle = new JLabel("Hard Coin Mode");
        hardCoinTitle.setSize(LEADER_BOARD_TITLE_SIZE);
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
        panelLabel.add(easyNormalTitle);
        panelLabel.add(easyNormalNames);
        panelLabel.add(easyNormalScores);
        panelLabel.add(easyCoinTitle);
        panelLabel.add(easyCoinNames);
        panelLabel.add(easyCoinScores);
        panelLabel.add(mediumNormalTitle);
        panelLabel.add(mediumNormalNames);
        panelLabel.add(mediumNormalScores);
        panelLabel.add(mediumCoinTitle);
        panelLabel.add(mediumCoinNames);
        panelLabel.add(mediumCoinScores);
        panelLabel.add(hardNormalTitle);
        panelLabel.add(hardNormalNames);
        panelLabel.add(hardNormalScores);
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