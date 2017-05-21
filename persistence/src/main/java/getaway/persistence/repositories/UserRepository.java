package getaway.persistence.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import getaway.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Transactional
	Long deleteById(Long id);

	User findByUsername(String username);

	User findById(Long id);
}
