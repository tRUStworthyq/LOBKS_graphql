package ru.trustworthyq.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.trustworthyq.graphql.entity.Author;

@AllArgsConstructor
@Data
public class BookDTO {
    private String name;
    private String status;
    private Long authorId;
}
