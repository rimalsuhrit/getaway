package getaway.persistence.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import getaway.domain.User;
import getaway.persistence.repositories.UserRepository;
import getaway.persistence.services.UserService;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceRepoImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<?> listAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);

		return users;
	}

	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		return userRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}

	@Override
	public void delete(User domainObject) {
		userRepository.delete(domainObject);
	}
}
