package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import roles.Role;

@SuppressWarnings("serial")
/** Encapsulates the players and the center cards. */
public class PlayArea extends JPanel {
	
	private List<Card> players;
	private List<Card> centerCards;
	
	/** Returns a play area representing the roles passed in with action listeners
	 * for the buttons using the list, with dimensions of size by size. The player count is
	 * given by playerCount */
	public PlayArea(int playerCount, List<Role> roles, int size, List<Integer> list) {
		setLayout(new CircleLayout());
		setPreferredSize(new Dimension(size, size));

		// Initialize the players cards
		players = new ArrayList<>();
		for (int i = 0; i < playerCount; i++) {
			Card panel = new Card(i, list);
			add(panel, CircleLayout.EDGE);
			players.add(panel);
		}
		
		// Initialize the center area
		centerCards = new ArrayList<>();
		JPanel centers = new JPanel();
		centers.setLayout(new GridLayout(1, 3));
		for (int i = 0; i < 3; i++) {
			Card panel = new Card(i, list);
			panel.setName("Center " + i);
			centers.add(panel);
			centerCards.add(panel);
		}
		add(centers, CircleLayout.CENTER);
		
		
	}
	
	/** Returns the player card for the player with index i */
	public Card getPlayer(int i) {
		return players.get(i);
	}
	
	public Card getCenter(int i) {
		return centerCards.get(i);
	}
	
}
