package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StartController;
import model.DbConnector;
import model.GameBoard;


public class StartScreen extends JFrame{
	
	private StartController controller;
	
	public StartScreen() {
		this.setTitle("Sudoku!");
		this.setSize(750,750);
		this.setUp();
	}
	
	private void setUp() {
		DbConnector conn = new DbConnector();
		
		this.controller = new StartController();
		setLayout(new BorderLayout());
		
			Font font = null;
		
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/FranklinGothic.ttf"));
				font = font.deriveFont(20f);
			} catch (IOException|FontFormatException e) {
				font = new Font("Arial", Font.PLAIN, 12);
				e.printStackTrace();
			}	
		
		try {
			BufferedImage coverArt = ImageIO.read(new File("assets/coverart.png"));
			JLabel coverPicLabel = new JLabel(new ImageIcon(coverArt));
			add(coverPicLabel, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setPreferredSize(new Dimension(800,100));
		
		JButton startEasy = new JButton("Start Easy Game");
		startEasy.setFont(font);
		startEasy.setPreferredSize(new Dimension(200,100));
		startEasy.setBackground(new Color(255,149,21));
		startEasy.setForeground(new Color(0,0,0));
		startEasy.setActionCommand("StartEasy");
		startEasy.addActionListener(controller);
		topPanel.add(startEasy);
		
		JButton startHard = new JButton("Start Hard Game");
		startHard.setFont(font);
		startHard.setBackground(new Color(255,149,21));
		startHard.setPreferredSize(new Dimension(200,100));
		startHard.setForeground(new Color(0,0,0));
		startHard.setActionCommand("StartHard");
		startHard.addActionListener(controller);
		topPanel.add(startHard);
		
		JButton loadGame = new JButton("Load Game");
		loadGame.setFont(font);
		loadGame.setPreferredSize(new Dimension(200,100));
		loadGame.setForeground(new Color(0,0,0));
		
		Boolean isPreviousLoadGame = conn.isSaveExist();
		if(isPreviousLoadGame) {
			loadGame.setBackground(new Color(255,149,21));
			loadGame.setPreferredSize(new Dimension(200,100));
			loadGame.setForeground(new Color(0,0,0));
			loadGame.setActionCommand("Load");
			loadGame.addActionListener(controller);
			topPanel.add(loadGame);
		}
		else {
			loadGame.setBackground(new Color(128, 128, 128));
		}
		topPanel.add(loadGame);
		

		
		
		JButton quit = new JButton("Quit");
		quit.setBackground(new Color(21, 127, 255));
		quit.setFont(font);
		quit.setForeground(new Color(0));
		quit.setActionCommand("Quit");
		quit.setPreferredSize(new Dimension(750, 100));
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
