package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

	private final int index;
	
	public ButtonListener(int index) {
		this.index = index;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	private static int nextIndex;

}
