package getaway.persistence.security.services;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = {"getaway.persistence.security.config"})
public class EncryptionServiceImpl implements EncryptionService {

	private StrongPasswordEncryptor strongPasswordEncryptor;

	@Autowired
	public void setStrongPasswordEncryptor(StrongPasswordEncryptor strongPasswordEncryptor) {
		this.strongPasswordEncryptor = strongPasswordEncryptor;
	}

	@Override
	public String encryptString(String plainString) {
		return strongPasswordEncryptor.encryptPassword(plainString);
	}

	@Override
	public boolean checkPassword(String plainPassword, String encryptedPassword) {
		return strongPasswordEncryptor.checkPassword(plainPassword,encryptedPassword);
	}
}
