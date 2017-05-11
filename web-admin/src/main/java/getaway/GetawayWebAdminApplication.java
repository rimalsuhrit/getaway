package getaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"getaway.persistence"})
@EntityScan(basePackages = {"getaway.domain"})
public class GetawayWebAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(GetawayWebAdminApplication.class);
	}
}
