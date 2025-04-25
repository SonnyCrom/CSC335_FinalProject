package view;

import javax.swing.JLabel;

import model.Numbers;

public class ValidGUI extends JLabel implements ValidObserver{
	
	public ValidGUI() {
		super("");
		this.setSize(250,100);
	}

	@Override
	public void isValidMove(int x) {
		if (x == 0) {
			this.setText("");
		} else if (x == 1) {
			this.setText("Invalid Move ");
		} else {
			this.setText("Static Cell ");
		}
		
	}
}
