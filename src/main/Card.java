package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Card extends JPanel {
	
	private JButton role; // The JButton that when pressed sets the value of the field for this player

	
	/** Constructor: returns a JPanel representing the player. */
	public Card(int index, List<Integer> list) {
		setLayout(new GridLayout(1, 3));
		setPreferredSize(new Dimension(150, 150));
		setBorder(BorderFactory.createTitledBorder("Unknown"));
		role = new JButton();
		role.addActionListener(new ButtonListener(index, list));
		add(role);
	}
	
	/** Sets the players name to n */
	public void setPlayerName(String n) {
		setBorder(BorderFactory.createTitledBorder(n));
	}
	
	/** Sets the players role to n */
	public void setRole(String r) {
		role.setText(r);
	}
		
}
