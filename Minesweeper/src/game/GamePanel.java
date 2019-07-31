package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private GameTile[][] map;
	private ArrayList<GameTile> gameTiles;
	private int bombCount;
	private int flagSet;
	private boolean generated;
	private int height;
	private int width;
	private HeaderPanel headerPanel;
	private int tilesOpened;
	
	public GamePanel(int height, int width, int bombCount) {
		this.height = height;
		this.width = width;
		this.bombCount = bombCount;
		this.flagSet = 0;
		tilesOpened = 0;
		
		map = new GameTile[height][width];
		gameTiles = new ArrayList<>();
		
		setLayout(new GridLayout(height, width));
		
		int size = 480/height;
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				GameTile t = new GameTile(i, j, size);
				gameTiles.add(t);
				map[i][j] = t;
				add(t);
			}
		}
		
		
	}
	
	private void generate(int row, int col) {
		ArrayList<GameTile> surroundingTiles = getSurroundingTiles(row, col);
		surroundingTiles.add(map[row][col]);
		int bombCount = this.bombCount;
		Collections.shuffle(gameTiles);
		for(int i = 0; bombCount > 0 && i < gameTiles.size(); i++) {
			GameTile current = gameTiles.get(i);
			if(!surroundingTiles.contains(current)) {
				current.setBomb(true);
				ArrayList<GameTile> surroundingCurrent = getSurroundingTiles(current.getRow(), current.getCol());
				for(GameTile t: surroundingCurrent) {
					t.incrementNumber();
				}
				bombCount--;
			}
		}
	}
	
	private ArrayList<GameTile> getSurroundingTiles(int row, int col) {
		ArrayList<GameTile> container = new ArrayList<>();
		if(row != 0) {
			container.add(map[row-1][col]);
		}
		if(row != height - 1) {
			container.add(map[row+1][col]);
		}
		if(col != 0) {
			container.add(map[row][col-1]);
		}
		if(col != width - 1) {
			container.add(map[row][col+1]);
		}
		if(row != 0 && col != 0) {
			container.add(map[row-1][col-1]);
		}
		if(row != 0 && col != width - 1) {
			container.add(map[row-1][col+1]);
		}
		if(row != height - 1 && col != 0) {
			container.add(map[row+1][col-1]);
		}
		if(row != height - 1 && col != width - 1) {
			container.add(map[row+1][col+1]);
		}
		return container;
	}
	
	private void removeTilesListener() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				GameTile t = map[i][j];
				t.removeMouseListener(t);
			}
		}
	}
	
	public void setHeaderPanel(HeaderPanel headerPanel) {
		this.headerPanel = headerPanel;
		updateFlag();
	}
	
	public HeaderPanel getHeaderPanel() {
		return this.headerPanel;
	}
	
	public int open(int row, int col) {
		if(!generated) {
			generate(row, col);
			generated = true;
		}
		ArrayList<GameTile> container = new ArrayList<>();
		GameTile first = map[row][col];
		if(first.isBomb()) {
			first.setOpen(true);
			first.update();
			revalidate();
			repaint();
			removeTilesListener();
			headerPanel.stopTimer();
			JOptionPane.showMessageDialog(null, "You Lose");
			return -1;
		}
		container.add(first);
		first.setOpen(true);
		first.update();
		tilesOpened++;
		while(!container.isEmpty()) {
			GameTile current = container.remove(0);
			if(current.getNumber() == 0) {
				ArrayList<GameTile> surrounding = getSurroundingTiles(current.getRow(), current.getCol());
				for(GameTile t: surrounding) {
					if(!t.isOpen()) {
						t.setOpen(true);
						tilesOpened++;
						t.update();
						t.setBorder(BorderFactory.createEmptyBorder());
						container.add(t);
					}
				}
			}
		}
		revalidate();
		repaint();
		checkWin();
		return 1;
	}
	
	public boolean addFlag() {
		if(flagSet < bombCount) {
			flagSet++;
			checkWin();
			return true;
		}
		return false;
	}
	
	public void reduceFlag() {
		flagSet--;
	}
	
	public void updateFlag() {
		headerPanel.setFlag(bombCount - flagSet);
	}
	
	private void checkWin() {
		if(flagSet == bombCount && tilesOpened + bombCount == width * height) {
			removeTilesListener();
			headerPanel.stopTimer();
			JOptionPane.showMessageDialog(this, "<html>You WIN!<br>Your Time: " + headerPanel.getTime() + "</html>");
		}
	}
}
