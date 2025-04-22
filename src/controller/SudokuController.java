package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.Observer;

public class SudokuController implements ActionListener {
	private Model model;

	public SudokuController(Model model) {
		this.model = new Model();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("empty")) {
			model.changeNum(0);
		}
		if(command.equals("one")) {
			model.changeNum(1);
		}
		if(command.equals("two")) {
			model.changeNum(2);
		}
		if(command.equals("three")) {
			model.changeNum(3);
		}
		if(command.equals("four")) {
			model.changeNum(4);
		}
		if(command.equals("five")) {
			model.changeNum(5);
		}
		if(command.equals("six")) {
			model.changeNum(6);
		}
		if(command.equals("seven")) {
			model.changeNum(7);
		}
		if(command.equals("eight")) {
			model.changeNum(8);
		}
		if(command.equals("nine")) {
			model.changeNum(9);
		}
	}
	
	public void addObserver(Observer observer) {
		model.registerObserver(observer);
	}
}
