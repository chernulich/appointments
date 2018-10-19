package appointer.calendar;

import java.util.concurrent.ConcurrentHashMap;

import biweekly.ICalendar;

/**
 * User class
 */
public class AppUser implements User {

	private String name;

	private ICalendar calendar;

	public static ConcurrentHashMap.KeySetView<String, Boolean> names = ConcurrentHashMap.newKeySet();

	/**
	 * Creates a new user with the given name if the name is not taken;
	 * @param name user name
	 * @throws IllegalArgumentException
	 */
	public AppUser(String name) throws IllegalArgumentException {
		synchronized (names) {
			this.name = name;
			if (names.contains(name))
				throw new IllegalArgumentException();
			names.add(name);
		}
	}

	@Override
	public ICalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(ICalendar calendar) {
		this.calendar = calendar;
	}

	@Override
	public String getName() {
		return name;
	}
}
