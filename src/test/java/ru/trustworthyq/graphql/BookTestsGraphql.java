package ru.trustworthyq.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import ru.trustworthyq.graphql.entity.Book;


import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class BookTestsGraphql {

    @Autowired
    private GraphQlTester tester;



    @Test
    void readAllBooks() {
        String query = "query {\n" +
                "  readAllBooks {\n" +
                "    id\n" +
                "    name\n" +
                "    status\n" +
                "    author {\n" +
                "      id\n" +
                "      firstname\n" +
                "      lastname\n" +
                "    }\n" +
                "  }\n" +
                "}";
        List<Book> books = tester.document(query)
                .execute()
                .path("data.readAllBooks")
                .entityList(Book.class)
                .hasSizeGreaterThan(1)
                .get();
        Assertions.assertNotNull(books);
        Assertions.assertNotNull(books.get(0).getId());
        Assertions.assertNotNull(books.get(0).getName());
        Assertions.assertNotNull(books.get(0).getStatus());
        Assertions.assertNotNull(books.get(0).getAuthor().getId());
        Assertions.assertNotNull(books.get(0).getAuthor().getFirstname());
        Assertions.assertNotNull(books.get(0).getAuthor().getLastname());
    }
    @Test
    void readBookById() {
        String query = "query {\n" +
                "  readBookById (id: 8) {\n" +
                "    id\n" +
                "    name\n" +
                "    status\n" +
                "    author {\n" +
                "      id\n" +
                "      firstname\n" +
                "      lastname\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Book book = tester.document(query)
                .execute()
                .path("data.readBookById")
                .entity(Book.class)
                .get();
        Assertions.assertNotNull(book);
        Assertions.assertEquals(8, book.getId());
        Assertions.assertEquals("111111", book.getName());
        Assertions.assertEquals("222222", book.getStatus());
        Assertions.assertEquals(1, book.getAuthor().getId());
        Assertions.assertEquals("Maxim", book.getAuthor().getFirstname());
        Assertions.assertEquals("Dolgiy", book.getAuthor().getLastname());
    }
    @Test
    void readBooksByAuthorId() {
        String query = "query {\n" +
                "  readBooksByAuthorId (id: 1) {\n" +
                "    id\n" +
                "    name\n" +
                "    status\n" +
                "    author {\n" +
                "      id\n" +
                "      firstname\n" +
                "      lastname\n" +
                "    }\n" +
                "  }\n" +
                "}";
        List<Book> books = tester.document(query)
                .execute()
                .path("data.readBooksByAuthorId")
                .entityList(Book.class)
                .hasSizeGreaterThan(1)
                .get();
        Assertions.assertNotNull(books);
        Assertions.assertNotNull(books.get(0).getId());
        Assertions.assertNotNull(books.get(0).getName());
        Assertions.assertNotNull(books.get(0).getStatus());
        Assertions.assertNotNull(books.get(0).getAuthor().getId());
        Assertions.assertNotNull(books.get(0).getAuthor().getFirstname());
        Assertions.assertNotNull(books.get(0).getAuthor().getLastname());
    }
    @Test
    void createBook() {
        String query = "mutation {\n" +
                "  createBook(bookDTO: {\n" +
                "    name: \"111\",\n" +
                "    status: \"222\",\n" +
                "    authorId: 1\n" +
                "  }) {\n" +
                "    id\n" +
                "    name\n" +
                "    status\n" +
                "    author{\n" +
                "      id\n" +
                "      firstname\n" +
                "      lastname\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Book book = tester.document(query)
                .execute()
                .path("data.createBook")
                .entity(Book.class)
                .get();
        Assertions.assertNotNull(book);
        Assertions.assertEquals("111", book.getName());
        Assertions.assertEquals("222", book.getStatus());
        Assertions.assertEquals(1, book.getAuthor().getId());
    }
    @Test
    void updateBook() {
        String query = "mutation {\n" +
                "  updateBook (bookUpdateDTO: {id: 7, name: \"999\", status: \"999\", authorId: 1}){\n" +
                "    id\n" +
                "    name\n" +
                "    status\n" +
                "    author{\n" +
                "      id\n" +
                "      firstname\n" +
                "      lastname\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Book book = tester.document(query)
                .execute()
                .path("data.updateBook")
                .entity(Book.class)
                .get();
        Assertions.assertNotNull(book);
        Assertions.assertEquals("999", book.getName());
        Assertions.assertEquals("999", book.getStatus());
        Assertions.assertEquals(1, book.getAuthor().getId());
    }
    @Test
    void deleteBook() {
        String query = "mutation {\n" +
                "  deleteBook (id: 5)\n" +
                "}";
        Long id = tester.document(query)
                .execute()
                .path("data.deleteBook")
                .entity(Long.class)
                .get();
        Assertions.assertNotNull(id);
        Assertions.assertEquals(5, id);
    }
}
