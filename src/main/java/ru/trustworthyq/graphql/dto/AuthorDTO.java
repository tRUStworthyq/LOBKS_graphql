package ru.trustworthyq.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDTO {

    private String firstname;
    private String lastname;
}
