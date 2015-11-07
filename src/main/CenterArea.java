package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import roles.Role;

@SuppressWarnings("serial")
public class CenterArea extends JPanel {
	
	List<PlayerPanel> centerCards;
	
	public CenterArea(List<Role> roles) {
		setLayout(new GridLayout(1, 3));
		
		setPreferredSize(new Dimension(450, 150));
		
		centerCards = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			PlayerPanel centerCard = new PlayerPanel();
			centerCard.setName("Center " + i);
			add(centerCard);
			centerCards.add(centerCard);
		}
	}
	
	public PlayerPanel get(int i) {
		return centerCards.get(i);
	}
	
}
