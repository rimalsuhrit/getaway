package getaway.nosqlpersistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import getaway.nosql.domain.Getaway;

public interface GetawayRepository extends MongoRepository<Getaway, Integer> {
	Getaway findByName(String name);
}
