package pl.indecoders.archetype.domain.account;

/**
 * The Enum RoleType.
 * @author Mateusz
 */
public enum RoleType {

	USER, MODERATOR;
	
	public String getRole() {
		return toString();
	}
}
