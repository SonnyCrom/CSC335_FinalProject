package controller;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

import javax.swing.SwingUtilities;

import model.Difficulty;
import model.Model;

public class StartController implements ActionListener{
	
	public StartController() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("StartHard")) {
			Model model = new Model(false, Difficulty.HARD);
			Object start =  e.getSource();
			Window window = SwingUtilities.getWindowAncestor((Component) start);
			window.dispose();
			return;
		}
		if(command.equals("Quit")) {
			System.exit(0);
		}
		if(command.equals("StartEasy")) {
			Model model = new Model(false, Difficulty.EASY);
			Object start =  e.getSource();
			Window window = SwingUtilities.getWindowAncestor((Component) start);
			window.dispose();
			return;
		}
		if(command.equals("Load")) {
			Model model = new Model(true, Difficulty.EASY);
			Object start =  e.getSource();
			Window window = SwingUtilities.getWindowAncestor((Component) start);
			window.dispose();
			return;
		}
		
	}

}
