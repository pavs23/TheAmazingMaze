import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class SelectGameMode extends JPanel{
	
	private GameModeButton coinMode;
	private GameModeButton adventureMode;
	
	public SelectGameMode(Dimension d){
		this.setSize(d);
		
		coinMode = new GameModeButton("Coin Mode", Color.YELLOW);
		adventureMode = new GameModeButton("Adventure", Color.green);
		//Still need to hook up this panel to the frame.
		//	also need to put the frame into the chain of the frames.
		
		this.add(coinMode);
		this.add(adventureMode);
		
	}
}
