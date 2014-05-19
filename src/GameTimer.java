import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameTimer {

	private static final String PAUSE = "PAUSE";
	private static final int TOTAL_MINS = 59;
	private static final int TOTAL_SECONDS = 59;
	private static final int ONE_SECOND = 1000;

	private int elapsedSeconds = 0;
	private int minutesRepo = TOTAL_MINS;
	private int secondsRepo = TOTAL_SECONDS;

	private int min = TOTAL_MINS;
	private int sec = TOTAL_SECONDS;
	private Timer myTimer;
	private JLabel timerLabel;
	private PlayerModes playerModes;
	
	public GameTimer(JLabel label, PlayerModes playerModes){
		
		timerLabel = label;
		this.playerModes = playerModes;
		
	}
	
	public void start(){
		

			//tracker = secondsRepo;
			myTimer = new Timer(ONE_SECOND, new TimerListener());
			myTimer.start();

			String text = String
					.format("CountDown %d : %d", minutesRepo, sec);
			timerLabel.setText(text);

		
	}
	
	public void pause(){
		
			timerLabel.setText(PAUSE);
			myTimer.stop();

		
	}
	
	public int getCurrentSecond(){
		
		return sec;
		
	}
	
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			elapsedSeconds++;

			if (sec == 0 && min == 0) {
				playerModes.gameEnd();
				myTimer.stop();
			} else if (sec == 0 && min != 0) {
				minutesRepo--;
				min--;
				elapsedSeconds = 0;
				secondsRepo = TOTAL_SECONDS;
				sec = TOTAL_SECONDS;
			}
			
			if (min >= 0 && sec > 0) {
				
				sec = secondsRepo -  elapsedSeconds;
					String text = String.format("CountDown %d : %d", min,
							sec);
					timerLabel.setText(text);
			}
		}
	}
	
}