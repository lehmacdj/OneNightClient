package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListener implements ActionListener {

	private final int index;
	private final List<Integer> list;
	
	/**
	 * Contructor: creates a new ButtonListener that relays button presses to an
	 * Synchronized List<Integer> signal that is read by the state to determine
	 * what action should be performed. The index is the index of the list to be
	 * used for this button. The integer can be used to determine the order the button
	 * presses were given in.
	 */
	public ButtonListener(int index, List<Integer> signal) {
		this.index = index;
		list = signal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		list.set(index, counter++);
	}

	private int counter = 0; //the number of times the counter was incremeted
	
	public void reset() {
		counter = 0;
	}
	
}
