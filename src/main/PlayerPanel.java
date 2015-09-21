package main;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	
	private JButton role;
	
	public PlayerPanel() {
		setLayout(new GridLayout(1, 3));
		role = new JButton();
		add(role);
		setBorder(BorderFactory.createTitledBorder("Unknown"));
	}
	
	public void setPlayerName(String n) {
		setBorder(BorderFactory.createTitledBorder(n));
	}
	
	public void setRole(String r) {
		role.setText(r);
	}
}
