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
	private int row;
	private int col;
	private int number;
	private boolean bomb;
	private boolean flag;
	private boolean open;
	private int size;
	
	public GameTile(int row, int col, int size) {
		this.row = row;
		this.col = col;
		this.size = size;
		this.number = 0;
		this.bomb = false;
		this.flag = false;		
		this.setLayout(new BorderLayout());
		this.addMouseListener(this);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.MINESWEEPER_X, size)));
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
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
		this.removeAll();
		if(this.isFlag()) {
			this.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.MINESWEEPER_F, size)));	
		} else if(!this.isOpen()) {
			this.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.MINESWEEPER_X, size)));
		} else {
			if(this.isBomb() && this.isOpen()) {
				this.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.MINESWEEPER_M, size)));
			} else {
				this.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.valueOf("MINESWEEPER_" + this.number), size)));		
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(!this.isFlag() && !this.isOpen()) {
				GamePanel parent = (GamePanel)getParent();
				parent.open(row, col);
			}
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			if(!this.isOpen()) {
				GamePanel parent = (GamePanel)getParent();
				if(this.isFlag()) {
					parent.reduceFlag();
					this.setFlag(false);
				} else {
					if(parent.addFlag())
						this.setFlag(true);					
				}
				
				update();
				parent.updateFlag();
				parent.revalidate();
				parent.repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(!this.isOpen())
			this.setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(!this.isOpen())
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
