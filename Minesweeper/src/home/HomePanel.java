package home;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.MainFrame;

public class HomePanel extends JPanel implements ActionListener{

	JButton easyButton, intermediateButton, hardButton;
	
	public HomePanel() {
		this.setLayout(new GridLayout(3, 1, 0, 50));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		easyButton = new JButton("<html>Easy Difficulty<br/>8x8, 10 Mines</html>");
		intermediateButton = new JButton("<html>Medium Map<br/>16x16, 40 Mines</html>");
		hardButton = new JButton("<html>Large Map<br/>24x24, 99 Mines</html>");
		this.add(easyButton);
		this.add(intermediateButton);
		this.add(hardButton);
		easyButton.addActionListener(this);
		intermediateButton.addActionListener(this);
		hardButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame parent = (MainFrame)SwingUtilities.getRoot(this);
		if(arg0.getSource() == easyButton) {
			parent.startGame(Difficulty.EASY);
		} else if(arg0.getSource() == intermediateButton) {
			parent.startGame(Difficulty.INTERMEDIATE);
		} else if(arg0.getSource() == hardButton) {
			parent.startGame(Difficulty.HARD);
		}
	}
}
