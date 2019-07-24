package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import game.icon.TileIcon;
import game.icon.TileIconContainer;

public class GameTile extends JPanel implements MouseListener{
	private int number;
	private boolean bomb;
	private boolean flag;
	private boolean open;
	
	public GameTile() {
		this.number = 0;
		this.bomb = false;
		this.flag = false;
		this.setLayout(new BorderLayout());
		this.addMouseListener(this);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	}

	public int getNumber() {
		return number;
	}

	public void incrementNumber() {
		this.number++;
	}

	public boolean isBomb() {
		return bomb;
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void update() {
		this.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.MINESWEEPER_X)));
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
