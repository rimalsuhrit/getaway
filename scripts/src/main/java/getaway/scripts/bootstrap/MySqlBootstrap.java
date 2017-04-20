package getaway.scripts.bootstrap;

import static getaway.domain.enums.UserRoleEnum.ADMIN;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import getaway.domain.User;
import getaway.domain.enums.UserRoleEnum;
import getaway.domain.security.Role;
import getaway.persistence.services.RoleService;
import getaway.persistence.services.UserService;
import java.util.List;

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
	}

	@SuppressWarnings("unchecked")
	private void loadUsers() {
		List<Role> allRoles = (List<Role>) roleService.listAll();

		User superAdminUser = new User();
		superAdminUser.setEnabled(true);
		superAdminUser.setUsername("suhrit+superadmin@g8away.com");
		superAdminUser.setRoles(allRoles);
		userService.saveOrUpdate(superAdminUser);

		List<Role> adminRoles = allRoles.stream().filter(role -> role.getRole() == ADMIN).collect(toList());

		User adminUser = new User();
		adminUser.setEnabled(true);
		adminUser.setUsername("suhrit+superadmin@g8away.com");
		adminUser.setRoles(adminRoles);
		userService.saveOrUpdate(adminUser);
	}

	@SuppressWarnings("unchecked")
	private void loadRoles() {
		Role adminRole = new Role();
		adminRole.setRole(ADMIN);
		roleService.saveOrUpdate(adminRole);

		Role superAdminRole = new Role();
		superAdminRole.setRole(UserRoleEnum.SUPER_ADMIN);
		roleService.saveOrUpdate(superAdminRole);
	}
}
