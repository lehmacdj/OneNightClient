package main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	
	private JButton role;
	
	public PlayerPanel() {
		setLayout(new GridLayout(1, 3));
		setPreferredSize(new Dimension(150, 150));
		setBorder(BorderFactory.createTitledBorder("Unknown"));
		role = new JButton();
		role.addActionListener(new ButtonListener(index++));
		add(role);
	}
	
	public void setPlayerName(String n) {
		setBorder(BorderFactory.createTitledBorder(n));
	}
	
	public void setRole(String r) {
		role.setText(r);
	}
	
	private static int index;
	
}
