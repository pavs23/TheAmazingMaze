import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameTimer {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui();
			}
		});
	}
	
	private static void createAndShowGui() {
		Gui mainPanel = new Gui();

		JFrame frame = new JFrame("GameTimer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
	}

}

class Gui extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String PRESS_BUTTON = "Press Button ";
	private static final String RESUME = "Resume Timer ";
	private static final String YOU_LOSE = "Time's Up! You Lose!";
	private static final String PAUSE = "PAUSE";
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private static final int TOTAL_SECONDS = 59;
	private static final int TOTAL_MIN = 2;
	private static final int ONE_SECOND = 1000;

	private int elapsedSeconds = 0;
	private int min = 2;
	private int secondsRemaining = 0;
	private int secondsRepo = TOTAL_SECONDS;
	private int minsRepo = TOTAL_MIN;
	private JLabel timerLabel = new JLabel(PRESS_BUTTON);
	private JButton start = new JButton("||>");
	private JButton reset = new JButton("Reset");
	private Timer myTimer;

	public Gui() {
		JPanel btnPanel = new JPanel();
		btnPanel.add(start);
		btnPanel.add(reset);

		setLayout(new BorderLayout());
		add(btnPanel, BorderLayout.CENTER);
		add(timerLabel, BorderLayout.SOUTH);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (myTimer != null && myTimer.isRunning()) {
					secondsRepo = secondsRemaining;
					minsRepo = min;
					myTimer.stop();
					myTimer = null;
					timerLabel.setText(PAUSE);
				
				} else {
					elapsedSeconds = 0;
					myTimer = new Timer(ONE_SECOND, new TimerListener());
					myTimer.start();
					
					String text = String.format("%s %d : %d",
							minsRepo , secondsRepo);
					timerLabel.setText(text);
					
				}
			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0){
				
				elapsedSeconds = 0;
				myTimer = new Timer(ONE_SECOND, new TimerListener());
				myTimer.start();
				
				String text = String.format("%s %d : %d",
						RESUME, TOTAL_MIN - 1 , TOTAL_SECONDS);
				timerLabel.setText(text);
	
			}
		});
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			elapsedSeconds++;
			
			if (secondsRemaining == 0 && min == 0){
				myTimer.stop();
				elapsedSeconds = 0;
				timerLabel.setText(YOU_LOSE);
			} else if (secondsRemaining == 0 && min != 0){
				min--;
				elapsedSeconds = 0;
				secondsRemaining = TOTAL_SECONDS;
			}
			
			if(min >= 0 && secondsRemaining != 0){
				secondsRemaining = TOTAL_SECONDS - elapsedSeconds;
				String text = String.format("%s %d : %d", RESUME, min,
						secondsRemaining);
				timerLabel.setText(text);
			}
		}
	}
}
