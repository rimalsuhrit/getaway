package getaway.domain.security;

import javax.persistence.*;

import getaway.domain.AbstractDomainClass;
import getaway.domain.User;
import getaway.domain.enums.UserRoleEnum;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role extends AbstractDomainClass {

	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private List<User> users = new ArrayList<>();

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		if (!this.users.contains(user)) {
			this.users.add(user);
		}

		if (!user.getRoles().contains(this)) {
			user.getRoles().add(this);
		}
	}

	public void removeUser(User user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}
}
