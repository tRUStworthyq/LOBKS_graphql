package ru.trustworthyq.graphql.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.trustworthyq.graphql.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {
}
