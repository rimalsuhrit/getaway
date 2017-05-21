package getaway.scripts.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import getaway.nosql.domain.Address;
import getaway.nosql.domain.Country;
import getaway.nosql.domain.Getaway;
import getaway.nosql.domain.Review;
import getaway.nosqlpersistence.repositories.GetawayRepository;
import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"getaway.nosqlpersistence.repositories"})
@EntityScan(basePackages = {"getaway.nosqldomain"})
public class MongoBootstrap implements CommandLineRunner {
	private GetawayRepository getawayRepository;

	@Autowired
	public void setGetawayRepository(GetawayRepository getawayRepository) {
		this.getawayRepository = getawayRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		loadGetAways();
	}

	public static void main(String[] args) {
		SpringApplication.run(MongoBootstrap.class);
	}

	@SuppressWarnings("unchecked")
	private void loadGetAways() {

		getawayRepository.deleteAll();

		Country usaCountry = new Country();
		usaCountry.setName("United States of America");
		usaCountry.setIso2("US");
		usaCountry.setIso3("USA");

		Address nationalMallAddress = new Address();
		nationalMallAddress.setCity("Washington DC");
		nationalMallAddress.setCountry(usaCountry);

		Review nationalMallReview1 = new Review();
		nationalMallReview1.setRating(5);
		nationalMallReview1.setApproved(true);
		nationalMallReview1.setUserId(1L);

		Getaway nationalMallGetAway = new Getaway();
		nationalMallGetAway.setName("National Mall");
		nationalMallGetAway.setDescription("National Mall in DC");
		nationalMallGetAway.setAddress(nationalMallAddress);
		nationalMallGetAway.setReviews(Arrays.asList(
				nationalMallReview1
		));

		getawayRepository.save(nationalMallGetAway);
	}
}
