package ru.trustworthyq.graphql.resolver;

import ru.trustworthyq.graphql.entity.Author;
import ru.trustworthyq.graphql.entity.Book;

import java.util.List;

public interface Query {
    List<Book> readAllBooks();
    Book readBookById(Long id);
    List<Book> readBooksByAuthorId(Long id);
    Author readAuthorByFirstname(String firstname);
}
