package main;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	
	private JLabel role;
	
	public PlayerPanel() {
		setLayout(new GridLayout(1, 3));
		role = new JLabel();
		role.setBorder(BorderFactory.createEtchedBorder());
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
