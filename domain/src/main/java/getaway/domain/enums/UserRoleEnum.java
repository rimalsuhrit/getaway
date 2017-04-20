package getaway.domain.enums;

public enum UserRoleEnum {
	SUPER_ADMIN("Super Administrator"),
	ADMIN("Administrator");

	private final String label;

	UserRoleEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
