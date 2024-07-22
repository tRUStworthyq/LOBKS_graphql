package ru.trustworthyq.graphql.controller;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.trustworthyq.graphql.dto.AuthorDTO;
import ru.trustworthyq.graphql.dto.BookDTO;
import ru.trustworthyq.graphql.dto.BookUpdateDTO;
import ru.trustworthyq.graphql.entity.Author;
import ru.trustworthyq.graphql.entity.Book;
import ru.trustworthyq.graphql.resolver.MutationImpl;
import ru.trustworthyq.graphql.resolver.QueryImpl;

import java.util.List;


@Controller
@AllArgsConstructor
public class MainController {
    private QueryImpl query;
    private MutationImpl mutation;

    @QueryMapping
    public List<Book> readAllBooks() {
        return query.readAllBooks();
    }
    @QueryMapping
    public Book readBookById(@Argument Long id) {
        return query.readBookById(id);
    }
    @QueryMapping
    public List<Book> readBooksByAuthorId(@Argument Long id) {
        return query.readBooksByAuthorId(id);
    }
    @QueryMapping
    public Author readAuthorByFirstname(@Argument String firstname) {
        return query.readAuthorByFirstname(firstname);
    }
    @MutationMapping
    public Author createAuthor(@Argument AuthorDTO authorDTO) {
        return mutation.createAuthor(authorDTO);
    }
    @MutationMapping
    public Book createBook(@Argument BookDTO bookDTO) {
        return mutation.createBook(bookDTO);
    }
    @MutationMapping
    public Book updateBook(@Argument BookUpdateDTO bookUpdateDTO) {
        return mutation.updateBook(bookUpdateDTO);
    }
    @MutationMapping
    public Long deleteBook(@Argument Long id) {
        return mutation.deleteBook(id);
    }
}
