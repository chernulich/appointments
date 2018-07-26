package appointer;

/**
 * Interface for AppEvent's payload
 */
public interface EventProperties {
	public EventCategory getCategory();
	public EventCategory setCategory(EventCategory category);
}
