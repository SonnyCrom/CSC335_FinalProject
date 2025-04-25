package controller;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import javax.swing.SwingUtilities;

import model.Difficulty;
import model.StartModel;
import view.SudokuGUI;

public class StartController implements ActionListener{

	private final StartModel model;

	public StartController() {
		this.model = new StartModel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("StartHard")) {
			new SudokuGUI(Difficulty.HARD);
			Object start =  e.getSource();
			Window window = SwingUtilities.getWindowAncestor((Component) start);
			window.dispose();
			return;
		}
		if(command.equals("Quit")) {
			System.exit(0);
		}
		if(command.equals("StartEasy")) {
			new SudokuGUI(Difficulty.EASY);
			Object start =  e.getSource();
			Window window = SwingUtilities.getWindowAncestor((Component) start);
			window.dispose();
			return;
		}
		if(command.equals("Load")) {
			new SudokuGUI();
			Object start =  e.getSource();
			Window window = SwingUtilities.getWindowAncestor((Component) start);
			window.dispose();
			return;
		}

	}

	public void setLoadBtnEnable(JButton btn){
		model.setLoadSaveEnable(btn);
	}
}
