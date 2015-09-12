package main;

import roles.Role;

/**
 * Represents what a player thinks a Center Card is based on the information that they have.
 * @author devin
 */
public class CenterCard implements CardLocation {

    private Role role;
    
    public CenterCard(Role role) {
        this.role = role;
    }
    
    @Override public Role getRole() {
        return role;
    }

    @Override public void setRole(Role role) {
        this.role = role;
    }

}
