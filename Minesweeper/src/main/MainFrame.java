package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.GamePanel;

public class MainFrame extends JFrame{

	public MainFrame() {
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GamePanel mainPanel = new GamePanel(10, 10);
		this.add(mainPanel);
		
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
