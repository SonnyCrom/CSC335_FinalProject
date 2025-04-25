package view;

import javax.swing.JLabel;

public class TimerLabel extends JLabel implements TimerObserver{

	
	public TimerLabel(int time) {
		super("Time: " + Integer.toString(time / 60) + " : " + String.format("%02d", (time % 60)) , JLabel.LEFT);
		this.setSize(250, 100);
	}

	@Override
	public void newTime(int time) {
		this.setText("Time: " + Integer.toString(time / 60) + " : " + String.format("%02d", (time % 60) ));
	}
}
