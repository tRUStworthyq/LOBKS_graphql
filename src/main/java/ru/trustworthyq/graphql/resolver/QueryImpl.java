package ru.trustworthyq.graphql.resolver;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.trustworthyq.graphql.entity.Author;
import ru.trustworthyq.graphql.entity.Book;
import ru.trustworthyq.graphql.repository.AuthorRepository;
import ru.trustworthyq.graphql.repository.BookRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class QueryImpl implements Query{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    public Book readBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book doesn't exist"));
    }

    public List<Book> readBooksByAuthorId(Long id) {
        return bookRepository.findAll().stream().filter(book -> book.getAuthor().getId().equals(id)).toList();
    }

    public Author readAuthorByFirstname(String firstname) {
        return authorRepository.findAuthorByFirstname(firstname);
    }
}
