package roles;

/**
 * @author devin
 */
public abstract class Role {
    
    private final String name;
    
    Role(String name) {
        this.name = name;
    }
    
    @Override public String toString() {
        return name;
    }
    
    public String getName() {
    	return name;
    }
    
}
