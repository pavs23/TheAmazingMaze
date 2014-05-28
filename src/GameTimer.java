import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A class that represents a timer for the game.
 * Each game is 2 minutes and 59 s.
 * @author JosephineJs
 *
 */
public class GameTimer {
	private static final int TOTAL_MINS = 2;
	private static final int TOTAL_SECONDS = 59;
	private static final int ONE_SECOND = 1000;

	private int elapsedSeconds = 0;
	private int minutesRepo = TOTAL_MINS;
	private int secondsRepo = TOTAL_SECONDS;

	private int min = TOTAL_MINS;
	private int sec = TOTAL_SECONDS;
	private Timer myTimer;
	private JLabel timerLabel;
	private SinglePlayer singlePlayer;
	
	/**
	 * Create timer for the game.
	 * @param label the label which will show the timer.
	 * @param singlePlayer the SinglePlayer class that calls this timer.
	 */
	public GameTimer(JLabel label, SinglePlayer singlePlayer){
		
		timerLabel = label;
		this.singlePlayer = singlePlayer;
		
	}
	
	/**
	 * Start the timer.
	 */
	public void start() {
		myTimer = new Timer(ONE_SECOND, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
	            elapsedSeconds++;

	            if (sec == 0 && min == 0) {
	                myTimer.stop();
	                singlePlayer.gameEndLost();           
	            } else if (sec == 0 && min != 0) {
	                minutesRepo--;
	                min--;
	                elapsedSeconds = 0;
	                secondsRepo = TOTAL_SECONDS;
	                sec = TOTAL_SECONDS;
	            }
	            
	            if (min >= 0 && sec > 0) {
	                
	                sec = secondsRepo -  elapsedSeconds;
	                    String text = String.format("%02d : %02d", min,
	                            sec);
	                    timerLabel.setText(text);
	            }
	        }
		});
		myTimer.start();
		
		String text = String.format("%02d : %02d", minutesRepo, sec);
		timerLabel.setText(text);
	}
	
	/**
	 * Pause the timer.
	 */
	public void pause() {
		myTimer.stop();
	}
	
	/**
	 * Get the current second of the timer.
	 * @return the current second.
	 */
	public int getCurrentSecond() {
		return min*60 + sec;
	}
}