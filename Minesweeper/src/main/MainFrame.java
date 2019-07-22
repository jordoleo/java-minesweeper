package main;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public MainFrame() {
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
