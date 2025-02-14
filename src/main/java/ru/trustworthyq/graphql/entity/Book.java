package ru.trustworthyq.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "book_sequence";
    @Id
    private Long id;
    private String name;
    private String status;
    private Author author;
}
