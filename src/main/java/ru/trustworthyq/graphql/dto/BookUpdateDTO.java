package ru.trustworthyq.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookUpdateDTO {
    private Long id;
    private String name;
    private String status;
    private Long authorId;
}
