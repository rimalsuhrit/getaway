package getaway.persistence.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import getaway.domain.security.Role;
import getaway.persistence.repositories.RoleRepository;
import getaway.persistence.services.RoleService;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleSerivceRepoImpl implements RoleService {
	private RoleRepository roleRepository;

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<?> listAll() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);

		return roles;
	}

	@Override
	public Role getById(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role saveOrUpdate(Role domainObject) {
		return roleRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		roleRepository.delete(id);
	}

	@Override
	public void delete(Role domainObject) {
		roleRepository.delete(domainObject);
	}
}
