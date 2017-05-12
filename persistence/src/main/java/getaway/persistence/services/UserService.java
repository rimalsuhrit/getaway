package getaway.persistence.services;

import getaway.domain.User;

public interface UserService extends CRUDService<User> {
	User findByUserName(String userName);
}
