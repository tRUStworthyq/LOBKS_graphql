package ru.trustworthyq.graphql.resolver;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.trustworthyq.graphql.dto.AuthorDTO;
import ru.trustworthyq.graphql.dto.BookDTO;
import ru.trustworthyq.graphql.dto.BookUpdateDTO;
import ru.trustworthyq.graphql.entity.Author;
import ru.trustworthyq.graphql.entity.Book;
import ru.trustworthyq.graphql.repository.AuthorRepository;
import ru.trustworthyq.graphql.repository.BookRepository;
import ru.trustworthyq.graphql.service.SequenceGeneratorService;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class MutationImpl implements Mutation {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    private SequenceGeneratorService sequenceGeneratorService;
    public Book createBook(BookDTO bookDTO) {
        Optional<Author> author = authorRepository.findById(bookDTO.getAuthorId());
        log.info("Trying to find the author with id=" + bookDTO.getAuthorId()+ " and create new book");
        return bookRepository.save(Book.builder()
                        .id(sequenceGeneratorService.getSequenceNumber(Book.SEQUENCE_NAME))
                        .name(bookDTO.getName())
                        .author(authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("Author Doesn't exist")))
                        .status(bookDTO.getStatus())
                .build());
    }

    public Book updateBook(BookUpdateDTO bookUpdateDTO) {
        Optional<Book> oNewBook = bookRepository.findById(bookUpdateDTO.getId());
        log.info("Trying to find book with id=" + bookUpdateDTO.getId() + " and update book");
        if (oNewBook.isPresent()) {
            Book newBook = oNewBook.get();
            log.info(newBook.toString() + " was found");
            if (bookUpdateDTO.getName() != null) {
                newBook.setName(bookUpdateDTO.getName());
                log.info("The name field has been changed to name=" + bookUpdateDTO.getName());
            }
            if (bookUpdateDTO.getAuthorId() != null) {
                newBook.setAuthor(authorRepository.findById(bookUpdateDTO.getAuthorId())
                        .orElseThrow(() -> new RuntimeException("Author doesn't exist")));
                log.info("The author field has been changed to author with id=" + bookUpdateDTO.getAuthorId());
            }
            if (bookUpdateDTO.getStatus() != null) {
                newBook.setStatus(bookUpdateDTO.getStatus());
                log.info("The status field has been changed to status=" + bookUpdateDTO.getStatus());
            }

            log.info("The book with id=" + newBook.getId() + " has been updated");
            return bookRepository.save(newBook);
        }
        throw new RuntimeException("Book doesn't found");
    }

    public Long deleteBook(Long id) {
        bookRepository.deleteById(id);
        log.info("The book with id=" + id + " has been deleted");
        return id;
    }

    public Author createAuthor(AuthorDTO authorDTO) {
        log.info("Trying to create new " + authorDTO.toString());
        return authorRepository.save(Author.builder()
                .id(sequenceGeneratorService.getSequenceNumber(Author.SEQUENCE_NAME))
                .firstname(authorDTO.getFirstname())
                .lastname(authorDTO.getLastname())
                .build());
    }
}
