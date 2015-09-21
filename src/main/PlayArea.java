package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayArea extends JPanel {
	
	private List<PlayerPanel> panels;
	
	public PlayArea(int playerCount) {
//		int gridSize = (int) Math.max(Math.ceil(Math.sqrt(playerCount)), 2);
//		setLayout(new GridLayout(gridSize, gridSize));
		setLayout(new CircleLayout());
		setPreferredSize(new Dimension(720, 720));
		panels = new ArrayList<>();
		for (int i = 0; i < playerCount; i++) {
			PlayerPanel panel = new PlayerPanel();
			add(panel, CircleLayout.EDGE);
			panels.add(panel);
		}
		
	}
	
	public PlayerPanel get(int i) {
		return panels.get(i);
	}
	
}
