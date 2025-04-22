package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.ModuleLayer.Controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Numbers;
import model.Model;
import controller.SudokuController;

public class SudokuGUI extends JFrame{

	private Numbers numbers;
	private SudokuController controller;
	
	public SudokuGUI() {
		this.controller = new SudokuController(new Model());
		this.setTitle("MVC Demo");
		this.setSize(400,400);
		this.setUp();
	}
	
	private void setUp() {
		numbers = new Numbers();
		this.add(numbers);
		controller.addObserver(numbers);
		
		//setting up the main panel
		JPanel mainPanel = new JPanel();
		this.add(mainPanel);
		
		//setting up the switch button
		JButton empty = new JButton("  ");
		empty.setActionCommand("empty");
		empty.addActionListener(controller);
		mainPanel.add(empty);
		
		//setting up the switch button
		JButton one = new JButton("1");
		one.setActionCommand("one");
		one.addActionListener(controller);
		mainPanel.add(one);
		
		//setting up the switch button
		JButton two = new JButton("2");
		two.setActionCommand("two");
		two.addActionListener(controller);
		mainPanel.add(two);

		//setting up the switch button
		JButton three = new JButton("3");
		three.setActionCommand("three");
		three.addActionListener(controller);
		mainPanel.add(three);
		
		//setting up the switch button
		JButton four = new JButton("4");
		four.setActionCommand("four");
		four.addActionListener(controller);
		mainPanel.add(four);
		
		//setting up the switch button
		JButton five = new JButton("5");
		five.setActionCommand("five");
		five.addActionListener(controller);
		mainPanel.add(five);
		
		//setting up the switch button
		JButton six = new JButton("6");
		six.setActionCommand("six");
		six.addActionListener(controller);
		mainPanel.add(six);
		
		//setting up the switch button
		JButton seven = new JButton("7");
		seven.setActionCommand("seven");
		seven.addActionListener(controller);
		mainPanel.add(seven);
		
		//setting up the switch button
		JButton eight = new JButton("8");
		eight.setActionCommand("eight");
		eight.addActionListener(controller);
		mainPanel.add(eight);
		
		//setting up the switch button
		JButton nine = new JButton("9");
		nine.setActionCommand("nine");
		nine.addActionListener(controller);
		mainPanel.add(nine);		
		
		//adding a window listener for closing the app
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		this.setVisible(true);
	}
}