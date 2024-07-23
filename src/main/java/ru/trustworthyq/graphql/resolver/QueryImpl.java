package ru.trustworthyq.graphql.resolver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.trustworthyq.graphql.entity.Author;
import ru.trustworthyq.graphql.entity.Book;
import ru.trustworthyq.graphql.repository.AuthorRepository;
import ru.trustworthyq.graphql.repository.BookRepository;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class QueryImpl implements Query {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> readAllBooks() {
        log.info("Trying to find all books");
        return bookRepository.findAll();
    }

    public Book readBookById(Long id) {
        log.info("Trying to find book with id=" + id);
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book doesn't exist"));
    }

    public List<Book> readBooksByAuthorId(Long id) {
        log.info("Trying to find books with authorId=" + id);
        return bookRepository.findAll().stream().filter(book -> book.getAuthor().getId().equals(id)).toList();
    }

    public Author readAuthorByFirstname(String firstname) {
        log.info("Trying to find author by firstname=" + firstname);
        return authorRepository.findAuthorByFirstname(firstname);
    }
}
