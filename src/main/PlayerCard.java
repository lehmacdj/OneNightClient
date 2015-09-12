package main;

import roles.Role;

/**
 * Represents what a certain players card is.
 * @author devin
 *
 */
public class PlayerCard implements CardLocation {

	private Role role;
	private String name;
	
	/**
	 * Creates a new instance of this object with name n and Role r;
	 */
	public PlayerCard(String n, Role r) {
		role = r;
		name = n;
	}
	
	/**
	 * Role is null if the player has no information regarding what it is.
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the name of the player to role.  null represents that the client 
	 * has no information regarding what the role is.
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * Returns the name of the player.
	 * @return the name of the player.
	 */
	public String getName() {
		return name;
	}

}
