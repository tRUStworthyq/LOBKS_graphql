package ru.trustworthyq.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trustworthyq.graphql.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
