package model;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.BoardObserver;

// TESTING Purpose --> Shows where in grid clicked

public class NumRep extends JLabel implements BoardObserver {
	
	private JPanel panel;
	
	public NumRep() {
		super("Current Cords: " + 0 + " " + 0);
		this.setSize(250,100);
	}
	
	public void newCord(int i, int j) {
		this.setText("Current Cords: " + i + " " + j);
	}
}