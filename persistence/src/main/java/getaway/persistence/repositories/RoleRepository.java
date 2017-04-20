package getaway.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import getaway.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
