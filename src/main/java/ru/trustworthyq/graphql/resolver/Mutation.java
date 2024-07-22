package ru.trustworthyq.graphql.resolver;

import ru.trustworthyq.graphql.dto.AuthorDTO;
import ru.trustworthyq.graphql.dto.BookDTO;
import ru.trustworthyq.graphql.dto.BookUpdateDTO;
import ru.trustworthyq.graphql.entity.Author;
import ru.trustworthyq.graphql.entity.Book;

public interface Mutation {
    Book createBook(BookDTO bookDTO);
    Book updateBook(BookUpdateDTO bookUpdateDTO);
    Long deleteBook(Long id);
    Author createAuthor(AuthorDTO authorDTO);
}
