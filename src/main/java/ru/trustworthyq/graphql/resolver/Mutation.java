package ru.trustworthyq.graphql.resolver;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.trustworthyq.graphql.dto.BookDTO;
import ru.trustworthyq.graphql.entity.Book;
import ru.trustworthyq.graphql.repository.BookRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class Mutation {
    private BookRepository repository;

    public Book createBook(BookDTO bookDTO) {
        return repository.save(Book.builder()
                        .name(bookDTO.getName())
                        .author(bookDTO.getAuthor())
                        .status(bookDTO.getStatus())
                .build());
    }

    public Book updateBook(Book book) {
        Optional<Book> oNewBook = repository.findById(book.getId());

        if (oNewBook.isPresent()) {
            Book newBook = oNewBook.get();

            if (book.getName() != null) {
                newBook.setName(book.getName());
            }
            if (book.getAuthor() != null) {
                newBook.setAuthor(newBook.getAuthor());
            }
            if (book.getStatus() != null) {
                newBook.setStatus(book.getStatus());
            }

            repository.save(newBook);
            return newBook;
        }
        throw new EntityNotFoundException("Book didn't found");
    }

    public String deleteBook(Long id) {
        repository.deleteById(id);
        return "Book had been deleted";
    }
}
