package getaway.scripts.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import getaway.domain.enums.UserRoleEnum;
import getaway.domain.security.Role;
import getaway.persistence.services.RoleService;
import getaway.persistence.services.UserService;

@Component
public class MySqlBootstrap implements CommandLineRunner{
	private UserService userService;
	private RoleService roleService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public void run(String... strings) throws Exception {
		loadRoles();
		loadUsers();
		assignUsersToDefaultRole();
	}

	private void loadUsers() {

	}

	private void loadRoles() {
		Role role = new Role();
		role.setRole(UserRoleEnum.ADMIN);

		roleService.saveOrUpdate(role);
	}

	private void assignUsersToDefaultRole() {

	}
}
