package roles;

/**
 * @author devin
 */
public abstract class Role implements Comparable<Role> {
    
    private final String name;
    
    Role(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public String getName() {
    	return name;
    }
    
    @Override
	public int compareTo(Role r) {
    	return name.compareTo(r.name);
    }
    
}
