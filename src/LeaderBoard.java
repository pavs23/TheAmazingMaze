import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LeaderBoard extends JPanel{
	
	private JLabel title;
	private BackButton back;
	
	public LeaderBoard(ArrayList<LeaderBoardEntry> leaders, final JPanel prev, final JFrame mainFrame){
		
		this.setSize(new Dimension(500, 500));;
		this.setLayout(null);
		back = new BackButton(new Point(20, 380), prev, mainFrame, this);
		title = new JLabel("Our Amazing Champions!");
		title.setLocation(new Point(175, 30));
		this.add(title);
		
		
		//Displaying the leaderboard not yet working
		LeaderBoardEntry a = leaders.get(0);
		JLabel b  = new JLabel(a.getScoreName() + "                     " + a.getScoreNum());
		b.setSize(new Dimension(100, 30));
		b.setLocation(new Point(20,20));
		b.setVisible(true);
		this.add(b);
		
		
		/*
		for(LeaderBoardEntry l : leaders){
			JLabel a  = new JLabel(l.getScoreName() + "                     " + l.getScoreNum());
			
		}
		*/
		this.add(back);
		
	}
}
