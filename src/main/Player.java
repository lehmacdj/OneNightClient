package main;

import roles.Role;

public class Player {
	private int index;
	private Role role;
	
	public Player(Role r) {
		index = nextUsableIndex++;
		role = r;
	}
	
	public Player() {
		this(null);
	}
	
	public Role getRole() {
		return role;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setRole(Role r) {
		role = r;
	}
	
	private static int nextUsableIndex = 0;
	
}
