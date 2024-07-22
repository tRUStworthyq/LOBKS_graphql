package ru.trustworthyq.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import ru.trustworthyq.graphql.entity.Author;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class AuthorTestsGraphql {

    @Autowired
    private GraphQlTester tester;

    @Test
    void createAuthor() {
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
        Assertions.assertEquals(author.getFirstname(), "111");
        Assertions.assertEquals(author.getLastname(), "222");
    }
    @Test
    void readAuthorByFirstname() {

    }

}
