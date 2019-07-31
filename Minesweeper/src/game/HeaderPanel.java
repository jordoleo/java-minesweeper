package game;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import game.icon.TileIcon;
import game.icon.TileIconContainer;
import main.MainFrame;

public class HeaderPanel extends JPanel implements ActionListener{

	JPanel leftPanel, middlePanel, rightPanel;
	JButton resetButton;
	JLabel flagCountLabel, timeTickLabel;
	
	Timer timer;
	Integer time = 0;
	
	public HeaderPanel() {
		setPreferredSize(new Dimension(0, 150));
		
		leftPanel = new JPanel();
		middlePanel = new JPanel();
		rightPanel = new JPanel();
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		
		flagCountLabel = new JLabel("00");
		timeTickLabel = new JLabel("000");
		
		setLayout(new BorderLayout());
		
		middlePanel.setLayout(new GridBagLayout());
		middlePanel.add(resetButton);
		
		leftPanel.setLayout(new GridBagLayout());
		leftPanel.setPreferredSize(new Dimension(150, 150));
		leftPanel.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.MINESWEEPER_F, 20)));
		leftPanel.add(new JLabel("   "));
		leftPanel.add(flagCountLabel);
		
		rightPanel.setLayout(new GridBagLayout());
		rightPanel.setPreferredSize(new Dimension(150, 150));
		rightPanel.add(new JLabel(TileIconContainer.getInstance().get(TileIcon.MINESWEEPER_C, 20)));
		rightPanel.add(new JLabel("   "));
		rightPanel.add(timeTickLabel);
		
		add(middlePanel);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		
		timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == timer) {
			time++;
			timeTickLabel.setText(String.format("%03d", time));			
		} else if(arg0.getSource() == resetButton) {
			MainFrame parent = (MainFrame)SwingUtilities.getRoot(this);
			parent.mainMenu();
		}
		
	}
	
	public void setFlag(int flagCount) {
		flagCountLabel.setText(String.format("%02d", flagCount));
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public int getTime() {
		return time;
	}
}
