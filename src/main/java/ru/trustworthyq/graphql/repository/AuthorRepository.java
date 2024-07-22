package ru.trustworthyq.graphql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.trustworthyq.graphql.entity.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, Long> {
    Author findAuthorByFirstname(String firstname);
}
