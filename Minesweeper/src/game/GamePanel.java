package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private GameTile[][] map;
	private ArrayList<GameTile> gameTiles;
	private int bombCount;
	private boolean generated;
	
	public GamePanel(int height, int width) {
		map = new GameTile[height][width];
		gameTiles = new ArrayList<>();
		bombCount = (height * width) / 5;
		
		setLayout(new GridLayout(height, width));
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				GameTile t = new GameTile();
				t.update();
				add(t);
			}
		}
	}
	
	private void generate(int x, int y) {
		
	}
	
	public int select(int x, int y) {
		if(!generated) {
			generate(x, y);
		}
		return 1;
	}
	
	public int open(int x, int y) {
		return 1;
	}
	
	public int flag(int x, int y) {
		return 1;
	}

}
