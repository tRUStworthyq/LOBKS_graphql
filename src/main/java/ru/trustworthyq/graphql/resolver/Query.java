package ru.trustworthyq.graphql.resolver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trustworthyq.graphql.entity.Book;
import ru.trustworthyq.graphql.repository.BookRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class Query {
    private BookRepository repository;

    public List<Book> readAllBooks() {
        return repository.findAll();
    }

    public Book readBookById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book doesn't exist"));
    }
}
