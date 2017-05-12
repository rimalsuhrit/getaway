package getaway.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import getaway.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
}
