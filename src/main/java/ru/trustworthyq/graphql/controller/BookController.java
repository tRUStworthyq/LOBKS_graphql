package ru.trustworthyq.graphql.controller;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import ru.trustworthyq.graphql.dto.BookDTO;
import ru.trustworthyq.graphql.entity.Book;
import ru.trustworthyq.graphql.resolver.Mutation;
import ru.trustworthyq.graphql.resolver.Query;

import java.util.List;


@Controller
@AllArgsConstructor
public class BookController {
    private Query query;
    private Mutation mutation;

    @QueryMapping
    public List<Book> readAllBooks() {
        return query.readAllBooks();
    }
    @QueryMapping
    public Book readBookById(@Argument Long id) {
        return query.readBookById(id);
    }
    @MutationMapping
    public Book createBook(@Argument BookDTO bookDTO) {
        return mutation.createBook(bookDTO);
    }
    @MutationMapping
    public Book updateBook(@Argument Book book) {
        return mutation.updateBook(book);
    }
    @MutationMapping
    public String deleteBook(@Argument Long id) {
        return mutation.deleteBook(id);
    }
}
