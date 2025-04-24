package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SudokuController;
import model.GameBoard;
import model.Model;


public class StartScreen extends JFrame{
	
	private SudokuController controller;
	private GameBoard board;
	
	public StartScreen() {
		this.controller = new SudokuController(new Model());
		this.setTitle("Sudoku!");
		this.setSize(400,400);
		this.setUp();
	}
	
	
	private void setUp() {
		JPanel mainPanel = new JPanel();
		this.add(mainPanel);
		
		setLayout(new BorderLayout());
		
		
		JButton start = new JButton("Start");
		start.setBackground(new Color(34, 139, 34));
		start.setForeground(new Color(0,0,0));
		start.setActionCommand("Start");
		start.addActionListener(controller);
		add(start, BorderLayout.NORTH);
		
		JButton quit = new JButton("Quit");
		quit.setBackground(new Color(128, 0, 32));
		quit.setForeground(new Color(0));
		quit.setActionCommand("Quit");
		quit.addActionListener(controller);
		add(quit, BorderLayout.SOUTH);
		
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		this.setVisible(true);
	}
	
	
}
