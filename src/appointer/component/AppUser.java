package appointer.component;

/**
 * mock User class
 */
public class AppUser implements User {

	private String name;
	
	public AppUser(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
