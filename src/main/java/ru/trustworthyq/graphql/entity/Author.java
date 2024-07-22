package ru.trustworthyq.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {

    @Transient
    public static final String SEQUENCE_NAME = "author_sequence";

    @Id
    private Long id;
    private String firstname;
    private String lastname;
}
