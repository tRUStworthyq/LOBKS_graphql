package ru.trustworthyq.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import ru.trustworthyq.graphql.entity.Author;
import ru.trustworthyq.graphql.entity.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class AuthorTestsGraphql {

    @Autowired
    private GraphQlTester tester;

    @Test
    void createAuthorShouldReturnAuthor() {
        String query = "mutation {\n" +
                "  createAuthor(authorDTO: {firstname: \"111\", lastname: \"222\"}) {\n" +
                "    id\n" +
                "    firstname\n" +
                "    lastname\n" +
                "  }\n" +
                "}";
        Author author = tester.document(query)
                .execute()
                .path("data.createAuthor")
                .entity(Author.class)
                .get();
        Assertions.assertNotNull(author);
        Assertions.assertEquals("111", author.getFirstname());
        Assertions.assertEquals("222", author.getLastname());
    }
    @Test
    void readAuthorByFirstnameShouldReturnAuthor() {
        String query = "query {\n" +
                "  readAuthorByFirstname (firstname: \"Maxim\") {\n" +
                "      id\n" +
                "      firstname\n" +
                "      lastname\n" +
                "  }\n" +
                "}";
        Author author = tester.document(query)
                .execute()
                .path("data.readAuthorByFirstname")
                .entity(Author.class)
                .get();
        Assertions.assertNotNull(author);
        Assertions.assertEquals(1, author.getId());
        Assertions.assertEquals("Maxim", author.getFirstname());
        Assertions.assertEquals("Dolgiy", author.getLastname());
    }

}
