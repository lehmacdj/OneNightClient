package main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import roles.Role;

@SuppressWarnings("serial")
public class PlayArea extends JPanel {
	
	private List<PlayerPanel> players;
	private CenterArea centerArea;
	
	public PlayArea(int playerCount, List<Role> roles, int dim) {
		setLayout(new CircleLayout());
		setPreferredSize(new Dimension(dim, dim));
		players = new ArrayList<>();
		for (int i = 0; i < playerCount; i++) {
			PlayerPanel panel = new PlayerPanel();
			add(panel, CircleLayout.EDGE);
			players.add(panel);
		}
		
		centerArea = new CenterArea(roles);
		add(centerArea, CircleLayout.CENTER);
		
	}
	
	public PlayerPanel getPlayer(int i) {
		return players.get(i);
	}
	
	public PlayerPanel getCenter(int i) {
		return centerArea.get(i);
	}
	
}
