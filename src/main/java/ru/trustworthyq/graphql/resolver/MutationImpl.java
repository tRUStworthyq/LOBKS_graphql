package ru.trustworthyq.graphql.resolver;


import lombok.AllArgsConstructor;
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
public class MutationImpl implements Mutation {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    private SequenceGeneratorService sequenceGeneratorService;
    public Book createBook(BookDTO bookDTO) {
        Optional<Author> author = authorRepository.findById(bookDTO.getAuthorId());
        return bookRepository.save(Book.builder()
                        .id(sequenceGeneratorService.getSequenceNumber(Book.SEQUENCE_NAME))
                        .name(bookDTO.getName())
                        .author(authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("Author Doesn't exist")))
                        .status(bookDTO.getStatus())
                .build());
    }

    public Book updateBook(BookUpdateDTO bookUpdateDTO) {
        Optional<Book> oNewBook = bookRepository.findById(bookUpdateDTO.getId());

        if (oNewBook.isPresent()) {
            Book newBook = oNewBook.get();

            if (bookUpdateDTO.getName() != null) {
                newBook.setName(bookUpdateDTO.getName());
            }
            if (bookUpdateDTO.getAuthorId() != null) {
                newBook.setAuthor(authorRepository.findById(bookUpdateDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("Author doesn't exist")));
            }
            if (bookUpdateDTO.getStatus() != null) {
                newBook.setStatus(bookUpdateDTO.getStatus());
            }


            return bookRepository.save(newBook);
        }
        throw new RuntimeException("Book doesn't found");
    }

    public Long deleteBook(Long id) {
        bookRepository.deleteById(id);
        return id;
    }

    public Author createAuthor(AuthorDTO authorDTO) {
        return authorRepository.save(Author.builder()
                .id(sequenceGeneratorService.getSequenceNumber(Author.SEQUENCE_NAME))
                .firstname(authorDTO.getFirstname())
                .lastname(authorDTO.getLastname())
                .build());
    }
}
