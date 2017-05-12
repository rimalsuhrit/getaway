package getaway.scripts.bootstrap;

import static getaway.domain.enums.UserRoleEnum.ADMIN;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import getaway.domain.User;
import getaway.domain.enums.UserRoleEnum;
import getaway.domain.security.Role;
import getaway.persistence.services.RoleService;
import getaway.persistence.services.UserService;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"getaway.persistence.repositories"})
@EntityScan(basePackages = {"getaway.domain"})
@ComponentScan(basePackages = {"getaway.persistence.services.reposervices"})
public class MySqlBootstrap implements CommandLineRunner{
	private RoleService roleService;
	private UserService userService;

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

	public static void main(String[] args) {
		SpringApplication.run(MySqlBootstrap.class);
	}

	@SuppressWarnings("unchecked")
	private void loadUsers() {
		List<Role> allRoles = (List<Role>) roleService.listAll();

		User superAdminUser = new User();
		superAdminUser.setId(1L);
		superAdminUser.setEnabled(true);
		superAdminUser.setUsername("suhrit+superadmin@g8away.com");
		superAdminUser.setPassword("password");
		superAdminUser.setRoles(allRoles);
		userService.saveOrUpdate(superAdminUser);

		List<Role> adminRoles = allRoles.stream().filter(role -> role.getRole() == ADMIN).collect(toList());

		User adminUser = new User();
		adminUser.setId(2L);
		adminUser.setEnabled(true);
		adminUser.setUsername("suhrit+superadmin@g8away.com");
		adminUser.setPassword("password");
		adminUser.setRoles(adminRoles);
		userService.saveOrUpdate(adminUser);
	}

	@SuppressWarnings("unchecked")
	private void loadRoles() {
		Role adminRole = new Role();
		adminRole.setId(1L);
		adminRole.setRole(ADMIN);
		roleService.saveOrUpdate(adminRole);

		Role superAdminRole = new Role();
		superAdminRole.setId(2L);
		superAdminRole.setRole(UserRoleEnum.SUPER_ADMIN);
		roleService.saveOrUpdate(superAdminRole);
	}
}
