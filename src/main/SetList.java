package main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import roles.Role;

@SuppressWarnings("serial")
public class SetList extends JPanel {
	
	private List<Role> roles;
	private List<JLabel> labels;
	
	public SetList(List<Role> s) {
		roles = s;
		labels = new ArrayList<>();
		setPreferredSize(new Dimension(100, 720));
		System.out.println(s);
		roles.stream().forEach(r -> {
			assert r != null;
			assert r.getName() != null;
			JLabel label = new JLabel();
			label.setText(r.getName());
			label.setPreferredSize(new Dimension(100, 15));
			add(label);
			labels.add(label);
		});
	}
}
