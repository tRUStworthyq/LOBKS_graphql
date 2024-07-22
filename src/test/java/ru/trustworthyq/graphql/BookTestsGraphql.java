package ru.trustworthyq.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class BookTestsGraphql {

    @Autowired
    private GraphQlTester tester;

    @Test
    void readAllBooks() {

    }
    @Test
    void readBookById() {

    }
    @Test
    void readBooksByAuthorId() {

    }
    @Test
    void createBook() {

    }
    @Test
    void updateBook() {

    }
    @Test
    void deleteBook() {

    }
}
