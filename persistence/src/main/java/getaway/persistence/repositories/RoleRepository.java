package getaway.persistence.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import getaway.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

	@Transactional
	Long deleteById(Long id);

	Role findById(Long id);
}
