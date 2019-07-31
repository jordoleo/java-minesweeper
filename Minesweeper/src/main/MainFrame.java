package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.GamePanel;
import game.HeaderPanel;
import home.Difficulty;
import home.HomePanel;

public class MainFrame extends JFrame{

	private HomePanel homePanel;
	private GamePanel gamePanel;
	
	public MainFrame() {
		setSize(480, 630);
		setLocationRelativeTo(null);
		setTitle("Minesweeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.homePanel = new HomePanel();
		this.add(homePanel);
		setVisible(true);
		setResizable(false);
	}
	
	public void startGame(Difficulty d) {
		switch (d) {
		case EASY:
			gamePanel = new GamePanel(8, 8, 10);
			break;
		case INTERMEDIATE:
			gamePanel = new GamePanel(16, 16, 40);
			break;
		case HARD:
			gamePanel = new GamePanel(24, 24, 99);
			break;
		}
		if(gamePanel != null) {
			this.remove(homePanel);
			HeaderPanel hp = new HeaderPanel();
			gamePanel.setHeaderPanel(hp);
			this.add(hp, BorderLayout.NORTH);
			this.add(gamePanel);
			revalidate();
			repaint();
		}
	}
	
	public void mainMenu() {
		remove(gamePanel.getHeaderPanel());
		remove(gamePanel);
		this.add(homePanel);
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
