package getaway.persistence.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import getaway.domain.User;
import getaway.persistence.repositories.UserRepository;
import getaway.persistence.security.services.EncryptionService;
import getaway.persistence.services.UserService;
import java.util.ArrayList;
import java.util.List;

@Service
@ComponentScan(basePackages = {"getaway.persistence.security.services"})
public class UserServiceRepoImpl implements UserService {

	private EncryptionService encryptionService;
	private UserRepository userRepository;

	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

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

		if(domainObject.getPassword() != null) {
			domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
		}

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

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}
}
