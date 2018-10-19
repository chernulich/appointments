package appointer.user;

import appointer.util.net.NamesRESTClient;

/**
 * User class
 */
public class AppUser implements User {

	private final String name;

	/**
	 * Creates a new user with the given name if the name is not taken;
	 * 
	 * @param name
	 *            user name
	 * @throws IllegalArgumentException
	 */
	public AppUser(String name) throws IllegalArgumentException {
		if (NamesRESTClient.isUnique(name)) {
			this.name = name;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getName() {
		return name;
	}
}
