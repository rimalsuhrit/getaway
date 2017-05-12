package getaway.persistence.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import getaway.domain.User;
import getaway.persistence.services.UserService;

@Service("userDetailsService")
@ComponentScan(basePackages = {"getaway.persistence.security.converters"})
public class SpringSecUserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;
	private Converter<User, UserDetails> userToUserDetailsConverter;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier(value = "userToUserDetails")
	public void setUserToUserDetailsConterverter(Converter<User, UserDetails> userToUserDetailsConverter) {
		this.userToUserDetailsConverter = userToUserDetailsConverter;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userToUserDetailsConverter.convert(userService.findByUserName(username));
	}
}
