package com.shop.books.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class AuthorTO {
    Integer id;

    @Size(min = 2, max = 255)
    @NotNull
    String name;

    Set<BookTO> book;
}
